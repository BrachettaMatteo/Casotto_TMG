package it.unicam.cs.IngegneriaDelSoftware.Casotto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Gestisce il database
 */
public class Database {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:sqlserver://casotto.database.windows.net:1433;database=Cassotto", "Tommaso", "Eo6AD!93");
        return connection;
    }

    /**
     * Permette di verificare il login e restitutisce il ruolo dell'username
     *
     * @param nomeUtente nomeUtente da verificare
     * @param password   password da erificare
     * @return ruolo dell'username oppure una stringa vuota
     */
    public static String login(String nomeUtente, String password) {
        try {
            Connection connection = getConnection();
            String query = "Select Ruolo from Utente Where Username='" + nomeUtente + "' AND Password='" + password + "';";
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                return rs.getString("Ruolo");
            } else
                return "";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean checkUsername(String nomeUtente) {
        try {
            String query = "SELECT Ruolo from Utente where Username='" + nomeUtente + "'";
            ResultSet rs = getConnection().createStatement().executeQuery(query);
            if (rs.next())
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Poermette di aggiungere un dipendete al casotto
     *
     * @param nU    nomeUtente del dipendete
     * @param ruolo ruolo del dipenente
     * @param email email del dipendente
     */
    public void aggiungiDipendente(String nU, String ruolo, String email) {
        // TODO - implement Database.aggiungiDipendente
        throw new UnsupportedOperationException();
    }

    /**
     * Permette di creare un nuovo cliente
     *
     * @param nomeUtente nome utente del nuovo cliente
     * @param email      email del nuovo cliente
     * @param password   password del nuovo cliente
     */
    public void nuovoCliente(String nomeUtente, String email, String password) {
        // TODO - implement Database.nuovoCliente
        throw new UnsupportedOperationException();
    }

}