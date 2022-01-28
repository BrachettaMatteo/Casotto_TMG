package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;

import java.util.ArrayList;

/**
 * Rappresenta una Comanda ristorazione e le sue caratteristiche
 */
public class ComandaRistorazione extends Comanda {

    private ArrayList<Prodotto> prodottiOrdinati;
    private Status Satus;

    /**
     * permette di creare una comanda ristorazione
     *
     * @param o                ombrellone
     * @param c                cliente
     * @param prodottiOrdinati lista dei prodotto da ordinare
     * @throws IllegalArgumentException se la lista vuota
     * @throws NullPointerException     se almeno prodotto della lista &egrave; null
     */
    public ComandaRistorazione(Ombrellone o, Cliente c, ArrayList<Prodotto> prodottiOrdinati) {
        super(o, c);
        if (prodottiOrdinati.isEmpty())
            throw new IllegalArgumentException("la lista è vuota");
        for (Prodotto p : prodottiOrdinati)
            if (p == null)
                throw new IllegalArgumentException("prodotto null");

        this.prodottiOrdinati = prodottiOrdinati;
        Satus = Status.daElaborare;
    }

    /**
     * @return la lista dei prodotti della comanda
     */
    public ArrayList<Prodotto> getProdottiOrdinati() {
        return this.prodottiOrdinati;
    }

    @Override
    public float calcolaConto() {
        float importo = 0;
        for (Prodotto p : prodottiOrdinati)
            importo += p.getPrezzo();
        return importo;
    }

    @Override
    public void chiudiComanda() {
        //chiedo pagamaneto al cliente
        super.getC().paga(this.calcolaConto());
        //invio la comnda al cassiere
        // todo : a chi inviare la comanda
    }

    /**
     * aggiunge un prodotto alla lista dei prodotti ordinati
     *
     * @param p prodotto da aggiungere
     * @return <code>true</code> se il prodotto &egrave; stato aggiunto,
     * altrimenti <code>False</code>
     */
    public boolean aggiungiProdotto(Prodotto p) {
        return this.prodottiOrdinati.add(p);
    }

    /**
     * permette di riuovere un prodotto dalla lista
     *
     * @param p prodotto da rimuovere
     * @return <code>True</code> se il prodotto &egrave; stato rimosso
     * altrimenti  <code>False</code>
     * @throws IllegalArgumentException se il prodotto non &egrave; contenuto nella
     *                                  lista dei prodotti;
     */
    public boolean rimuoviProdotto(Prodotto p) {
        if (this.prodottiOrdinati.contains(p)) {
            return this.prodottiOrdinati.remove(p);
        } else
            throw new IllegalArgumentException("il prodotto non è contenuto");
    }

}