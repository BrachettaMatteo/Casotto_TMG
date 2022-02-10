package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Gestore;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PrenotazioneOmbrelloneTest {
    @Test
    public void Prenotazione() {
        Casotto c = new Casotto();
        Gestore G = new Gestore("GG", "GG", "GG", "1234567890", "GG", "GG@GG.GG");

        Cliente CL1 = new Cliente("uno", "uno", "Camerino", "1234567890", "uno", "uno@uno.uno");
        Cliente CL2 = new Cliente("due", "due", "Camerino", "1234567890", "due", "due@due.due");

        Ombrellone O1 = new Ombrellone(1, 7.0f, 2);
        Ombrellone O2 = new Ombrellone(2, 7.0f, 1);

        LocalDateTime fine = LocalDateTime.of(2022, 1, 30, 15, 0, 0);

        assertThrows(IllegalArgumentException.class, () -> new PrenotazioneOmbrellone(CL1, O1, fine));

        c.aggiungiCLiente(CL1);
        c.aggiungiCLiente(CL2);

        assertThrows(IllegalArgumentException.class, () -> new PrenotazioneOmbrellone(CL1, O1, fine));

        c.aggiungiOmbrellone(O1);
        c.aggiungiOmbrellone(O2);

        LocalDateTime fineError = LocalDateTime.of(2022, 1, 25, 15, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> new PrenotazioneOmbrellone(CL1, O1, fineError));


        PrenotazioneOmbrellone p = new PrenotazioneOmbrellone(CL1, O1, fine);
        assertFalse(c.getOmbrelloni().get(c.getOmbrelloni().indexOf(O1)).getDisponibilita());
        assertEquals(fine, c.getOmbrelloni().get(c.getOmbrelloni().indexOf(O1)).getFine());

        assertEquals(p, c.getPrenotazioni().get(0));

    }

}