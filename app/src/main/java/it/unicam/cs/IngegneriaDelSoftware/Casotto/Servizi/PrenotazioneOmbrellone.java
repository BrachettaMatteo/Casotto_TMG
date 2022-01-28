package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * permette di prenotare un ombrellone nel casotto
 */
public class PrenotazioneOmbrellone {

    private static Casotto casotto;
    private final UUID idPrenotazione;
    private Cliente c;
    private Ombrellone o;
    private LocalDateTime dataFine;

    /**
     * permette di creare una nuova prenotazione
     *
     * @param c        cliente della prenotazione
     * @param o        ombrellone della prenotazione
     * @param dataFine data di fine prenotazione
     * @throws IllegalArgumentException se il cliente non appartiene alla lista dei clienti del casotto
     * @throws IllegalArgumentException se l'ombrellone non appartiene al casotto
     * @throws IllegalArgumentException se la data è precedente alla data attauae.
     */
    public PrenotazioneOmbrellone(Cliente c, Ombrellone o, LocalDateTime dataFine) {
        casotto = Casotto.getInstance();

        if (!casotto.getClienti().contains(c))
            throw new IllegalArgumentException("cliente errato");
        if (!casotto.getOmbrelloni().contains(o))
            throw new IllegalArgumentException("ombrellone errato");
        if (dataFine.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("data erata");

        //controllo cliente
        this.c = c;
        this.o = o;
        this.dataFine = dataFine;
        this.idPrenotazione = UUID.randomUUID();

        this.prenotaOmbrellone(dataFine);

        //aggiungo la prenotazione al casotto
        casotto.aggiungiPrenotazione(this);

    }

    /**
     * Permette di prenotare un ombrellone
     *
     * @param dataFine data di fine prenotazione
     */
    private void prenotaOmbrellone(LocalDateTime dataFine) {
        //prenoto ombrellone
        this.o.setFine(dataFine);
        this.o.setDisponibilita(false);
        int index = casotto.getOmbrelloni().indexOf(o);
        casotto.getOmbrelloni().set(index, o);
    }


    //!----------GET------------!

    /**
     * @return l'identificativo della prenotazione dell'ombrellone
     */
    public String getIdComanda() {
        return this.idPrenotazione.toString();
    }

    /**
     * @return l'ombrellone della prenotazione
     */
    public Ombrellone getO() {
        return this.o;
    }

    /**
     * @return il cliente della prenotazione
     */
    public Cliente getC() {
        return this.c;
    }

    /**
     * @return la data di fine della prenotazione
     */
    public LocalDateTime getDataFine() {
        return dataFine;

    }

    //!----------SET------------!

    /**
     * imposta la data di fine della prenotazione dell'ombrellone
     *
     * @param dataFine nuova data di fine
     * @throws IllegalArgumentException se la data &egrave; precedente alla data attuale
     */

    public void setDataFine(LocalDateTime dataFine) {
        if (!dataFine.isBefore(LocalDateTime.now())) {
            this.prenotaOmbrellone(dataFine);
            this.dataFine = dataFine;
        }
        throw new IllegalArgumentException("data erata");
    }

    @Override
    public String toString() {
        return "PrenotazioneOmbrellone{" +
                "idPrenotazione=" + idPrenotazione +
                ", Cliente=" + c +
                ", Ombrellone=" + o +
                ", dataFine=" + dataFine +
                '}';
    }
}