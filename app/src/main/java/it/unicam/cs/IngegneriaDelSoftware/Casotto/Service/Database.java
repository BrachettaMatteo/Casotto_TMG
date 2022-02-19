package it.unicam.cs.IngegneriaDelSoftware.Casotto.Service;

import java.sql.*;

/**
 * Gestisce il database
 */
public class Database {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Casotto?autoReconnect=true&useSSL=false", "User", "Casotto2022");        return connection;
    }

    /**
     * Permette di verificare il login e restitutisce il ruolo dell'username
     *
     * @return ruolo dell'username oppure una stringa vuota
     * @param nomeUtente nomeUtente da verificare
     * @param password   password da erificare
     */
    public static String login(String nomeUtente, String password) {
        try {
            Connection connection= getConnection();
            String query="Select Ruolo from Utenti Where Username='"+nomeUtente+"'&& Password = '"+password+"';";
           ResultSet rs = connection.createStatement().executeQuery(query);
            if(rs.next()){
                return rs.getString("Ruolo");
            }else
                return "";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
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