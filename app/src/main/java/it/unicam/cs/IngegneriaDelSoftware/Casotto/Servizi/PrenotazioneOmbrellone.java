package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * permette di prenotare un ombrellone nel casotto
 */
public class PrenotazioneOmbrellone {

    private static Casotto casotto;
    private final UUID idPrenotazione;
    private String idCliente;
    private String idOmbrellone;
    private Cliente c;
    private Ombrellone o;
    private LocalDateTime dataFine;
    private LocalDateTime dataInizio;

    /**
     * permette di creare una nuova prenotazione
     *
     * @param c        cliente della prenotazione
     * @param o        ombrellone della prenotazione
     * @param dataFine data di fine prenotazione
     * @throws IllegalArgumentException se il cliente non appartiene alla lista dei clienti del casotto
     * @throws IllegalArgumentException se l'ombrellone non appartiene al casotto
     * @throws IllegalArgumentException se la data è precedente alla data attauae.
     */
    public PrenotazioneOmbrellone(Cliente c, Ombrellone o, LocalDateTime dataFine) {
        casotto = Casotto.getInstance();

        if (!casotto.getClienti().contains(c))
            throw new IllegalArgumentException("cliente errato");
        if (!casotto.getOmbrelloni().contains(o))
            throw new IllegalArgumentException("ombrellone errato");
        if (dataFine.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("data erata");

        //controllo cliente
        this.c = c;
        this.o = o;
        this.dataFine = dataFine;
        this.idPrenotazione = UUID.randomUUID();

        this.prenotaOmbrellone(dataFine);

        //aggiungo la prenotazione al casotto
        casotto.aggiungiPrenotazione(this);

    }

    public PrenotazioneOmbrellone(String idCliente, String idOmbrellone, LocalDateTime fine, LocalDateTime inizio) {
        this.idPrenotazione = UUID.randomUUID();
        this.idCliente = idCliente;
        this.idOmbrellone = idOmbrellone;
        this.dataFine = fine;
        this.dataInizio = inizio;
    }

    public PrenotazioneOmbrellone(String id, String idCliente, String idOmbrellone, Timestamp fine,Timestamp inizio) {
        this.idPrenotazione = UUID.fromString(id);
        this.idCliente = idCliente;
        this.idOmbrellone = idOmbrellone;
        this.dataFine = fine.toLocalDateTime();
        this.dataInizio=inizio.toLocalDateTime();
    }

    /**
     * Permette di prenotare un ombrellone
     *
     * @param dataFine data di fine prenotazione
     */
    private void prenotaOmbrellone(LocalDateTime dataFine) {
        //prenoto ombrellone
        this.o.setFine(dataFine);
        this.o.setDisponibilita(false);
        int index = casotto.getOmbrelloni().indexOf(o);
        casotto.getOmbrelloni().set(index, o);
    }


    //!----------GET------------!

    /**
     * @return l'identificativo della prenotazione dell'ombrellone
     */
    public String getIdComanda() {
        return this.idPrenotazione.toString();
    }

    /**
     * @return l'ombrellone della prenotazione
     */
    public Ombrellone getO() {
        return this.o;
    }

    /**
     * @return il cliente della prenotazione
     */
    public Cliente getC() {
        return this.c;
    }

    /**
     * @return la data di fine della prenotazione
     */
    public LocalDateTime getDataFine() {
        return dataFine;

    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    //!----------SET------------!

    /**
     * imposta la data di fine della prenotazione dell'ombrellone
     *
     * @param dataFine nuova data di fine
     * @throws IllegalArgumentException se la data &egrave; precedente alla data attuale
     */

    public void setDataFine(LocalDateTime dataFine) {
        if (!dataFine.isBefore(LocalDateTime.now())) {
            this.prenotaOmbrellone(dataFine);
            this.dataFine = dataFine;
        }
        throw new IllegalArgumentException("data erata");
    }

    public float calcolaConto() {
        float tariffa = Casotto.getInstance().getOmbrellone(idOmbrellone).getTariffa();
        float conto = tariffa * (dataInizio.until(dataFine, ChronoUnit.DAYS) + 1);
        if (dataInizio.getHour() != dataFine.getHour()) {
            //c'è una mezza giornata, quindi aggiungo una mezza giornata al conto
            conto += tariffa / 2;
        }
        return conto;

    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdOmbrellone() {
        return idOmbrellone;
    }

    @Override
    public String toString() {
        Cliente cliente = Casotto.getInstance().getCliente(this.idCliente);
        Ombrellone o = Casotto.getInstance().getOmbrellone(this.idOmbrellone);
        return "Cliente: " + cliente.getNome() + " " + cliente.getCognome() + '\n' +
                "Ombrellone numero: " + o.getNumero() + " Fila: " + o.getFila() + '\n' +
                "Inizio Prenotazione:" + dataInizio.toString() + "\n" +
                "Fine Prenotazione=" + dataFine + "\n"+
                "Totale:" +this.calcolaConto()+"€";
    }

    public void chiudiPrenotazione() {
        Cliente cliente = Casotto.getInstance().getCliente(this.idCliente);
        Alert alert;
        if (cliente.paga(this.calcolaConto())) {
            //aggiungo Comanda

            try {
                Connection connection = Database.getConnection();
                String query = "INSERT INTO Prenotazioni(Id, idCliente, idOmbrellone, inizio, fine) VALUES ("
                        + "'" + this.getIdComanda()+ "',"
                        + "'" + this.getIdCliente() + "',"
                        + "'" + this.getIdOmbrellone() + "',"

                        + "'"+ Timestamp.valueOf(this.getDataInizio()) + "',"
                        + "'"+ Timestamp.valueOf(this.getDataFine()) + "');";
                connection.createStatement().executeUpdate(query);
                //aggiorno ombrellone
                 query = "UPDATE Ombrelloni SET Disponibilita='Non Disponibile' where Id='"+this.getIdOmbrellone()+"';";
                connection.createStatement().executeUpdate(query);
                query = "UPDATE Ombrelloni SET Fine='"+this.dataFine+"' where Id='"+this.getIdOmbrellone()+"';";
                connection.createStatement().executeUpdate(query);
                alert = new Alert(Alert.AlertType.CONFIRMATION, "Prenotazione effettuata correttamente");
                alert.setTitle("Prenotazione Effettuata");
                alert.setHeaderText("Prenotazione effettuata con successo");
                alert.show();
            } catch (SQLException e) {
                alert = new Alert(Alert.AlertType.ERROR, "ErroreSistema: impossibile effettuare l'operazione");
                alert.show();
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Errore: Credito insufficiente");
            alert.show();
        }
    }
}