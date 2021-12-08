import java.util.Date;

public class Cliente {

    private String nome;
    private String cognome;
    private int id;

    public Cliente(String nome, String cognome) {

        if (nome.length() <= 1)
            throw new IllegalArgumentException("nome errato");
        if (nome == null)
            throw new NullPointerException("il nome è null");

        if (cognome.length() <= 1)
            throw new IllegalArgumentException("cognome errato");
        if (cognome == null)
            throw new NullPointerException("il cognome è null");
        this.nome = nome;
        this.cognome = cognome;
        //todo creare generatore id?
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public int getId() {
        return this.id;
    }

    /**
     * richiesta prenotazione ombrellone, restituira true se è stata accettata altrimenti false.
     *
     * @param ombrellone ...;
     * @param fine     indica la durat della prenotazione
     */
    public void richiestaPrenotazioneOmbrellone(Ombrellone ombrellone, Date fine, Casotto c) {
        c.prenotaOmbrellone(this,ombrellone,fine);
    }

    /**
     * richiesta prenotazione servizio, restituira true se è stata accettata altrimenti false.
     *
     * @param comanda ...;
     */
    public boolean richiestaPrenotazioneServizio(Comanda comanda) {
        // TODO - Rivedere
        return false;
    }

}