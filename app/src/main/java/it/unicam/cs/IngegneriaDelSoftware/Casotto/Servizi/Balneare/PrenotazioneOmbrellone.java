package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Balneare;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente.Cliente;
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

    private final UUID idPrenotazione;
    private final String idCliente;
    private final String idOmbrellone;
    private LocalDateTime dataFine;
    private LocalDateTime dataInizio;


    public PrenotazioneOmbrellone(String idCliente, String idOmbrellone, LocalDateTime fine, LocalDateTime inizio) {
        this.idPrenotazione = UUID.randomUUID();
        this.idCliente = idCliente;
        this.idOmbrellone = idOmbrellone;
        this.dataFine = fine;
        this.dataInizio = inizio;
    }

    public PrenotazioneOmbrellone(String id, String idCliente, String idOmbrellone, Timestamp fine, Timestamp inizio) {
        this.idPrenotazione = UUID.fromString(id);
        this.idCliente = idCliente;
        this.idOmbrellone = idOmbrellone;
        this.dataFine = fine.toLocalDateTime();
        this.dataInizio = inizio.toLocalDateTime();
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
        return Casotto.getInstance().getOmbrellone(this.idOmbrellone);
    }

    /**
     * @return il cliente della prenotazione
     */
    public Cliente getC() {
        return Casotto.getInstance().getCliente(this.idCliente);
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
        this.dataFine = dataFine;
    }

    public float calcolaConto() {
        float tariffa = Casotto.getInstance().getOmbrellone(idOmbrellone).getTariffa();
        //mezza giornata
        if (dataInizio.getYear() == dataFine.getYear() &&
                dataInizio.getMonth() == dataFine.getMonth() &&
                dataInizio.getDayOfMonth() == dataFine.getDayOfMonth() &&
                dataInizio.getHour() != dataFine.getHour())
            //controlle se e mezza giornata
            if (dataFine.getHour() - dataInizio.getHour() < 7)
                return tariffa / 2;
            else return tariffa;

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
                "Fine Prenotazione=" + dataFine + "\n" +
                "Totale:" + this.calcolaConto() + "€";
    }

    public void chiudiPrenotazione() {
        Cliente cliente = Casotto.getInstance().getCliente(this.idCliente);
        Alert alert;
        if (cliente.paga(this.calcolaConto())) {

            try {
                Connection connection = Database.getConnection();
                String query = "INSERT INTO Prenotazione(ID, idCliente, idOmbrellone, Inizio, Fine) VALUES ("
                        + "'" + this.getIdComanda() + "',"
                        + "'" + this.getIdCliente() + "',"
                        + "'" + this.getIdOmbrellone() + "',"

                        + "'" + Timestamp.valueOf(this.getDataInizio()) + "',"
                        + "'" + Timestamp.valueOf(this.getDataFine()) + "');";
                connection.createStatement().executeUpdate(query);
                //aggiorno ombrellone
                query = "UPDATE Ombrellone SET Disponibilita = 'Non Disponibile' where ID='" + this.getIdOmbrellone() + "';";
                connection.createStatement().executeUpdate(query);
                query = "UPDATE Ombrellone SET Fine='" + Timestamp.valueOf(this.dataFine) + "' where ID ='" + this.getIdOmbrellone() + "';";
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

    public void setDataInizio(LocalDateTime inizio) {
        this.dataInizio = inizio;
    }
}