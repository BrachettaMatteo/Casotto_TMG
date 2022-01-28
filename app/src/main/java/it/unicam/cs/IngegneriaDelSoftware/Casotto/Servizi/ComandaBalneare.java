package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Bagnino;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;

import java.time.LocalDateTime;
import java.util.Map;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * Rappresenta una comanda Balneare ovvero la possibilità di prenotare materiali aggiuntivi all'ombrellone
 */
public class ComandaBalneare extends Comanda {

    private static Casotto casotto;
    private Map<Materiale, Integer> materiali;
    private LocalDateTime durata;

    /**
     * permette di creare una nuova comandaBalneare sapendo materiale e durata
     *
     * @param o      ombrellone
     * @param c      cliente
     * @param ordine mappa contente materiale e quantita da ordinare.
     * @param durata durata del tempo di affitto
     */
    public ComandaBalneare(Ombrellone o, Cliente c, Map<Materiale, Integer> ordine, LocalDateTime durata) {
        super(o, c);
        casotto = getCasotto();
        if (ordine.isEmpty())
            throw new IllegalArgumentException("La lista dei materiali è vuota");
        if (durata.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("la durata è errata");

        if (this.verificaMateriali(ordine)) {
            this.materiali = ordine;
            this.durata = durata;
        }


    }

    /**
     * permette di verificare se i materiali è disponibile nel Casotto.
     *
     * @param ordine mappa contenente il materiale da oridinare
     * @return true se il materiale &egrave; disponibile altrimenti flase.
     */
    private boolean verificaMateriali(Map<Materiale, Integer> ordine) {
        //controllo materiali
        for (Materiale m : ordine.keySet())
            if (casotto.getMagazzino().containsKey(m)) {
                if (casotto.getMagazzino().get(m) >= ordine.get(m)) {
                    return true;
                } else
                    throw new IllegalArgumentException("numero oggetti non disponibili");

            } else
                throw new IllegalArgumentException("materiale non contenuto");
        return false;
    }


    @Override
    public float calcolaConto() {
        float importo = 0;
        for (Materiale m : materiali.keySet())
            importo += m.getCosto() * this.convertiOreGiorni(HOURS.between(LocalDateTime.now(), this.durata));
        return importo;
    }

    /**
     * permette di determinari i giorni della prenotazione
     *
     * @param ore ore da convertire in giorni
     * @return il numero dei giorni se le ore sono minore di 24 conterà un giorno
     */
    private int convertiOreGiorni(float ore) {
        if (ore < 24 && ore > 0)
            return 1;
        else
            //ceil arrotonda
            return (int) Math.ceil(ore / 24);
    }

    @Override
    public void chiudiComanda() {

        //inoltro comanda al bagnino
        for (Dipendente d : casotto.getPersonale())
            if (d instanceof Bagnino) {
                //consegno comanda al bagnino
                ((Bagnino) d).aggiungiComanda(this);
                //scalo soldi dal conto
                this.getC().paga(this.calcolaConto());
                super.getO().setComposizione(materiali);
                Casotto.getInstance().aggiornaOmbrellone(super.getO());
                return;
            }
    }
    //!--------------DA-ELIMINARE------------!

    /**
     * permette di aggiungere un materiale alla lista dei materiali da ordinare
     *
     * @param m        materiale da oaggiungere
     * @param quantita numero di elementi da aggiungere
     * @throws IllegalArgumentException se la quantita disponibile &egrave;
     *                                  minore della quantit&agrave; richiesta
     * @throws IllegalArgumentException se il materiale non &egrave;
     *                                  contenuto in magazzino.
     * @throws NullPointerException     se il materiale &egrave; null.
     * @throws IllegalArgumentException se la quantit&agrave; minore di 1.
     */
    public void aggiungiMateriale(Materiale m, int quantita) {
        if (m == null)
            throw new NullPointerException("materiale null");
        if (quantita <= 0)
            throw new IllegalArgumentException("quantità minore di 1");
        if (casotto.getMagazzino().containsKey(m)) {
            if (casotto.getMagazzino().get(m) >= quantita) {
                this.materiali.put(m, quantita);
            } else
                throw new IllegalArgumentException("numero oggetti non disponibili");
        } else throw new IllegalArgumentException("materiale non contenuto");


    }


}