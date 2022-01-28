package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Menu;

import java.util.ArrayList;

/**
 * Rappresenta il gestore dello chalet
 */
public class Gestore extends Persona {

    /**
     * lista degli chalet posseduti da un gestore
     */
    private ArrayList<Casotto> listaChalet;
    private Casotto c;

    /**
     * Permette di creare un nuov gestore
     *
     * @param nome       nome del gestore
     * @param cognome    cognome del gestore
     * @param residenza  residenza del gestore
     * @param telefono   telefono del gestore
     * @param nomeUtente nomeUtente del gestore
     * @param email      email del gestore
     */
    public Gestore(String nome, String cognome, String residenza, int telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email);
        this.listaChalet = new ArrayList<>();
        this.c = Casotto.getInstance();
    }

    /**
     * permette di creare una spiaggia.
     *
     * @param file                    numero di file della spiaggia
     * @param numeroOmbrelloniPerFila numero di ombrelloni per fila di una spiaggia.
     * @throws IllegalArgumentException se il numero delle file o il numero degli ombrelloni per fila
     *                                  &egrave; minore di 0;
     */
    public void creaSpiaggia(int file, int numeroOmbrelloniPerFila) {
        if (file < 0 || numeroOmbrelloniPerFila < 0)
            throw new IllegalArgumentException("il numero inserito è minore di 0");
        ArrayList<Ombrellone> ombrelloni = new ArrayList<>();
        for (int fila = 1; fila <= file; fila++) {
            for (int numero = 1; numero <= numeroOmbrelloniPerFila; numero++) {
                this.c.aggiungiOmbrellone(new Ombrellone(fila, 0.0f, numero));
            }
        }
    }

    /**
     * permette di impostare una tariffa per una fila di ombrelloni
     *
     * @param fila  fila di ombrelloni a cui aggiungere la tariffa.
     * @param costo costo della fila.
     */
    public void setTariffe(int fila, float costo) {

        for (Ombrellone o : this.c.getOmbrelloni()) {
            if (o.getFila() == fila)
                o.setTariffa(costo);
        }
    }

    /**
     * aggiunge un menu al casotto
     *
     * @param m menu
     */
    public void aggiungiMenu(Menu m) {

        // TODO - implement Gestore.aggiungiMenu
        throw new UnsupportedOperationException();
    }

    /**
     * @return il casooto
     */
    public Casotto getC() {
        return c;
    }
}