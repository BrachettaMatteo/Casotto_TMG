package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione;

import java.util.UUID;

/**
 * Rappresenta un prodotto e le sue caratteristiche
 */
public class Prodotto {

    private String nome;
    private final float prezzo;
    private final String descrizione;

    private final UUID idProdotto;
    private int quantita;

    /**
     * @param nome        nome del prodotto
     * @param prezzo      presso del prodpttp
     * @param descrizione descrizione del prodotto
     * @throws IllegalArgumentException se il nome &egrave; vuoto.
     * @throws IllegalArgumentException se il costo  &egrave; minore di 0;
     * @throws IllegalArgumentException se la descrizione &egrave; minore di 0;
     */
    public Prodotto(String nome, float prezzo, String descrizione) {
        if (nome.isEmpty())
            throw new IllegalArgumentException("il nome non è corretto");
        if (prezzo < 0)
            throw new IllegalArgumentException("presso miunore di 0");
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;

        this.idProdotto = UUID.randomUUID();
    }

    public Prodotto(String id, String nome, float prezzo, String descrizione) {

        this.idProdotto = UUID.fromString(id);
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;

    }

    /**
     * @return il nome del prodotto
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * permette di modificare il nome
     *
     * @param nome nuovo nome
     * @throws IllegalArgumentException se il nome è vuoto
     */
    public void setNome(String nome) {
        if (this.nome.isEmpty())
            throw new IllegalArgumentException("nome vuoto");
        this.nome = nome;
    }

    /**
     * @return il prezzo del prodotto
     */
    public float getPrezzo() {
        return this.prezzo;
    }

    /**
     * restituisce la descrizione di un prodotto
     *
     * @return descrizione del prodotto.
     */
    public String getDescrizione() {
        return this.descrizione;
    }


    public String getIdProdotto() {
        return idProdotto.toString();
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getQuantita() {
        return this.quantita;
    }

    @Override
    public String toString() {
        return
                "nome: " + nome +"\n"+
                        "prezzo:" + prezzo + " €" +"\n"+
                        "descrizione=: " + descrizione +"\n"+
                        "idProdotto: " + idProdotto +"\n"+
                        "quantita: " + quantita;
    }
}