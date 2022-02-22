package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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
        //aggiunge cliente al casotto
        //c.aggiungiCLiente(this);

    }

    /**
     * prenotazione ombrellone
     *
     * @param ombrellone ombrellone da prenotare
     * @param fine       indica la data della fine della prenotazione,
     */
    public void prenotaOmbrellone(Ombrellone ombrellone, LocalDateTime fine) {
        PrenotazioneOmbrellone p = new PrenotazioneOmbrellone(this, ombrellone, fine);
    }

    /**
     * prenota un servizio balneare
     *
     * @param m    materiale da prenotare
     * @param fine data di fine della prenotazione
     * @param o    ombrellone del cliente
     */
    public void prenotaServizioBalneare(Materiale m, LocalDateTime fine, Ombrellone o) {
        HashMap<Materiale, Integer> ordine = new HashMap<>();
        ordine.put(m, 1);
        ComandaBalneare cB = new ComandaBalneare(o, this, ordine, fine);
        cB.chiudiComanda();
    }

    /**
     * prenota una lista di  servizi balneare
     *
     * @param ordine lista dei componenti da prenotare
     * @param fine   data di fine della prenotazione
     * @param o      ombrellone del cliente
     * @throws IllegalArgumentException se il saldo del cliente &egrave; minore uguale a 0
     */
    public void prenotaSeviziBalneare(HashMap<Materiale, Integer> ordine, LocalDateTime fine, Ombrellone o) {
        if (this.credito <= 0)
            throw new IllegalArgumentException("ricaricarica prima di effeetuare un'operazione");

        ComandaBalneare cB = new ComandaBalneare(o, this, ordine, fine);
        cB.chiudiComanda();

    }

    /**
     * permette di ricaricare il credito del cliente
     *
     * @param f denaro da aggiungere
     * @throws IllegalArgumentException se il valore da inserire &egrave; minore o uguale 0
     */
    public void ricarica(Float f) {
        if (f <= 0)
            throw new IllegalArgumentException("valore insufficiente");

        try {
            Connection con=Database.getConnection();
            con.createStatement().executeUpdate("UPDATE Cliente SET Conto= Conto+"+f+"Where ID='"+this.getId()+"'");
            this.credito += f;
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            Connection connection = null;
            try {
                connection = Database.getConnection();
                String query = "UPDATE Cliente SET Conto=" + this.credito + " WHERE ID='" + this.getId() + "';";
                connection.createStatement().executeUpdate(query);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } else
            return false;
    }

    private void aggiornacredito() {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT Conto FROM Cliente WHERE ID='"+this.getId()+"';";
            ResultSet rs =connection.createStatement().executeQuery(query);
            if(rs.next()){
                this.credito = rs.getFloat("Conto");
            }
           else
               throw new IllegalArgumentException("cliente inesistente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * permette di prenotare un attivit&agrave;
     *
     * @param a attivit&agrave; da prenotare
     */
    public void prenotaAttivita(Attivita a) {
        if (!c.getAttivita().contains(a))
            throw new IllegalArgumentException("l'attivita non esiste");
        int index = c.getAttivita().indexOf(a);
        //aggiungo componente
        c.getAttivita().get(index).aggiungiComponente(this);
        c.aggiungiAttivita(a);
    }

    /**
     * @return il saldo del cliente
     */
    public float getSaldo() {
        return this.credito;
    }

    /**
     * permette di prenotare un servizio ristorazione
     *
     * @param p lista dei prodotti da ordinare
     * @param o ombrellone del cliente
     */
    public void prenotaServizioRistorazione(ArrayList<Prodotto> p, Ombrellone o) {
        ComandaRistorazione cR = new ComandaRistorazione(o, this, p);
    }

}