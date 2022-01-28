package it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.PrenotazioneOmbrellone;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Rappresenta un Casotto, ovvero una digitalizzazione di uno chalet
 */
public class Casotto {

    private static Casotto instance;


    private ArrayList<Dipendente> personale;

    private ArrayList<Ombrellone> ombrelloni;

    private ArrayList<Cliente> clienti;

    private ArrayList<PrenotazioneOmbrellone> prenotazioni;
    //mappa cosi si puo tenere traccia della quantita;
    private Map<Materiale, Integer> magazzino;

    private ArrayList<Attivita> attivita;


    //!-----------costruttore & instance----------!

    /**
     * Permette di creare un nuovo Casotto
     */
    public Casotto() {
        this.ombrelloni = new ArrayList<>();
        this.clienti = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
        this.magazzino = new HashMap<>();
        this.attivita = new ArrayList<>();
        this.personale = new ArrayList<>();

        instance = this;
    }

    /**
     * @return il casotto
     */
    public static Casotto getInstance() {
        if (instance == null)
            new Casotto();
        return instance;
    }

    //!------------------------GET----------------------!

    /**
     * @return La lista dei clienti del casotto
     */
    public ArrayList<Cliente> getClienti() {
        return this.clienti;
    }

    /**
     * restituisce il prezzo che corrisponde alla fila inserita
     *
     * @param fila numero che corrisponde alla fila
     * @return il costo della fila
     */
    public float getTariffa(int fila) {
        for (Ombrellone o : this.ombrelloni) {
            if (o.getFila() == fila)
                return o.getTariffa();
        }
        throw new IllegalArgumentException("la fila non esite");
    }

    /**
     * @return la lista degli ombrelloni contenuti nel casotto
     */
    public ArrayList<Ombrellone> getOmbrelloni() {

        return this.ombrelloni;
    }

    /**
     * @return la lista delle prenotazione del casotto
     */
    public ArrayList<PrenotazioneOmbrellone> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * @return la lista delle attivi&agrave; del casotto
     */

    public ArrayList<Attivita> getAttivita() {
        return attivita;
    }

    /**
     * @return il magazzino del casotto
     */

    public Map<Materiale, Integer> getMagazzino() {
        return magazzino;
    }

    /**
     * @return il personale del casotto
     */
    public ArrayList<Dipendente> getPersonale() {
        return personale;
    }
    //!------------------------SET----------------------!

    /**
     * permette di aggiornare tutta la lista dei Clienti
     *
     * @param Clienti lista di clienti
     */
    public void setClienti(ArrayList<Cliente> Clienti) {
        this.clienti = Clienti;
    }

    /**
     * permette di insirire una lista di ombrelloni
     *
     * @param Ombrelloni lista ombrelloni da aggiungere
     * @throws IllegalArgumentException se la lista è vuota
     */
    public void setOmbrelloni(ArrayList<Ombrellone> Ombrelloni) {
        if (Ombrelloni.isEmpty())
            throw new IllegalArgumentException("lista ombrelloni vuota");
        this.ombrelloni.addAll(Ombrelloni);

    }

    /**
     * aggiunge un ombrellone al Casotto
     *
     * @param o ombrellone da aggiungere
     */
    public void aggiungiOmbrellone(Ombrellone o) {
        this.ombrelloni.add(o);
    }

    /**
     * permette di rimpiazzare il magazzino
     *
     * @param magazzino nuovo magazzino
     */
    public void setMagazzino(Map<Materiale, Integer> magazzino) {
        this.magazzino = magazzino;
    }


    //!----------------SERVIZI----------------!

    /**
     * aggiunge attività alla lista
     *
     * @param a attivita da aggiungere
     */
    public void aggiungiAttivita(Attivita a) {

        this.attivita.add(a);
    }

