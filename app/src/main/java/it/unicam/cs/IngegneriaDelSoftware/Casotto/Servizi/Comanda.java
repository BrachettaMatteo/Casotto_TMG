package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;

import java.util.UUID;

/**
 * rappresneta una comanda e le sue caratteristiche
 */
public abstract class Comanda {
    private static Casotto casotto;
    private Ombrellone o;
    private Cliente c;
    private UUID idComanda;

    /**
     * crea una nuova comanda
     *
     * @param o ombrelone, destinazione della comanda
     * @param c cliente, proprietario della comanda
     */
    public Comanda(Ombrellone o, Cliente c) {

        casotto = Casotto.getInstance();

        if (casotto.verificaPrenotazione(o, c)) {
            this.o = o;
            this.c = c;
            this.idComanda = UUID.randomUUID();
        } else
            throw new IllegalArgumentException("Prenotazione Ombrellone non eggettuata Errore");

    }

    /**
     * @return il cassotto
     */
    public static Casotto getCasotto() {
        return casotto;
    }

    /**
     * @return l'ombrellone della comanda
     */
    public Ombrellone getO() {
        return o;
    }

    /**
     * @return il cliente della comanda
     */
    public Cliente getC() {
        return c;
    }

    /**
     * @return l'indetificativo della comanda
     */
    public UUID getIdComanda() {
        return idComanda;
    }

    /**
     * permette di crere il conto tatake della comanda
     *
     * @return l'importo della comanda
     */
    public abstract float calcolaConto();

    /**
     * peemette di chiudere una comanda
     */
    public abstract void chiudiComanda();


}