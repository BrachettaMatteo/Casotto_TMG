package it.unicam.cs.IngegneriaDelSoftware.Casotto.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestisce il database
 */
public class Database {

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Casotto?autoReconnect=true&useSSL=false", "User", "Casotto2022");
        return connection;
    }

    /**
     * Premtte di verificar en login
     *
     * @param nomeUtente nomeUtente da verificare
     * @param password   password da erificare
     */
    public void login(String nomeUtente, String password) {
        // TODO - implement Database.login
        throw new UnsupportedOperationException();
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