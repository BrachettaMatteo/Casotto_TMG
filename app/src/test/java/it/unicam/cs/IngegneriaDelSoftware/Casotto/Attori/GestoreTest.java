package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GestoreTest {

    @Test
    void creaSpiaggia() {
        Casotto c = new Casotto();
        Gestore G = new Gestore("GG", "GG", "GG", 1234567890, "GG", "GG@GG.GG");

        assertThrows(IllegalArgumentException.class, () -> G.creaSpiaggia(-1, 9));
        assertThrows(IllegalArgumentException.class, () -> G.creaSpiaggia(9, -1));

        G.creaSpiaggia(2, 5);


        for (int fila = 1; fila <= 2; fila++) {
            for (int numero = 1; numero <= 5; numero++) {
                c.aggiungiOmbrellone(new Ombrellone(fila, 0.0f, numero));
            }
        }
        /*
        confronto ombrelloni in questo modo perchè ogni ombrellone possiede un indentificativo che
        viene assegnato alla creazione
        */
        for (int i = 0; i <= c.getOmbrelloni().size() - 1; i++) {
            assertEquals(c.getOmbrelloni().get(i).getFila(), G.getC().getOmbrelloni().get(i).getFila());
            assertEquals(c.getOmbrelloni().get(i).getNumero(), G.getC().getOmbrelloni().get(i).getNumero());
            assertEquals(c.getOmbrelloni().get(i).getTariffa(), G.getC().getOmbrelloni().get(i).getTariffa());
        }
    }

    @Test
    void setTariffe() {
        Gestore G = new Gestore("GG", "GG", "GG", 1234567890, "GG", "GG@GG.GG");
        G.creaSpiaggia(10, 3);
        G.setTariffe(8, 7.0f);
        assertEquals(7.0f, G.getC().getTariffa(8));
    }

    @Test
    void getC() {
        Casotto c = new Casotto();
        c.aggiungiOmbrellone(new Ombrellone(1, 2.0f, 2));
        Gestore G = new Gestore("GG", "GG", "GG", 1234567890, "GG", "GG@GG.GG");
        assertEquals(c, G.getC());
    }
}