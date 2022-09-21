package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione;

import java.util.ArrayList;

/**
 * Rappresenta il cibo e le sue caratteristiche
 */
public class Cibo extends Prodotto {

    private final ArrayList<String> ingredienti;
    private final ArrayList<String> allergeni;

    /**
     * Permette di creare un nuovo cibo
     *
     * @param nome        nome del cibo
     * @param prezzo      prezzo del cibo
     * @param descrizione descrizione del cibo
     * @param ingredienti lista di ingredieti del cibo
     * @param allergeni   lista di allergeni del cibo
     * @throws NullPointerException     se gli ingredienti sono null
     * @throws NullPointerException     se gli allergeni sono null
     * @throws IllegalArgumentException se l'ingredienete è errato
     * @throws IllegalArgumentException se l'allergene è errato
     */
    public Cibo(String nome, float prezzo, String descrizione, ArrayList<String> ingredienti, ArrayList<String> allergeni) {
        super(nome, prezzo, descrizione);
        //controllo
        if (ingredienti == null)
            throw new NullPointerException("ingredienti null");
        if (allergeni == null)
            throw new NullPointerException("allergeni null");
        if (allergeni.isEmpty())
            throw new NullPointerException("allergeni vuoti");
        if (ingredienti.isEmpty())
            throw new IllegalArgumentException("ingredienti vuoti");


        this.ingredienti = ingredienti;
        this.allergeni = allergeni;
    }

    /**
     * permette di aggiungere un allergene
     *
     * @param allergene da aggiungere
     * @throws IllegalArgumentException l'allergene &egrave; vuoto;
     * @throws IllegalArgumentException l'allergene &egrave; contenuto;
     */
    public void aggiungiAllergene(String allergene) {
        if (allergene.isEmpty())
            throw new IllegalArgumentException("allergne vuoto");
        if (this.allergeni.contains(allergene))
            throw new IllegalArgumentException("l'allrgene è contenuto");
        this.allergeni.add(allergene);
    }

    /**
     * permette di aggiungere un allergene
     *
     * @param ingrediente da aggiungere
     * @throws IllegalArgumentException se l'ingrediente &egrave; vuoto;
     * @throws IllegalArgumentException se l'ingrediente &egrave; contenuto;
     */
    public void aggiungiIngredienti(String ingrediente) {
        if (ingrediente.isEmpty())
            throw new IllegalArgumentException("allergne vuoto");
        if (this.ingredienti.contains(ingrediente))
            throw new IllegalArgumentException("l'allrgene è contenuto");
        this.ingredienti.add(ingrediente);
    }


}