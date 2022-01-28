package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;

/**
 * Rappresenta un Dipendente e le sue caratteristiche
 */
public class Dipendente extends Persona {
    private static Casotto c;

    /**
     * crea un nuovo dipendenre
     *
     * @param nome       nome del dipendente
     * @param cognome    cognome del dipendete
     * @param residenza  residenza del dipendente
     * @param telefono   telefono del dipendente
     * @param nomeUtente nomeUtente del dipendente
     * @param email      email del dipendete
     */
    public Dipendente(String nome, String cognome, String residenza, int telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email);
        c = Casotto.getInstance();
    }

    /**
     * @return il casotto
     */
    public Casotto getC() {
        return c;
    }

}