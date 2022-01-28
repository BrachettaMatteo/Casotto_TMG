package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.ComandaBalneare;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagninoTest {
    @Test
    void liberaOmbrellone() {
        Casotto casotto = new Casotto();
        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);

        Bagnino bagnino = new Bagnino("Bagnino", "Bagnino", "Camerino", 1234567890, "Bagnino", "bagnino@bagnino.bagnino");

        Cliente CL1 = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");


        CL1.prenotaOmbrellone(casotto.getOmbrelloni().get(1), LocalDateTime.of(2022, Month.APRIL, 4, 15, 00, 00));
        bagnino.liberaOmbrellone(casotto.getOmbrelloni().get(1));

        assertNull(casotto.getOmbrelloni().get(1).getFine());
        assertTrue(casotto.getOmbrelloni().get(1).getDisponibilita());

    }

    @Test
    void aggiungiAttivita() {
        Casotto casotto = new Casotto();
        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);

        Bagnino bagnino = new Bagnino("Bagnino", "Bagnino", "Camerino", 1234567890, "Bagnino", "bagnino@bagnino.bagnino");

        Attivita a = new Attivita("beachVolley", 20, 4, LocalDateTime.of(2022, Month.APRIL, 4, 15, 00, 00), 5.0f);
        bagnino.aggiungiAttivita(a);
        assertEquals(a, casotto.getAttivita().get(0));

    }

    @Test
    void modificaAttivita() {
        Casotto casotto = new Casotto();

        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);

        Bagnino bagnino = new Bagnino("Bagnino", "Bagnino", "Camerino", 1234567890, "Bagnino", "bagnino@bagnino.bagnino");

        Attivita a = new Attivita("beachVolley", 20, 4, LocalDateTime.of(2022, Month.APRIL, 4, 15, 00, 00), 5.0f);
        bagnino.aggiungiAttivita(a);

        bagnino.modificaAttivita(a.getIdAttivita(), "BeachSoccer", a.getOrario(), a.getPostiMax(), a.getPostiMin());

        assertThrows(IllegalArgumentException.class, () ->
                bagnino.modificaAttivita(a.getIdAttivita(), "", a.getOrario(), a.getPostiMax(), a.getPostiMin()));

        assertThrows(IllegalArgumentException.class, () ->
                bagnino.modificaAttivita(a.getIdAttivita(), a.getNome(), LocalDateTime.of(2021, Month.JANUARY, 9, 15, 0, 0), a.getPostiMax(), a.getPostiMin()));

        assertThrows(IllegalArgumentException.class, () ->
                bagnino.modificaAttivita(a.getIdAttivita(), "BeachSoccer", a.getOrario(), 0, a.getPostiMin()));
        assertThrows(IllegalArgumentException.class, () ->
                bagnino.modificaAttivita(a.getIdAttivita(), "BeachSoccer", a.getOrario(), a.getPostiMax(), 0));


        assertEquals("BeachSoccer", casotto.getAttivita().get(0).getNome());

        assertEquals(a.getIdAttivita(), casotto.getAttivita().get(0).getIdAttivita());

    }

    @Test
    void aggiungiComanda() {
        Casotto c = new Casotto();

        Gestore G = new Gestore("Gestore", "Gestore", "Camerino", 1234567890, "Gestore", "Gestore@Gestore.Gestore");
        G.creaSpiaggia(10, 10);

        Cliente CL = new Cliente("Cliente", "Cliente", "Camerino", 1234567890, "Cliente", "Cliente@Cliente.Cliente");

        CL.prenotaOmbrellone(c.getOmbrelloni().get(1), LocalDateTime.of(2023, Month.JANUARY, 9, 15, 0, 0));
        CL.ricarica(100f);

        CL.prenotaOmbrellone(c.getOmbrelloni().get(1), LocalDateTime.of(2022, Month.FEBRUARY, 9, 15, 0, 0));
        Bagnino bagnino = new Bagnino("bagnino", "bagnino", "Camerino", 1234567890, "bagnino", "bagnino@bagnino.it");

        Materiale m1 = new Materiale("Lettino", 7.0f, "lettino lunghezza X larghezza");
        Materiale m2 = new Materiale("Sdraio", 7.0f, "sdraio singolo");

        c.aggiungiMateriale(m1);
        c.aggiungiMateriale(m2);

        Map<Materiale, Integer> ordine = new HashMap<>();
        ordine.put(m1, 1);

        ComandaBalneare comandaBalneare = new ComandaBalneare(c.getOmbrelloni().get(1), CL, ordine, LocalDateTime.of(2022, Month.FEBRUARY, 10, 15, 0, 0));

        //chiudo comanda
        comandaBalneare.chiudiComanda();
        assertTrue(bagnino.getListaOrdini().contains(comandaBalneare));

    }
}