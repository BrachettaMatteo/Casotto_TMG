import java.util.ArrayList;

public class Casotto {

    private Gestore g;

    private ArrayList<Ombrellone> ombrelloni;
    private ArrayList<Cliente> clienti;
    private ArrayList<Prenotazione> prenotazioni;

    public Casotto() {
        this.g = new Gestore();
    }

    /**
     * permette di impostare le tariffe degli ombrelloni per fila.
     *
     * @param tariffa cifra che corrisponde al costo dell'ombrellone nella fila inserita.
     * @param fila    numero che identifica la fila
     */
    public void setTariffe(float tariffa, int fila) {
        //todo controlli tariffe
        this.g.setTariffe(tariffa, fila);
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
        return -1;
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
     * @param cliente
     * @param ombrellone
     * @param comanda
     */
    public boolean prenotaOmbrellone(Cliente c ,Ombrellone o, int durata) {
        //controllo presenza ombrellon
            Ombrellone a = o;
             o.setDisponibilita(false);
            this.ombrelloni.set(this.ombrelloni.indexOf(a),o);

        return true;
    }

    /**
     * permette di accettare o meno la richiesta del servizio
     *
     * @param cliente
     * @param ombrellone
     * @param comanda
     */
    public boolean prenotaServizio(Cliente cliente, Ombrellone ombrellone, Comanda comanda) {
        // TODO - implement Casotto.prenotaServizio
        throw new UnsupportedOperationException();
    }

}