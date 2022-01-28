package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Bevanda;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void prenotaOmbrellone() {
        Casotto c = new Casotto();
        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);

        Cliente CL1 = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");
        CL1.ricarica(100f);
        CL1.prenotaOmbrellone(c.getOmbrelloni().get(1), LocalDateTime.of(2022, Month.APRIL, 4, 15, 00, 00));

        assertEquals(1, c.getPrenotazioni().size());

        assertEquals(CL1, c.getPrenotazioni().get(0).getC());

    }

    @Test
    void prenotaServizioBalneare() {
        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);
        Bagnino bagnino = new Bagnino("Bagnino", "Bagnino", "Camerino", 1234567890, "Bagnino", "bagnino@bagnino.bagnino");
        Cliente CL1 = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");
        CL1.ricarica(1000f);
        CL1.prenotaOmbrellone(G.getC().getOmbrelloni().get(1), LocalDateTime.of(2022, Month.APRIL, 4, 15, 0, 0));

        Materiale lettino = new Materiale("lettino", 7, "lettino h x w");
        Materiale sdraio = new Materiale("sdraio", 7, "sdraio h x w");

        Casotto.getInstance().aggiungiMateriale(lettino);
        Casotto.getInstance().aggiungiMateriale(sdraio);

        CL1.prenotaServizioBalneare(lettino, LocalDateTime.of(2022, Month.MARCH, 4, 15, 0, 0), G.getC().getOmbrelloni().get(1));

        assertEquals(CL1, bagnino.getListaOrdini().get(0).getC());
    }

    @Test
    void prenotaSeviziBalneare() {
        Casotto c = new Casotto();
        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);
        Bagnino bagnino = new Bagnino("Bagnino", "Bagnino", "Camerino", 1234567890, "Bagnino", "bagnino@bagnino.bagnino");
        Cliente CL1 = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");
        CL1.ricarica(1000f);
        CL1.prenotaOmbrellone(G.getC().getOmbrelloni().get(1), LocalDateTime.of(2022, Month.APRIL, 4, 15, 0, 0));

        Materiale lettino = new Materiale("lettino", 7, "lettino h x w");
        Materiale sdraio = new Materiale("sdraio", 7, "sdraio h x w");

        for (int i = 0; i < 5; i++) {
            Casotto.getInstance().aggiungiMateriale(lettino);
            Casotto.getInstance().aggiungiMateriale(sdraio);
        }

        HashMap<Materiale, Integer> ordine = new HashMap<>();
        ordine.put(lettino, 3);
        ordine.put(sdraio, 4);

        CL1.prenotaSeviziBalneare(ordine, LocalDateTime.of(2022, Month.MARCH, 4, 15, 0, 0), G.getC().getOmbrelloni().get(1));

        assertEquals(CL1, bagnino.getListaOrdini().get(0).getC());
    }

    @Test
    void ricarica() {
        Cliente CL1 = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");
        CL1.ricarica(100f);

        assertEquals(100f, CL1.getSaldo());
    }

    @Test
    void paga() {
        Cliente CL1 = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");
        assertThrows(IllegalArgumentException.class, () -> CL1.paga(100f));

        CL1.ricarica(100f);
        CL1.paga(50f);

        assertEquals(50f, CL1.getSaldo());
    }

    @Test
    void prenotaAttivita() {
        Casotto c = new Casotto();
        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);
        Cliente CL1 = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");
        CL1.ricarica(10f);
        Attivita at = new Attivita("Saluto al sole", 1, 100, LocalDateTime.of(2022, Month.FEBRUARY, 25, 6, 0, 0), 1f);
        c.aggiungiAttivita(at);

        CL1.prenotaAttivita(at);

        assertTrue(c.getAttivita().get(0).getComponenti().contains(CL1));
    }
}