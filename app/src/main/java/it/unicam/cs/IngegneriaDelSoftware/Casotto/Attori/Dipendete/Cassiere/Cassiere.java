package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Cassiere;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Dipendente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Rappresenta il casiere, il suo ruolo &egrave;....
 */
public class Cassiere extends Dipendente {
    /**
     * permette di creare un nuovo cassiere
     *
     * @param nome       nome del cassiere
     * @param cognome    cognome del cassiere
     * @param residenza  residenza del cassiere
     * @param telefono   telefono del cassiere
     * @param nomeUtente moneUtente del cassiere
     * @param email      email del cassiere
     */
    public Cassiere(String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email, "Cassiere");
    }

    public Cassiere(String id, String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        super(id, nome, cognome, residenza, telefono, nomeUtente, email, "Cassiere");
    }

    public Boolean ricaricaConto(String idCliente, Float importo) {
        try {
            Connection con = Database.getConnection();
            con.createStatement().executeUpdate("UPDATE Cliente SET Conto= Conto+" + importo + " WHERE ID='" + idCliente + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}