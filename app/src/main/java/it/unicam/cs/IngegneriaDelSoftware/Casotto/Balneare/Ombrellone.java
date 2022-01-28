package it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Rappresenta l'ombrellone e le sue caratteristiche
 */
public class Ombrellone {
    private final UUID id;
    private int fila;
    private float tariffa;
    private Map<Materiale, Integer> composizione;
    private String QRCode;
    private int numero;
    private boolean disponibilita;
    private LocalDateTime fine;

    /**
     * permette di creare un nuovo ombrellone.
     *
     * @param fila    fila dell'ombrellone
     * @param tariffa tariffa dell'ombrellone
     * @param numero  numero dell'ombrellone
     * @throws IllegalArgumentException se i valori sono minori di o
     */
    public Ombrellone(int fila, float tariffa, int numero) {
        if (fila < 0 || tariffa < 0 || numero < 0)
            throw new IllegalArgumentException("i valori sono errati");

        this.id = UUID.randomUUID();
        this.fila = fila;
        this.tariffa = tariffa;
        this.numero = numero;
        this.disponibilita = true;
        this.composizione = new HashMap<>();
        this.fine = null;
    }

    //!-----------------GET-------------------!

    /**
     * @return la fila dell'ombrellone
     */
    public int getFila() {
        return this.fila;
    }

    /**
     * @return la tariffa dell'ombrellone
     */

    public float getTariffa() {
        return this.tariffa;
    }

    /**
     * @return il QRCode dell'ombrellone
     */
    public String getQRCode() {
        // TODO - implement Ombrellone.getQRCode
        throw new UnsupportedOperationException();
    }

    /**
     * @return il numero dell'ombrellone
     */
    public int getNumero() {
        return this.numero;
    }


    /**
     * permtte di impostre la fila dell'ombrellone
     *
     * @param fila nuova fila dell'ombrellone
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * @return <code>true</code> se l'ombrellone &egrave; diponibile per la prenotazione,
     * altrimenti <code>false</code>
     */

    public boolean getDisponibilita() {
        return this.disponibilita;
    }

    /**
     * @return la data di fine della prenotazione dell'ombrellone
     */
    public LocalDateTime getFine() {
        return this.fine;
    }

    /**
     * @return l'identificativo dell'ombrellone
     */
    public String getId() {
        return id.toString();
    }
    //!------------SET----------------!

    /**
     * permette di impostare la tariffa dell'ombrellone
     *
     * @param tariffa nuova tariffa
     * @throws IllegalArgumentException se la tariffa &egrave; minore di 0.
     */
    public void setTariffa(float tariffa) {
        if (tariffa < 0)
            throw new IllegalArgumentException("la tariffa è minore di 0");
        this.tariffa = tariffa;
    }


    /**
     * permette di impostare un nuovo numero dell'ombrellone
     *
     * @param numero nuovo numero dell'ombrellone
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }


    /**
     * permette di impostare la disponibilit&agrave; di un ombrellone
     *
     * @param disponibilita nuova disponibilit&agrave;
     */
    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    /**
     * permette di aggiornare la composizione, <b>elimina la precedente composizione</b>
     *
     * @param composizione nuova composizione
     */
    public void setComposizione(Map<Materiale, Integer> composizione) {
        this.composizione = composizione;
    }

    /**
     * permette d'impostare la nuova data di fine
     *
     * @param fine nuova data di fine
     */
    public void setFine(LocalDateTime fine) {
        if (fine.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("data Errata");
        this.fine = fine;
    }

    //!--------SERVIZI-----------!

    /**
     * aggiunge elemento alla lista che contiene tutto il materiale dell'ombrellone
     *
     * @param m materiale da aggiungere
     * @return <code>true</code> se il materiale &egrave; stato aggiunto altrimenti <code>false</code>
     */
    public boolean aggiungiAllaComposizione(Materiale m) {
        if (m == null)
            throw new IllegalArgumentException("Materiale null");
        if (this.composizione.containsKey(m))
            return this.composizione.replace(m, this.composizione.get(m), this.composizione.get(m) + 1);
        return false;
    }


    @Override
    public String toString() {
        return "Ombrellone{" +
                "fila=" + fila +
                ", numero=" + numero +
                ",tariffa=" + tariffa +
                '}';
    }

    /*
     * due ombrelloni sono uguali se hanno lo stesso id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ombrellone that = (Ombrellone) o;
        return Objects.equals(id, that.id);
    }

    /**
     * permette di liberare un ombrellone
     */
    public void libera() {
        this.fine = null;
        this.composizione = new HashMap<>();
        this.disponibilita = true;
    }

}