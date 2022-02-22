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
import java.util.Map;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * Rappresenta una comanda Balneare ovvero la possibilità di prenotare materiali aggiuntivi all'ombrellone
 */
public class ComandaBalneare extends Comanda {

    private static Casotto casotto;
    private String idmateriale;
    private int quantita;
    private Materiale materiale;
    private Map<Materiale, Integer> materiali;
    private LocalDateTime durata;

    /**
     * permette di creare una nuova comandaBalneare sapendo materiale e durata
     *
     * @param o      ombrellone
     * @param c      cliente
     * @param ordine mappa contente materiale e quantita da ordinare.
     * @param durata durata del tempo di affitto
     */
    public ComandaBalneare(Ombrellone o, Cliente c, Map<Materiale, Integer> ordine, LocalDateTime durata) {
        super(o, c);
        casotto = getCasotto();
        if (ordine.isEmpty())
            throw new IllegalArgumentException("La lista dei materiali è vuota");
        if (durata.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("la durata è errata");

        if (this.verificaMateriali(ordine)) {
            this.materiali = ordine;
            this.durata = durata;
        }

    }

    public ComandaBalneare(String idOmbrellone, String idCliente, Materiale materiale, Timestamp durata, int quantita) {
        super(idOmbrellone, idCliente);

        if (durata.toLocalDateTime().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("la durata è errata");

        if (Casotto.getInstance().getQuantitaMateriale(materiale.getNome())
                        >= quantita) {
            this.materiale = materiale;
            this.durata = durata.toLocalDateTime();

        } else {
            throw new IllegalArgumentException("materiale insufficiente");
        }
        this.quantita = quantita;

    }

    /**
     * @param idOmbrellone identificativo dell'ombrellone
     * @param idCliente    identificativo del cliente
     * @param materiale    materiale della comanda
     * @param durata       durata della comanda
     * @param status       status della comanda
     */
    public ComandaBalneare(String idComanda, String idOmbrellone, String idCliente, Materiale materiale, Timestamp durata, String status) {
        super(idComanda, idOmbrellone, idCliente, status);
        casotto = getCasotto();
        if (durata.toLocalDateTime().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("la durata è errata");

        if (casotto.getMagazzino().contains(materiale) &&
                casotto.getMagazzino().get(casotto.getMagazzino().indexOf(materiale)).getQuantita()
                        >= materiale.getQuantita()) {
            this.materiale = materiale;
            this.durata = durata.toLocalDateTime();

        }

    }

    public ComandaBalneare(String idComanda, String idOmbrellone, String idCliente, String materiale, Timestamp durata, String status, int quantita) {
        super(idComanda, idOmbrellone, idCliente, status);
        casotto = getCasotto();
        if (durata != null)
            if (durata.toLocalDateTime().isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException("la durata è errata");
            else
                this.durata = durata.toLocalDateTime();
        this.idmateriale = materiale;


        this.quantita = quantita;

    }


    /**
     * permette di verificare se i materiali è disponibile nel Casotto.
     *
     * @param ordine mappa contenente il materiale da oridinare
     * @return true se il materiale &egrave; disponibile altrimenti flase.
     */
    private boolean verificaMateriali(Map<Materiale, Integer> ordine) {
        //controllo materiali
        for (Materiale m : ordine.keySet())
            if (casotto.getMagazzino().contains(m)) {
                if (casotto.getMagazzino().get(casotto.getMagazzino().indexOf(m)).getQuantita() >= ordine.get(m)) {
                    return true;
                } else
                    throw new IllegalArgumentException("numero oggetti non disponibili");

            } else
                throw new IllegalArgumentException("materiale non contenuto");
        return false;
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

    public Integer getNumeroOmbrellone() {
        return this.getO().getNumero();
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
                alert = new Alert(Alert.AlertType.INFORMATION , "Prenotazione effettuata correttamente");
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

    public Integer getNumeroMateriali() {
        return this.materiale.getQuantita();
    }
    //!--------------DA-ELIMINARE------------!

    /**
     * permette di aggiungere un materiale alla lista dei materiali da ordinare
     *
     * @param m        materiale da oaggiungere
     * @param quantita numero di elementi da aggiungere
     * @throws IllegalArgumentException se la quantita disponibile &egrave;
     *                                  minore della quantit&agrave; richiesta
     * @throws IllegalArgumentException se il materiale non &egrave;
     *                                  contenuto in magazzino.
     * @throws NullPointerException     se il materiale &egrave; null.
     * @throws IllegalArgumentException se la quantit&agrave; minore di 1.
     */
    public void aggiungiMateriale(Materiale m, int quantita) {
        if (m == null)
            throw new NullPointerException("materiale null");
        if (quantita <= 0)
            throw new IllegalArgumentException("quantità minore di 1");
        if (casotto.getMagazzino().contains(m)) {
            if (casotto.getMagazzino().get(casotto.getMagazzino().indexOf(m)).getQuantita() >= quantita) {
                this.materiali.put(m, quantita);
            } else
                throw new IllegalArgumentException("numero oggetti non disponibili");
        } else throw new IllegalArgumentException("materiale non contenuto");


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
                "quantita: " + quantita + "\n" +
                "costo: " + this.materiale.getCosto();
    }
}