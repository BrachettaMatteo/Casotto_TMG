package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;

/**
 * Rappresenta un Dipendente e le sue caratteristiche
 */
public class Dipendente extends Persona {
    private static Casotto c;
    String ruolo;

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
    public Dipendente(String nome, String cognome, String residenza, String telefono, String nomeUtente, String email,String ruolo) {
        super(nome, cognome, residenza, telefono, nomeUtente, email);
        c = Casotto.getInstance();
        this.ruolo=ruolo;
    }

    public Dipendente(String id, String nome, String cognome, String residenza, String telefono, String nomeUtente, String email,String ruolo) {
        super(id,nome, cognome, residenza, telefono, nomeUtente, email);
        this.ruolo=ruolo;
    }

    /**
     * @return il casotto
     */
    public Casotto getC() {
        return c;
    }

    public String getRuolo(){
        return ruolo;
    }


    @Override
    public String toString() {
        return "Dipendente:\n" +
                "nome: '" + getNome() + '\n' +
                "Cognome: '" + getCognome() + '\n' +
                "Residenza: "+getResidenza()+"\n"+
                "Telefono: "+getTelefono()+"\n"+
                "NomeUtente: "+getNomeUtente()+"\n"+
                "ruolo: '" + ruolo + '\'';
    }


}