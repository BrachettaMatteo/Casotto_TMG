package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Balneare;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Comanda;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione.Materiale;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * Rappresenta una comanda Balneare ovvero la possibilità di prenotare materiali aggiuntivi all'ombrellone
 */
public class ComandaBalneare extends Comanda {

    private final String idmateriale;
    private final int quantita;
    private Materiale materiale;
    private LocalDateTime durata;

    /**
     * permette di creare una nuova comandaBalneare sapendo materiale e durata
     *
     * @param idOmbrellone identificativo dell'ombrellone
     * @param idCliente    identificativo del cliente
     * @param materiale    materiale
     * @param quantita     quantita del materiale richiesto
     * @param durata       durata del tempo di affitto
     */

    public ComandaBalneare(String idOmbrellone, String idCliente, Materiale materiale, Timestamp durata, int quantita) {
        super(idOmbrellone, idCliente);

        if (durata.toLocalDateTime().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("la durata è errata");

        if (Casotto.getInstance().getQuantitaMateriale(materiale.getNome())
                >= quantita) {
            this.materiale = materiale;
            this.idmateriale = materiale.getId();
            this.durata = durata.toLocalDateTime();

        } else {
            throw new IllegalArgumentException("materiale insufficiente");
        }
        this.quantita = quantita;

    }


    public ComandaBalneare(String idComanda, String idOmbrellone, String idCliente, String idmateriale, Timestamp durata, String status, int quantita) {
        super(idComanda, idOmbrellone, idCliente, status);

        if (durata != null)
            if (durata.toLocalDateTime().isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException("la durata è errata");
            else
                this.durata = durata.toLocalDateTime();
        this.idmateriale = idmateriale;


        this.quantita = quantita;

    }

    @Override
    public float calcolaConto() {
        return this.materiale.getCosto() * this.convertiOreGiorni(HOURS.between(LocalDateTime.now(), this.durata));
    }

    /**
     * permette di determinari i giorni della prenotazione
     *
     * @param ore ore da convertire in giorni
     * @return il numero dei giorni se le ore sono minore di 24 conterà un giorno
     */
    private int convertiOreGiorni(float ore) {
        if (ore < 24 && ore > 0)
            return 1;
        else
            //ceil arrotonda
            return (int) Math.ceil(ore / 24);
    }


    @Override
    public void chiudiComanda() {
        Alert alert;
        if (Casotto.getInstance().getCliente(this.idCliente()).paga(this.calcolaConto())) {
            try {
                Connection con = Database.getConnection();
                String query = "INSERT INTO ComandaBalneare(ID, idOmbrellone, idCliente, Durata, idMateriale, Status, Quantita) VALUES ('" +
                        this.getId() + "', " +
                        "'" + this.getIdOmbrellone() + "', " +
                        "'" + this.getIdCliente() + "', " +
                        "'" + Timestamp.valueOf(this.durata) + "', " +
                        "'" + materiale.getId() + "', " +
                        "'daElaborare', " +
                        quantita +
                        ");";
                con.createStatement().executeUpdate(query);
                alert = new Alert(Alert.AlertType.INFORMATION, "Prenotazione effettuata correttamente");
                alert.show();
            } catch (SQLException e) {
                alert = new Alert(Alert.AlertType.ERROR, "Errore Sistema prenotazione comanda Balneare");
                alert.show();
                e.printStackTrace();
            }

        } else {
            alert = new Alert(Alert.AlertType.ERROR, "Credito insufficiente");
            alert.show();
        }
    }


    public int getQuantita() {
        return quantita;
    }

    public String getMateriale() {
        return Casotto.getInstance().getNomeMateriale(idmateriale);

    }

    public Timestamp getData() {
        if (durata == null)
            return null;
        return Timestamp.valueOf(durata);
    }

    public String getId() {
        return super.getIdComanda().toString();
    }

    public String idCliente() {
        return super.getIdCliente();
    }

    public int getOmbrellone() {
        return super.getO().getNumero();
    }

    @Override
    public String toString() {
        return "ComandaBalneare:\n " +
                "materiale: '" + materiale.getNome() + "\n" +
                "quantità: " + quantita + "\n" +
                "costo: " + this.materiale.getCosto();
    }
}