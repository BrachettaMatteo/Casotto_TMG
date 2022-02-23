package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Ristorazione;

import java.util.UUID;

/**
 * Permette di rappresentare il materiale e le sue caratteristiche
 */
public class Materiale {

    private final UUID idMateriale;
    private String nome;
    private float costo;
    private String desc;
    private int quantita;

    /**
     * permette di creare un nuovo materiale
     *
     * @param nome  nome del materiale
     * @param costo costo del materiale
     * @param desc  descrizione del materiale.
     * @throws IllegalArgumentException se il nome &egrave; errato.
     * @throws IllegalArgumentException se il prezzo &egrave; errato.
     * @throws IllegalArgumentException se la descrizione &egrave; errata.
     */
    public Materiale(String nome, float costo, String desc) {
        if (nome.isEmpty())
            throw new IllegalArgumentException("il nome non è corretto");
        if (costo < 0)
            throw new IllegalArgumentException("prezzo errato");
        if (desc.isEmpty())
            throw new IllegalArgumentException("descrizione errata");

        this.idMateriale = UUID.randomUUID();

        this.nome = nome;
        this.costo = costo;
        this.quantita = 1;
        this.desc = desc;
    }

    public Materiale(String idMateriale, String nome, float costo, int quantita) {
        this.idMateriale = UUID.fromString(idMateriale);
        this.nome = nome;
        this.costo = costo;
        this.quantita = quantita;
    }

    public Materiale(String nome, float costo, int quantita) {
        this.idMateriale= UUID.randomUUID();
        this.nome = nome;
        this.costo = costo;
        this.quantita = quantita;
    }
    //!-----------------GET----------------

    public int getQuantita(){
        return quantita;
    }

    /**
     * @return il nome del materiale
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return il costo del materiale
     */
    public float getCosto() {
        return this.costo;
    }

    /**
     * @return la descrizione del materiale
     */

    public String getDesc() {
        return this.desc;
    }

    /**
     * @return l'identificativo del materiale
     */

    public String getId() {
        return this.idMateriale.toString();
    }

    //!--------------------SET--------------
    public void aggiungiQuantit(int i) {
        quantita += i;
    }

    /**
     * @param nome nuovo nome del materiale
     * @throws IllegalArgumentException se il nome &egrave; errato
     */
    public void setNome(String nome) {
        if (nome.isEmpty())
            throw new IllegalArgumentException("il nome è errato");
        this.nome = nome;
    }

    /**
     * permette di aggiornare il costo del materiale
     *
     * @param costo nuovo costo del materiale
     * @throws IllegalArgumentException se il valore &egrave; minore di 0.
     */
    public void setCosto(float costo) {
        if (costo < 0)
            throw new IllegalArgumentException("il costo è minore di 0");
        this.costo = costo;
    }

    /**
     * permette di aggironare la descrizione del materiale
     *
     * @param desc nuova descrizione del materiale
     */
    public void setDesc(String desc) {
        if (desc.isEmpty())
            throw new IllegalArgumentException("la descrizione non è corretta");
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Materiale:\n" +
                "nome: " + nome + "\n" +
                "costo: " + costo +"\n" +
                "quantita: " + quantita ;
    }

}