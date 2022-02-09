package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Comanda;

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
    public Cassiere(String nome, String cognome, String residenza, int telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email,"Cassiere");
    }

    public Cassiere(String id, String nome, String cognome, String residenza, int telefono, String nomeUtente, String email) {
        super(id, nome, cognome, residenza, telefono, nomeUtente, email,"Cassiere");
    }

    /**
     * permette pagamaneot di una comanda
     *
     * @param c comanda aa pagamre
     * @throws IllegalArgumentException se la comanda &egrave; null.
     */
    public void pagamento(Comanda c) {
        if (c == null)
            throw new IllegalArgumentException("comada null");

    }

}