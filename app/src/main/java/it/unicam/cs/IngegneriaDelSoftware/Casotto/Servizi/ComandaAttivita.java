package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cassiere;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;

import java.util.ArrayList;

/**
 * Rappresenta una comanda per prenotare un'attivit&agrave;
 */
public class ComandaAttivita extends Comanda {


    private ArrayList<Attivita> attivita;

    /**
     * permette di creare una nuova comanda
     *
     * @param o        ombrellone della comanda
     * @param c        cliente della comanda
     * @param attivita attivita da prenotare
     */
    public ComandaAttivita(Ombrellone o, Cliente c, ArrayList<Attivita> attivita) {
        super(o, c);
        this.attivita = attivita;
    }

    /**
     * permette di aggiungere un'attivita alla lista da prenotare
     *
     * @param a attività da aggiungere alla comanda
     */
    public void aggiungiAttivita(Attivita a) {
        if (!Casotto.getInstance().getAttivita().contains(a))
            throw new IllegalArgumentException("attività non contenuta");
        this.attivita.add(a);
    }

    @Override
    public float calcolaConto() {
        float importo = 0;
        for (Attivita a : attivita)
            importo += a.getCosto();
        return importo;
    }

    @Override
    public void chiudiComanda() {
    }
}