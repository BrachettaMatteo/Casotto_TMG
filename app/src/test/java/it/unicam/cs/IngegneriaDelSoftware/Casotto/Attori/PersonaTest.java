package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonaTest {
    @Test
    public void personaTest() {

        assertThrows(IllegalArgumentException.class, () -> new Persona("", "GG", "Camerino", "1234567890", "Person", "GG@GG.it"));
        assertThrows(IllegalArgumentException.class, () -> new Persona("GG", "", "Camerino", "1234567890", "Person", "GG@GG.it"));
        assertThrows(IllegalArgumentException.class, () -> new Persona("GG", "GG", "", "1234567890", "Person", "GG@GG.it"));
        assertThrows(IllegalArgumentException.class, () -> new Persona("GG", "GG", "Camerino", "34567890", "Person", "GG@GG.it"));
        //TODO : controllare Username
        assertThrows(IllegalArgumentException.class, () -> new Persona("GG", "GG", "Camerino", "1234567890", "Person", "GG.it"));
        assertThrows(IllegalArgumentException.class, () -> new Persona("GG", "GG", "Camerino", "1234567890", "Person", "GG@GG"));

        Persona p = new Persona("GG", "GG", "Camerino", "1234567890", "Person", "GG@GG.it");
        assertEquals("GG", p.getCognome());
        assertEquals("GG", p.getNome());
        assertEquals("Camerino", p.getResidenza());
        assertEquals("1234567890", p.getTelefono());
        assertEquals("GG@GG.it", p.getEmail());


    }

}