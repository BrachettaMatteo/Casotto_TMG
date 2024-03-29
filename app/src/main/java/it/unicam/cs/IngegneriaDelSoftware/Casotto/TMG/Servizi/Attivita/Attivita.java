package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Attivita;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Cliente.Cliente;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Attivit&agrave; ludico sportive, sono delle Attivit&agrave; messe a disposizione per i clienti
 */
public class Attivita {

    private final UUID idAttivita;

    private String nome;
    /**
     * numero che indentifica i componenti massimi
     */
    private int postiMax;
    /**
     * identifica i posti minimi per far cominciare l'attivit&agrave;
     */
    private int postiMin;
    /**
     * identifica i componenti inscritti all'attivit&agrave;
     */
    private ArrayList<Cliente> componenti;

    private final float costo;

    private LocalDateTime Orario;

    private int partecipanti;

    /**
     * permette di creare una nuova Attivit&agrave;
     *
     * @param nome     nome della Attivit&agrave;
     * @param postiMax numero massimi di partecipanti per l'Attivit&agrave;
     * @param postiMin posti minimi di parteciapnti per l'Attivit&agrave;
     * @param inizio   data d'inizio della Attivit&agrave;
     * @param costo    costo della Attivit&agrave;
     */
    public Attivita(String nome, int postiMax, int postiMin, LocalDateTime inizio, float costo) {
        //controllo
        if (nome.length() <= 1)
            throw new IllegalArgumentException("nome errato");
        if (postiMax <= 0)
            throw new IllegalArgumentException("posti Max errati");
        if (postiMin <= 0)
            throw new IllegalArgumentException("posti Max errati");
        //se la data di inzio viene dopo la data attuale
        if (inizio.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("orario attivita non corretta");
        if (costo < 0)
            throw new IllegalArgumentException("costo minore di 0");

        this.idAttivita = UUID.randomUUID();
        this.nome = nome;
        this.postiMax = postiMax;
        this.postiMin = postiMin;
        this.componenti = new ArrayList<>();
        this.Orario = inizio;
        this.costo = costo;

    }

    public Attivita(String idAttivita, String nome, int postiMax, int postiMin, float costo, Timestamp orario) {
        this.idAttivita = UUID.fromString(idAttivita);
        this.nome = nome;
        this.postiMax = postiMax;
        this.postiMin = postiMin;
        this.costo = costo;
        if (orario != null)
            this.Orario = orario.toLocalDateTime();
        else
            this.Orario = null;
    }

    public Attivita(String idAttivita, String nome, int postiMax, int postiMin, float costo, Timestamp orario, int partecipanti) {
        this.idAttivita = UUID.fromString(idAttivita);
        this.nome = nome;
        this.postiMax = postiMax;
        this.postiMin = postiMin;
        this.costo = costo;
        this.partecipanti = partecipanti;
        if (orario != null)
            this.Orario = orario.toLocalDateTime();
        else
            this.Orario = null;
    }

//!----------------Get--------------!

    /**
     * @return il nome della Attivit&agrave;
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * @return il numero massimo di posti della Attivit&agrave;
     */

    public int getPostiMax() {
        return this.postiMax;
    }

    /**
     * @return il numero minimo di posti della Attivit&agrave;
     */
    public int getPostiMin() {
        return this.postiMin;
    }

    /**
     * @return l'identificativo della Attivit&agrave;
     */
    public String getIdAttivita() {
        return this.idAttivita.toString();
    }

    /**
     * @return la data dell'inizio Attivit&agrave;
     */
    public LocalDateTime getOrario() {
        return this.Orario;
    }


    /**
     * @return il costo dell'Attivit&agrave;
     */
    public float getCosto() {
        return costo;
    }

    public int getPartecipanti() {
        return this.componenti == null ? 0 : this.componenti.size();

    }

    //!---------------------------Set-----------------------------!

    /**
     * imposta un nuovo nome all'attivit&agrave;
     *
     * @param nome dell'attività.
     * @throws IllegalArgumentException se il nome è errato, ovvero la lunghezza del nome
     *                                  &egrave; minore uguale a 1.
     */
    public void setNome(String nome) {
        if (nome.length() <= 1)
            throw new IllegalArgumentException("nome errato");
        this.nome = nome;
    }

    /**
     * imposta il numero massimo dei posti per l'attivit&agrave;
     *
     * @param postiMax numero di posti massimi
     */
    public void setPostiMax(int postiMax) {
        this.postiMax = postiMax;
    }


    /**
     * imposta il numero minimo dei posti per l'attivit&agrave;
     *
     * @param postiMin numero di posti minimi
     */
    public void setPostiMin(int postiMin) {
        this.postiMin = postiMin;
    }

    /**
     * modifica l'orario di un'attività.
     *
     * @param orario orario dell'inizio dell'attivit&agrave;
     */
    public void setOrario(LocalDateTime orario) {
        if (Orario.isAfter(LocalDateTime.now()))
            this.Orario = orario;
        else throw new IllegalArgumentException("l'orario non è corretto");
    }


//!--------------------------SERVIZI-----------------------!

    /**
     * aggiunge un componente
     *
     * @param cliente cliente da aggiungere
     * @return true se il componente &egrave; stato aggiunto altrimenti false.
     * @throws NullPointerException il cliente inserito &egrave; null
     */
    public boolean aggiungiComponente(Cliente cliente) {
        //controllo
        if (cliente == null) throw new NullPointerException("il cliente inserito è nullo");

        if (this.componenti.size() < this.postiMax)
            if (cliente.paga(costo))
                return this.componenti.add(cliente);
        return false;
    }


    public String getId() {
        return this.idAttivita.toString();
    }

    @Override
    public String toString() {
        return "Attivita: \n" +
                "idAttivita:" + idAttivita.toString() + "\n" +
                "nome:" + nome + '\n' +
                "postiMax:" + postiMax + '\n' +
                "postiMin:" + postiMin + '\n' +
                "componenti:" + componenti + '\n' +
                "costo:" + costo + '\n' +
                "Orario: " + Orario;
    }
}