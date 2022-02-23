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
        return DriverManager.getConnection("jdbc:sqlserver://casotto.database.windows.net:1433;database=Cassotto", "Tommaso", "Eo6AD!93");
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
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}