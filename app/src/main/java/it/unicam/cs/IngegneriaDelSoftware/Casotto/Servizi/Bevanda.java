package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

/**
 * Rappresenta un Bevanda con le sue caratteristiche
 */
public class Bevanda extends Prodotto {

    private Formato formato;
    private int gradoAlcolico;

    /**
     * permette di creare una nuova bevanda
     *
     * @param nome          nome della bevanda
     * @param prezzo        prezzo della bevanda
     * @param descrizione   descrizione della bevanda
     * @param formato       formato della bevanda
     * @param gradoAlcolico grado alcolico della bevanda
     */
    public Bevanda(String nome, float prezzo, String descrizione, Formato formato, int gradoAlcolico) {
        super(nome, prezzo, descrizione);

        if (gradoAlcolico < 0)
            throw new IllegalArgumentException("il grado alcolico non puo essere negativo");

        this.formato = formato;
        this.gradoAlcolico = gradoAlcolico;
    }


    //!-----------Get-----------!

    /**
     * @return il formato della bevanda
     */
    public Formato getFormato() {
        return this.formato;
    }

    /**
     * @return il grado alcolico della bevanda
     */
    public int getGradoAlcolico() {
        return this.gradoAlcolico;
    }

    //!------------SET---------!

    /**
     * permette di impostare un nuvo formato della bevanda
     *
     * @param formato nuovo formato
     */
    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    /**
     * permette di cambiare il grado alcolico della bevanda
     *
     * @param gradoAlcolico nuovo grado alcolico
     */
    public void setGradoAlcolico(int gradoAlcolico) {
        this.gradoAlcolico = gradoAlcolico;
    }
}