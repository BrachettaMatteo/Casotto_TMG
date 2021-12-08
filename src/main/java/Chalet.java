import java.util.Date;

public class Chalet {

    private String nome;
    private String indirizzo;
    private int telefono;
    private Date orarioApertura;

    public Chalet(String nome, String indirizzo, int telefono, Date orarioApertura) {
        if (nome.length() <= 1)
            throw new IllegalArgumentException("nome errato");
        if (nome == null)
            throw new NullPointerException("il nome è null");
        if (indirizzo.length() <= 1)
            throw new IllegalArgumentException("indirizzo errato");
        if (indirizzo == null)
            throw new NullPointerException("indirizzo null");

        if (telefono < 1_000_000_000)
            //il numero di telefono ha 10 numeri, e suppongo che nessun numero inizi con 1....
            throw new IllegalArgumentException("telefono errato");
        //todo creare controllo  orarioApertura;

        this.nome = nome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.orarioApertura = orarioApertura;
    }

    public String getNome() {
        return this.nome;
    }

    /**
     * @param nome ...;
     */
    public void setNome(String nome) {
        //controllo
        if (nome.length() <= 1)
            throw new IllegalArgumentException("nome errato");
        if (nome == null)
            throw new NullPointerException("il nome è null");
        this.nome = nome;
    }

    public String getIndirizzo() {
        return this.indirizzo;
    }

    /**
     * @param indirizzo ...;
     */
    public void setIndirizzo(String indirizzo) {
        //controllo
        if (indirizzo.length() <= 1)
            throw new IllegalArgumentException("indirizzo errato");
        if (indirizzo == null)
            throw new NullPointerException("indirizzo null");
        this.indirizzo = indirizzo;
    }

    public int getTelefono() {
        return this.telefono;
    }

    /**
     * @param telefono ...;
     */
    public void setTelefono(int telefono) {
        if (telefono < 1_000_000_000)
            throw new IllegalArgumentException("telefono errato");
        this.telefono = telefono;
    }

    public Date getOrarioApertura() {
        return this.orarioApertura;
    }

    /**
     * @param orarioApertura ...;
     */
    public void setOrarioApertura(Date orarioApertura) {
        //todo inserire controllo orarioApertura
        this.orarioApertura = orarioApertura;
    }

}