package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Persona;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Rappresenta il gestore dello chalet
 */
public class Gestore extends Persona {

    /**
     * lista degli chalet posseduti da un gestore
     */
    private final ArrayList<Casotto> listaChalet;


    /**
     * Permette di creare un nuov gestore
     *
     * @param nome       nome del gestore
     * @param cognome    cognome del gestore
     * @param residenza  residenza del gestore
     * @param telefono   telefono del gestore
     * @param nomeUtente nomeUtente del gestore
     * @param email      email del gestore
     */
    public Gestore(String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email);
        this.listaChalet = new ArrayList<>();
    }

    public Gestore(String id, String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        super(id, nome, cognome, residenza, telefono, nomeUtente, email);
        this.listaChalet = new ArrayList<>();

    }

    /**
     * permette di creare una spiaggia.
     *
     * @param file                    numero di file della spiaggia
     * @param numeroOmbrelloniPerFila numero di ombrelloni per fila di una spiaggia.
     * @throws IllegalArgumentException se il numero delle file o il numero degli ombrelloni per fila
     *                                  &egrave; minore di 0;
     */
    public void creaSpiaggia(int file, int numeroOmbrelloniPerFila) {

        Connection con;
        try {
            con = Database.getConnection();
            con.createStatement().executeUpdate("DELETE FROM Ombrellone");
            String query;
            int numero = 1;
            for (int i = 1; i <= file; i++) {
                for (int k = 1; k <=numeroOmbrelloniPerFila; k++) {
                    Ombrellone o = new Ombrellone(i, 0.0f, numero);
                    numero++;
                    query = "INSERT INTO Ombrellone (ID, Numero, Fila, Tariffa, Disponibilita) VALUES (" +
                            "'" + o.getId() + "'," +
                            "'" + o.getNumero() + "'," +
                            "'" + o.getFila() + "'," +
                            "'" + o.getTariffa() + "'," +
                            "'" + o.getDisponibilita() + "');";
                    con.createStatement().executeUpdate(query);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * permette di impostare una tariffa per una fila di ombrelloni
     *
     * @param fila  fila di ombrelloni a cui aggiungere la tariffa.
     * @param costo costo della fila.
     */
    public void setTariffe(int fila, float costo) {

        Connection con ;
        try {
            con = Database.getConnection();
            String query = "UPDATE Ombrellone  SET Tariffa='" + costo + "' WHERE Fila = '" + fila + "';";
            con.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}