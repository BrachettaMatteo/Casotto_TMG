import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestoreTest {

    @Test
    void setTariffe() {
        Casotto c = new Casotto(4,10);
        Gestore g = new Gestore();
        int fila= 2;
        float tariffa = 9.0f;
        //creo l'out
        ArrayList<Ombrellone> omobrelloni = new ArrayList<>(c.getOmbrelloni());
        for(Ombrellone ombrellone : omobrelloni){
            if (ombrellone.getFila()== fila){
                ombrellone.setTariffa(tariffa);
            }
        }
        g.setTariffe(tariffa,fila,c);
       assertEquals(omobrelloni, c.getOmbrelloni());

    }
}