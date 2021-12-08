import java.util.ArrayList;

public class Bagnino extends Dipendenti {
    /**
     * libera un ombrellone
     * @param ombrellone ...;
     * @param c ...;
     */
    public void liberaOmbrellone(Ombrellone ombrellone, Casotto c){
        ArrayList<Ombrellone> ombrelloni = new ArrayList<>(c.getOmbrelloni());
        for(Ombrellone ombre : ombrelloni ){
            if (ombre == ombrellone) {
                ombre.setFine(null);
                ombre.setDisponibilita(true);
            }
        }
    }
}
