package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.ComandaBalneare;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Rappresenta un bagnino all'interno del casotto. Il suo ruolo: la gestione delle comande balneare.
 */
public class Bagnino extends Dipendente {

    private ArrayList<ComandaBalneare> listaOrdini;

    /**
     * crea un nuovo bagnino
     *
     * @param nome       nome del bagnina
     * @param cognome    cognome del bagnino
     * @param residenza  residenza del bagnino
     * @param telefono   telefono del bagnino
     * @param nomeUtente nomeUtente del bagnino
     * @param email      email del bagnino
     */
    public Bagnino(String nome, String cognome, String residenza, int telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email);
        this.listaOrdini = new ArrayList<>();
        getC().aggiungiDipendente(this);
    }

    /**
     * libera un ombrellone, quindi svuoterà i materiali aggiuntivi e impostera la disponibilità per essere prenotatato
     *
     * @param ombrellone ombrellone da sistemare.
     * @throws IllegalArgumentException se l'ombrellone non appartiene al casotto.
     */
    public void liberaOmbrellone(Ombrellone ombrellone) {
        //controllo che l'ombrellone sia contenuto
        if (this.getC().getOmbrelloni().contains(ombrellone)) {
            //controllo che l'ombrellone sia occupato
            int index = this.getC().getOmbrelloni().indexOf(ombrellone);
            if (!this.getC().getOmbrelloni().get(index).getDisponibilita()) {
               /* //libero ombrellone
                ombrellone.setDisponibilita(true);
                ombrellone.setFine(null);
                ombrellone.setComposizione(new ArrayList<>());
                this.getC().aggiornaOmbrellone(ombrellone);*/
                ombrellone.libera();
                this.getC().aggiungiOmbrellone(ombrellone);
            } else
                throw new IllegalArgumentException("l'ombrellone è libero");
        } else
            throw new IllegalArgumentException("l'ombrellone non appartiene al casotto");
    }

    /**
     * permette di aggiungere un'Attivit&agrave; al casotto
     *
     * @param a Attivit&agrave; da aggiungere
     */
    public void aggiungiAttivita(Attivita a) {
        if (this.getC().getAttivita().contains(a))
            throw new IllegalArgumentException("l'attività è gia presente");
        else
            this.getC().aggiungiAttivita(a);
    }

    /**
     * permette di modifcare un Attivit&agrave; gi&agrave; esistenete
     *
     * @param idAttivita identificativo dell' Attivit&agrave;.
     * @param nome       nuovo nome dell Attivit&agrave;
     * @param orario     nuovo orario dell Attivit&agrave;
     * @param postiMax   nuovi posti massimi dell Attivit&agrave;
     * @param postiMin   nuovi posti min dell  Attivit&agrave;
     */
    public void modificaAttivita(String idAttivita, String nome, LocalDateTime orario, int postiMax, int postiMin) {
        this.getC().aggiornaAttivita(idAttivita, nome, orario, postiMax, postiMin);
    }

    /**
     * aggiunge una comanda balneare alla lista del bagnino
     *
     * @param c comanda da aggiungere
     * @throws IllegalArgumentException se la comanda non è contenuta.
     */
    public void aggiungiComanda(ComandaBalneare c) {
        if (this.listaOrdini.contains(c))
            throw new IllegalArgumentException("la comanda è contenuta");
        else
            this.listaOrdini.add(c);
    }

    /**
     * @return la lista degli ordini del bagnino
     */
    public ArrayList<ComandaBalneare> getListaOrdini() {
        return listaOrdini;
    }
}