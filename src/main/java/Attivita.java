import java.util.ArrayList;

public class Attivita {

    private int idAttivita;
    private String nome;
    /**
     * Numero che identifica i componenti massimi.
     */
    private int postiMax;

    /**
     * Numero che identifica i posti minimi per avviare l'attività.
     */
    private int postiMin;

    /**
     * Identifica i componenti iscritti all'attività.
     */
    private ArrayList<Cliente> componenti;

    /**
     * Costo dell'attività corrispondente.
     */
    private float costo;

    /**
     * Descrizione dell'attività.
     */
    private String descrizione;

    /**
     * Orario in cui è disponibile usufruire dell'attività.
     */
//todo - QUESTION non sarebbe meglio impostare tipo date ed inserire la data di inzio?
    private int oraAttivita;


    public Attivita(int idAttivita, String nome, int postiMax, int postiMin, float costo, String descrizione, int oraAttivita) {
        //controllo
        if (idAttivita < 0)
            throw new IllegalArgumentException("id non corretto");
        if (nome.length() <= 1)
            throw new IllegalArgumentException("nome errato");
        if (nome == null)
            throw new NullPointerException("il nome è null");
        if (postiMax <= 0)
            throw new IllegalArgumentException("posti Max errati");
        if (postiMin <= 0)
            throw new IllegalArgumentException("posti Max errati");
        if (descrizione.length() <= 2)
            throw new IllegalArgumentException("posti min errati");
        if (costo < 0)
            //<0 perchè un attività puo essere anche gratis
            throw new IllegalArgumentException("costo errato");
        if (descrizione == null)
            throw new NullPointerException("escrizione null");
        if (descrizione.length() <= 1)
            throw new IllegalArgumentException("descrizione non corretta");
        if (oraAttivita <= 0)
            throw new IllegalArgumentException("orario attivita non corretta");
        this.idAttivita = idAttivita;
        this.nome = nome;
        this.postiMax = postiMax;
        this.postiMin = postiMin;
        this.componenti = new ArrayList<Cliente>();
        this.costo = costo;
        this.descrizione = descrizione;
        this.oraAttivita = oraAttivita;
    }

    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome ...;
     */
    public void setNome(String nome) {
        if (nome.length() <= 1)
            throw new IllegalArgumentException("nome errato");
        if (nome == null)
            throw new NullPointerException("il nome è null");
        this.nome = nome;
    }

    public int getPostiMax() {
        return this.postiMax;
    }

    public int getPostiMin() {
        return this.postiMin;
    }

    public float getCosto() {
        return this.costo;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    /**
     * @param descrizione ...;
     */
    public void setDescrizione(String descrizione) {
        //controllo
        if (descrizione == null)
            throw new NullPointerException("escrizione null");
        if (descrizione.length() <= 1)
            throw new IllegalArgumentException("descrizione non corretta");
        this.descrizione = descrizione;
    }

    public int getIdAttivita() {
        return this.idAttivita;
    }

    /**
     * @return null se non è presente neanche un componente altrimenti la lista dei componenti;
     */
    public ArrayList<Cliente> getComponenti() {
        if (this.componenti.isEmpty())
            return null;
        return this.componenti;
    }

    /**
     * aggiunge un componente
     *
     * @param cliente ...;
     */
    public boolean aggiungiComponente(Cliente cliente) {
        //controllo
        if (cliente == null) throw new NullPointerException("il cliente inserito è nullo");
        return this.componenti.add(cliente);
    }

    public int getOraAttivita() {
        return this.oraAttivita;
    }

}