    /**
     * permette di aggiungere il materiale al magazzino
     *
     * @param m materiale da aggiungere
     */
    public void aggiungiMateriale(Materiale m) {
        if (magazzino.containsKey(m)) {
            int numeroMateriale = this.magazzino.get(m);
            this.magazzino.replace(m, numeroMateriale + 1);
        } else
            this.magazzino.put(m, 1);
    }

    /**
     * inserisce un cliente nella lista
     *
     * @param cliente cliente da aggiungere
     */
    public void aggiungiCLiente(Cliente cliente) {
        if (cliente == null)
            throw new NullPointerException("il cliente è null");
        this.clienti.add(cliente);


    }

    /**
     * permette di aggiungere una prenotazione alla lista
     *
     * @param p prenotazione da aggiungere
     */
    public void aggiungiPrenotazione(PrenotazioneOmbrellone p) {
        if (p == null)
            throw new IllegalArgumentException("la prenotazione è null");
        this.prenotazioni.add(p);
    }

    /**
     * aggiunge un nuovo dipendente
     *
     * @param dipendente nuovo dipendete
     * @throws IllegalArgumentException se il dipendente è null.
     * @throws IllegalArgumentException se il dipendente è già presente
     */
    public void aggiungiDipendente(Dipendente dipendente) {
        if (dipendente == null)
            throw new IllegalArgumentException("dipendente null");
        if (this.personale.contains(dipendente))
            throw new IllegalArgumentException("dipendete gia presente");

        this.personale.add(dipendente);

    }

    /**
     * verifica se un cliente ha prenotato un determianto ombrellone
     *
     * @param o ombrellone da controllare
     * @param c cliente da controllare
     * @return true se la prenotazione &egrave; scaduta oppure false se la prenotazione.
     * non &egrave; contenuta o &egrave; scaduta.
     * @throws IllegalArgumentException se l'ombrellone non &egrave; contenuto.
     * @throws IllegalArgumentException se il cliente non &egrave; contenuto.
     */
    public boolean verificaPrenotazione(Ombrellone o, Cliente c) {
        if (!this.ombrelloni.contains(o))
            throw new IllegalArgumentException("L'ombrellone non è contenuto");
        if (!this.clienti.contains(c))
            throw new IllegalArgumentException("il cliente non è contenuto");
        for (PrenotazioneOmbrellone p : this.prenotazioni) {
            if (p.getO() == o && p.getC() == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * permette di aggiornare un ombrellone appartenenete al casotto
     *
     * @param ombrellone ombrellone da aggiornare
     */
    public void aggiornaOmbrellone(Ombrellone ombrellone) {
        int index = this.ombrelloni.indexOf(ombrellone);
        this.ombrelloni.set(index, ombrellone);
    }

    /**
     * Permette di modificare un'attivita esistente.
     *
     * @param idAttivita identificativo attivita
     * @param nome       nome attivita
     * @param orario     orario attivita
     * @param postiMax   posti max
     * @param postiMin   posti minimi
     * @throws IllegalArgumentException nome errato
     * @throws IllegalArgumentException orario errato
     * @throws IllegalArgumentException postiMax errato
     * @throws IllegalArgumentException postiMin errato
     */
    public void aggiornaAttivita(String idAttivita, String nome, LocalDateTime orario, int postiMax, int postiMin) {
        for (Attivita a : this.attivita) {
            if (a.getIdAttivita().equals(idAttivita)) {
                if (!nome.isEmpty())
                    a.setNome(nome);
                else
                    throw new IllegalArgumentException("nome errato");
                if (orario.isAfter(LocalDateTime.now()))
                    a.setOrario(orario);
                else
                    throw new IllegalArgumentException("orario errato");
                if (postiMax > 0)
                    a.setPostiMax(postiMax);
                else
                    throw new IllegalArgumentException("postiMax errato");
                if (postiMin > 0)
                    a.setPostiMin(postiMin);
                else
                    throw new IllegalArgumentException("psotiMin errato");

            }
        }
    }
}