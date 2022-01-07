import java.util.ArrayList;
import java.util.Date;

public class Casotto {

    private ArrayList<Ombrellone> ombrelloni;
    private ArrayList<Cliente> clienti;
    private ArrayList<Prenotazione> prenotazioni;

    /**
     * lista dei materiali presenti nel casotto
     */
    private ArrayList<Materiale> listaMateriali;
    /**
     * lista delle attivita presenti nel casotto
     */
    private ArrayList<Attivita> listaAttività;

    /**
     * crea il casotto con i suoi ombrelloni
     *
     * @param file           ...;
     * @param ombrelloniFile ...;
     */
    public Casotto(int file, int ombrelloniFile) {
        //todo controllo parametri

        this.ombrelloni = new ArrayList<>();
        this.clienti = new ArrayList<>();
        for (int f = 0; f <= file; f++) {
            for (int o = 0; o <= ombrelloniFile; o++) {
                this.ombrelloni.add(new Ombrellone(f, 0, o));
            }
        }
    }


    /**
     * restituisce il prezzo che corrisponde alla fila inserita
     *
     * @param fila numero che corrisponde alla fila
     */
    public float getTariffa(int fila) {
        //todo controllo fila
        for (Ombrellone o : this.ombrelloni) {
            if (o.getFila() == fila)
                return o.getTariffa();
        }
        throw new IllegalArgumentException("la fila non esite");
    }

    public ArrayList<Ombrellone> getOmbrelloni() {
        return this.ombrelloni;
    }

    /**
     * @param Ombrelloni
     */
    public void setOmbrelloni(ArrayList<Ombrellone> Ombrelloni) {
        this.ombrelloni = Ombrelloni;
    }

    public ArrayList<Cliente> getClienti() {
        return this.clienti;
    }

    /**
     * @param Clienti
     */
    public void setClienti(ArrayList<Cliente> Clienti) {
        this.clienti = Clienti;
    }

    /**
     * permette di accetare o meno la richiesta di prenotazione .
     *
     * @param c    ...;
     * @param o    ...;
     * @param fine ...;
     */
    public void prenotaOmbrellone(Cliente c, Ombrellone o, Date fine) {
        //todo controllare parametri

        Ombrellone a = o;
        o.setDisponibilita(false);
        o.setFine(fine);
        this.ombrelloni.set(this.ombrelloni.indexOf(a), o);
        //todo trovare un modo per memorizzare tutte le prenotazioni
    }

    /**
     * permette di accettare o meno la richiesta del servizio
     *
     * @param cliente    ...;
     * @param ombrellone ...;
     * @param comanda    ...;
     */
    public void prenotaServizio(Cliente cliente, Ombrellone ombrellone, Comanda comanda) {
        // TODO implementare
    }

}