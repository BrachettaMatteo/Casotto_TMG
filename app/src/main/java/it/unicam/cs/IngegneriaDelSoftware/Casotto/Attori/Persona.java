package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import java.util.UUID;

/**
 * Rappresenta una Persona e le sue caratteristiche
 */
public class Persona {

    private UUID id;
    private String nome;
    private String cognome;
    private String residenza;
    private String telefono;
    private String nomeUtente;
    private String email;

    /**
     * permette di creare una nuova persona
     *
     * @param nome       nome della persona
     * @param cognome    cognome della persona
     * @param residenza  residenza della persona
     * @param telefono   telefono  della persona
     * @param nomeUtente nome utente della persona
     * @param email      email della persona
     */
    public Persona(String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        if (nome.isEmpty() || cognome.isEmpty())
            throw new IllegalArgumentException("nominativo errato");
        if (residenza.isEmpty())
            throw new IllegalArgumentException("la residenza è vuota");
        if (telefono.length() != 10)
            throw new IllegalArgumentException("numero inserito non valido");
        if (!email.contains("@"))
            throw new IllegalArgumentException("email errata");
        if (!email.contains("."))
            throw new IllegalArgumentException("email errata");
        //TODO - controllare nome utente.
        this.id= UUID.randomUUID();
        this.nome = nome;
        this.cognome = cognome;
        this.residenza = residenza;
        this.telefono = telefono;
        this.nomeUtente = nomeUtente;
        this.email = email;
    }

    public Persona(String id, String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        if (nome.isEmpty() || cognome.isEmpty())
            throw new IllegalArgumentException("nominativo errato");
        if (residenza.isEmpty())
            throw new IllegalArgumentException("la residenza è vuota");
        if (telefono.length() != 10)
            throw new IllegalArgumentException("numero inserito non valido");
        if (!email.contains("@"))
            throw new IllegalArgumentException("email errata");
        if (!email.contains("."))
            throw new IllegalArgumentException("email errata");
        //TODO - controllare nome utente.
        this.id = UUID.fromString(id);
        this.nome = nome;
        this.cognome = cognome;
        this.residenza = residenza;
        this.telefono = telefono;
        this.nomeUtente = nomeUtente;
        this.email = email;

    }
    //!------------GET----------------!

    /**
     * @return il nome della persona
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return il cognome della persona
     */
    public String getCognome() {
        return this.cognome;
    }

    /**
     * @return la residenza della persona
     */
    public String getResidenza() {
        return this.residenza;
    }

    /**
     * @return il telefono della persona
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * @return l'email della persona
     */
    public String getEmail() {
        return this.email;
    }

    //!------------SET----------------!

    /**
     * permette di aggiornare la mail associato al dipendente
     *
     * @param email nuova mail
     * @throws IllegalArgumentException se la mail è errata
     * @throws NullPointerException     se la mail è null.
     * @throws IllegalArgumentException se la mail è identica a quella esistente.
     */
    public void setEmail(String email) {
        if (email == null)
            throw new NullPointerException("email null");
        if (email.equals(this.email))
            throw new IllegalArgumentException("questa mail è gia presente");
        if (email.contains("@"))
            if (email.contains("."))
                this.email = email;
        throw new IllegalArgumentException("email errata");
    }

    /**
     * permette di cambiare la residenza del dipendente
     *
     * @param residenza nuova residenza.
     * @throws IllegalArgumentException se la residenza è vuota.
     */
    public void setResidenza(String residenza) {
        if (residenza.isEmpty())
            throw new IllegalArgumentException("la residenza è vuota");
        this.residenza = residenza;

    }

    /**
     * permette di cambiare il numero di telefono
     *
     * @param telefono nuovo numero di telefono
     * @throws IllegalArgumentException se il numero è errato.
     */
    public void setTelefono(String telefono) {
        if (telefono.length() != 10)
            throw new IllegalArgumentException("nome inserito non valido");
        this.telefono = telefono;
    }

    public String getId() {
        return id.toString();
    }
}