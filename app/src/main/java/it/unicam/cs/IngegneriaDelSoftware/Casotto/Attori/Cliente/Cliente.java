package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Persona;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita.ComandaAttivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Balneare.ComandaBalneare;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Ristorazione.ComandaRistorazione;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Rappresenta un cliente e le sue cartteristiche
 */
public class Cliente extends Persona {

    private static Casotto c;
    private float credito;

    /**
     * permette di creare un nuovo Cliente
     *
     * @param nome       nome del cliente
     * @param cognome    cognome del cliente
     * @param residenza  residenza del cliente
     * @param telefono   telefono del cliente
     * @param nomeUtente nomeutente del cliente
     * @param email      email del cliente
     */
    public Cliente(String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email);
        this.credito = 0;
        c = Casotto.getInstance();
        //aggiunge cliente al casotto
        c.aggiungiCLiente(this);

    }

    public Cliente(String id, String nome, String cognome, String residenza, String telefono, String nomeUtente, String email, float conto) {
        super(id, nome, cognome, residenza, telefono, nomeUtente, email);
        this.credito = conto;
        c = Casotto.getInstance();

    }

    /**
     * prenota un servizio balneare
     */
    public void prenotaServizioBalneare(ComandaBalneare cb) {
        //controllo dati
        if (cb == null)
            throw new NullPointerException("comanda balneare errata");
        cb.chiudiComanda();
    }


    /**
     * permette di pagare un'importo
     *
     * @param importo da pagare
     * @return <code>true</code> se il pagamento &egrave; andata a buon fine
     * altrimenti <code>false</code>
     */
    public boolean paga(float importo) {
        this.aggiornacredito();
        if (this.credito - importo > 0) {
            this.credito -= importo;
            Connection connection;
            try {
                connection = Database.getConnection();
                String query = "UPDATE Cliente SET Conto=" + this.credito + " WHERE ID='" + this.getId() + "';";
                connection.createStatement().executeUpdate(query);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } else
            return false;
    }

    private void aggiornacredito() {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT Conto FROM Cliente WHERE ID='" + this.getId() + "';";
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                this.credito = rs.getFloat("Conto");
            } else
                throw new IllegalArgumentException("cliente inesistente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * permette di prenotare un attivit&agrave;
     */
    public void prenotaAttivita(ComandaAttivita cA) {
        if (cA == null)
            throw new NullPointerException("comanda attivita null");

        cA.chiudiComanda();
    }

    /**
     * @return il saldo del cliente
     */
    public float getSaldo() {
        return this.credito;
    }

    /**
     * permette di prenotare un servizio ristorazione
     */
    public void prenotaServizioRistorazione(ComandaRistorazione cr) {
        if (cr == null)
            throw new NullPointerException("comanda ristorazione null");
        cr.chiudiComanda();
    }

}