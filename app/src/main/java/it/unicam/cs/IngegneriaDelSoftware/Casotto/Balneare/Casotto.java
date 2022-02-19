package it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Bagnino;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cassiere;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private ArrayList<Materiale> magazzino;

    private ArrayList<Attivita> attivita;


    //!-----------costruttore & instance----------!

    /**
     * Permette di creare un nuovo Casotto
     */
    public Casotto() {
        this.ombrelloni = new ArrayList<>();
        this.clienti = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
        this.magazzino = new ArrayList<>();
        this.attivita = new ArrayList<>();
        this.personale = new ArrayList<>();

    }

    /**
     * @return il casotto
     */
    public static Casotto getInstance() {
        if (instance == null)
            instance = new Casotto();
        return instance;
    }

    //!------------------------GET----------------------!

    /**
     * @return La lista dei clienti del casotto
     */
    public ArrayList<Cliente> getClienti() {
        Connection con = null;
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Clienti");
            this.clienti.clear();
            while (rs.next()) {
                Cliente cl = new Cliente(
                        rs.getString("Id"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Residenza"),
                        rs.getString("Telefono"),
                        rs.getString("nomeUtente"),
                        rs.getString("email"),
                        rs.getFloat("Conto")
                );
                this.clienti.add(cl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.clienti;
    }

    /**
     * restituisce il prezzo che corrisponde alla fila inserita
     *
     * @param fila numero che corrisponde alla fila
     * @return il costo della fila
     */
    public float getTariffa(int fila) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT distinct Tariffa as prezzo FROM Ombrelloni WHERE Fila = " + fila + ";";
            ResultSet rs = con.createStatement().executeQuery(query);
            return rs.getInt("prezzo");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @return la lista degli ombrelloni contenuti nel casotto
     */
    public ArrayList<Ombrellone> getOmbrelloni() {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Ombrelloni ORDER BY Numero";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (this.ombrelloni != null)
                this.ombrelloni.clear();
            while (rs.next()) {
                Ombrellone o = new Ombrellone(rs.getString("Id"),
                        rs.getInt("Fila"),
                        rs.getFloat("Tariffa"),
                        rs.getInt("Numero"),
                        rs.getString("Disponibilita"),
                        rs.getTimestamp("Fine"));
                this.ombrelloni.add(o);
            }
            return this.ombrelloni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return la lista delle prenotazione del casotto
     */
    public ArrayList<PrenotazioneOmbrellone> getPrenotazioni() {
        this.prenotazioni.clear();
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Prenotazioni";
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                this.prenotazioni.add(new PrenotazioneOmbrellone(rs.getString("Id"), rs.getString("idCliente"),
                        rs.getString("idOmbrellone"), rs.getTimestamp("fine"), rs.getTimestamp("inizio")));
            }
            return this.prenotazioni;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.prenotazioni;
    }

    /**
     * @return la lista delle attivi&agrave; del casotto
     */
    public ArrayList<Attivita> getAttivita() {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Attivita";
            ResultSet rs = con.createStatement().executeQuery(query);
            this.attivita.clear();
            while (rs.next()) {
                Attivita a = new Attivita(rs.getString("Id"),
                        rs.getString("nome"),
                        rs.getInt("postiMax"),
                        rs.getInt("postiMin"),
                        rs.getFloat("costo"),
                        rs.getTimestamp("orario"));
                this.attivita.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attivita;
    }

    /**
     * @return il magazzino del casotto
     */

    public ArrayList<Materiale> getMagazzino() {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Materiali WHERE Quantita > 0";
            ResultSet rs = con.createStatement().executeQuery(query);
            this.magazzino.clear();
            while (rs.next()) {
                Materiale m = new Materiale(
                        rs.getString("idMateriale"),
                        rs.getString("Nome"),
                        rs.getFloat("costo"),
                        rs.getInt("Quantita")
                );
                this.magazzino.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.magazzino;
    }

    /**
     * @return il personale del casotto
     */
    public ArrayList<Dipendente> getPersonale() {
        this.personale.clear();
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Dipendenti";
            ResultSet rs = con.createStatement().executeQuery(query);

            while (rs.next()) {
                Dipendente d = new Dipendente(
                        rs.getString("Id"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Residenza"),
                        rs.getString("Telefono"),
                        rs.getString("nomeUtente"),
                        rs.getString("Email"),
                        rs.getString("Ruolo")
                );
                this.personale.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.personale;
    }
    //!------------------------SET----------------------!

    /**
     * permette di aggiornare tutta la lista dei Clienti
     *
     * @param Clienti lista di clienti
     */
    public void setClienti(ArrayList<Cliente> Clienti) {
        for (Cliente cl : Clienti)
            this.aggiungiCLiente(cl);
    }

    /**
     * permette di insirire una lista di ombrelloni
     *
     * @param Ombrelloni lista ombrelloni da aggiungere
     * @throws IllegalArgumentException se la lista è vuota
     */
    public void setOmbrelloni(ArrayList<Ombrellone> Ombrelloni) {
        for (Ombrellone o : Ombrelloni)
            this.aggiungiOmbrellone(o);

    }

    /**
     * aggiunge un ombrellone al Casotto
     *
     * @param o ombrellone da aggiungere
     */
    public void aggiungiOmbrellone(Ombrellone o) {
        try {
            Connection con = Database.getConnection();
            String query = "INSERT INTO Ombrelloni(Id, Numero, Fila, Tariffa, Fine `Disponibilita`) VALUES (" +
                    o.getId() + "," +
                    o.getNumero() + "," +
                    o.getTariffa() + "," +
                    o.getFine() + "," +
                    this.setDiponibilita(o.getIsDisponibile()) +
                    ");";
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String setDiponibilita(boolean disponibilita) {
        return disponibilita ? "Disponibile" : "Non Disponibile";
    }

    /**
     * permette di rimpiazzare il magazzino
     *
     * @param magazzino nuovo magazzino
     */
    public void setMagazzino(Map<Materiale, Integer> magazzino) {
        magazzino.forEach(this::aggiungiMateriale);
    }


    //!----------------SERVIZI----------------!

    /**
     * aggiunge attività alla lista
     *
     * @param a attivita da aggiungere
     */
    public void aggiungiAttivita(Attivita a) {
        try {
            Connection con = Database.getConnection();
            String query = "INSERT INTO Attivita(id, nome, postimax, postimin, costo, orario) VALUES (" +
                    "'" + a.getId() + "'," +
                    "'" + a.getNome() + "'," +
                    "'" + a.getPostiMax() + "'," +
                    "'" + a.getCosto() + "'," +
                    "'" + a.getOrario() + "'" +
                    ");";
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * permette di aggiungere il materiale al magazzino
     *
     * @param m materiale da aggiungere
     */
    public void aggiungiMateriale(Materiale m, Integer v) {
        try {
            Connection con = Database.getConnection();
            String query = "select * from Materiali where idMateriale= '" + m.getId() + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                //il materiale è presente aggiorno
                String subquery = "UPDATE Materiali SET 'Quantità' =' Quantità' + " + m.getQuantita() +
                        " WHERE idMateriale = '" + m.getId() + "'; ";
                con.createStatement().executeUpdate(subquery);
            } else {
                //aggiungo il materiale
                String subquery = "INSERT INTO Materiali (idMateriale, Nome, Quantita, costo) +" +
                        "VALUES ('" + m.getId() + "'," +
                        "'" + m.getNome() + "'," +
                        "'" + m.getQuantita() + "'," +
                        "'" + m.getCosto() + "');";
                con.createStatement().executeUpdate(subquery);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * inserisce un cliente nella lista
     *
     * @param cliente cliente da aggiungere
     */
    public void aggiungiCLiente(Cliente cliente) {
        try {
            Connection con = Database.getConnection();
            String query = "INSERT INTO Clienti(Id, Nome, Cognome, Residenza, Telefono, Email, Conto, nomeUtente) VALUEs ('" +
                    cliente.getId() + "'," +
                    "'" + cliente.getNome() + "'," +
                    "'" + cliente.getCognome() + "'," +
                    "'" + cliente.getResidenza() + "'," +
                    "'" + cliente.getTelefono() + "'," +
                    "'" + cliente.getEmail() + "'," +
                    +cliente.getSaldo() + "," +
                    "'" + cliente.getEmail() +
                    "');";
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * permette di aggiungere una prenotazione alla lista
     *
     * @param p prenotazione da aggiungere
     *          public PrenotazioneOmbrellone(String id, String idCliente,
     *          String idOmbrellone, Timestamp fine) {
     */
    public void aggiungiPrenotazione(PrenotazioneOmbrellone p) {
        if (p == null)
            throw new IllegalArgumentException("la prenotazione è null");
        try {
            Connection con = Database.getConnection();
            String query = "INSERT INTO Prenotazioni(id, idCliente, idombrellone, inizio) VALUES ('" +
                    p.getIdComanda() + "'," +
                    "'" + p.getC().getId() + "'," +
                    "'" + p.getO().getId() + "'," +
                    p.getDataFine() + ");";
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        try {
            Connection con = Database.getConnection();
            String query = "INSERT INTO Dipendenti(Id, Nome, Cognome, Residenza, Telefono, Ruolo, Email, nomeUtente) VALUES ('" +
                    dipendente.getId() + "'," +
                    "'" + dipendente.getNome() + "'," +
                    "'" + dipendente.getCognome() + "'," +
                    "'" + dipendente.getResidenza() + "'," +
                    "'" + dipendente.getTelefono() + "'," +
                    "'" + dipendente.getRuolo() + "'," +
                    "'" + dipendente.getEmail() + "'," +
                    "null);";
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * verifica se un cliente ha prenotato un determinate ombrellone
     *
     * @param o ombrellone da controllare
     * @param c cliente da controllare
     * @return true se la prenotazione &egrave; scaduta oppure false se la prenotazione.
     * non &egrave; contenuta o &egrave; scaduta.
     * @throws IllegalArgumentException se l'ombrellone non &egrave; contenuto.
     * @throws IllegalArgumentException se il cliente non &egrave; contenuto.
     */
    public boolean verificaPrenotazione(Ombrellone o, Cliente c) {/*
        if (!this.getOmbrelloni().contains(o))
            throw new IllegalArgumentException("L'ombrellone non è contenuto");
        if (!this.getClienti().contains(c))
            throw new IllegalArgumentException("il cliente non è contenuto");*/
        Connection con = null;
        try {
            con = Database.getConnection();
            //rivedere query
            String query = "select id from Prenotazioni where idCliente='" + c.getId() + "' && idOmbrellone='" + o.getId() + "'" +
                    " && 0>(SELECT datediff(fine,'" + Timestamp.valueOf(LocalDateTime.now()) + "')";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * permette di aggiornare un ombrellone appartenenete al casotto
     *
     * @param ombrellone ombrellone da aggiornare
     */
    public void aggiornaOmbrellone(Ombrellone ombrellone) {
        if (this.getOmbrelloni().contains(ombrellone))
            throw new IllegalArgumentException("Ombrellone non contenuto");
        Connection con = null;
        try {
            con = Database.getConnection();
            String query = "UPDATE Ombrelloni SET Numero=" + ombrellone.getNumero() + ", Fila=" + ombrellone.getFila() +
                    ",Tariffa=" + ombrellone.getTariffa() + "," +
                    "Fine =" + ombrellone.getFine() + "," +
                    "'Disponibilita' =" + this.setDiponibilita(ombrellone.getIsDisponibile()) +
                    " WHERE id='" + ombrellone.getId() + "';";
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        if (nome.isEmpty())
            throw new IllegalArgumentException("il nome è errato");
        if (orario.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("la data è errata");
        if (postiMax < 1)
            throw new IllegalArgumentException("posti Max errati");
        if (postiMin < 1)
            throw new IllegalArgumentException("posti min errati");
        try {
            Connection con = Database.getConnection();
            String query = "UPDATE Attivita SET nome=" + nome + ", postiMax=" + postiMax +
                    ",postiMin=" + postiMin + ", orario" +
                    " =" + orario + "," +
                    " WHERE id='" + idAttivita + "';";
            con.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getNomeMateriale(String idmateriale) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT nome FROM Materiali WHERE idMateriale='" + idmateriale + "';";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next())
                return rs.getString("nome");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Ombrellone getOmbrellone(String ombrellone) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Ombrelloni WHERE Id='" + ombrellone + "';";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next())
                return new Ombrellone(
                        rs.getString("Id"),
                        rs.getInt("Fila"),
                        rs.getFloat("Tariffa"),
                        rs.getInt("Numero"),
                        rs.getString("Disponibilita"),
                        rs.getTimestamp("Fine")
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getNomiAttivita() {
        ArrayList<String> out = new ArrayList<>();
        try {
            Connection con = Database.getConnection();
            String query = "select distinct nome from Attivita WHERE postiMax-Attivita.Componenti>0;";
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next())
                out.add(rs.getString("nome"));
            return out;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public ArrayList<Timestamp> getOrariAttivita(String value) {
        ArrayList<Timestamp> out = new ArrayList<>();
        try {
            Connection con = Database.getConnection();
            String query = "select orario from Attivita where nome='" + value + "'and Componenti<Attivita.postiMax;";
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next())
                out.add(rs.getTimestamp("orario"));
            return out;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public String getOmbrelloneIdCliente(String idCliente) throws SQLException {
        Connection con = Database.getConnection();
        String query = "select idOmbrellone from Prenotazioni where idCliente='" + idCliente + "' && fine > '" + Timestamp.valueOf(LocalDateTime.now()) + "'";
        ResultSet rs = con.createStatement().executeQuery(query);
        if (rs.next()) {
            return rs.getString("idOmbrellone");
        }
        throw new IllegalArgumentException("cliente non prenotato");
    }

    public String getIdAttivita(String nome, Timestamp data) throws SQLException {
        Connection con = Database.getConnection();
        String query = "SELECT Id from Attivita WHERE nome='" + nome + "' && orario='" + data + "';";
        ResultSet rs = con.createStatement().executeQuery(query);
        if (rs.next()) {
            return rs.getString("Id");
        }
        throw new IllegalArgumentException("cliente non prenotato");
    }

    public ArrayList<Integer> getOmbrelloniDisponibili() throws SQLException {
        Connection con = Database.getConnection();
        String query = "SELECT * from Ombrelloni WHERE Disponibilita='Disponibile' ORDER BY Numero;";
        ResultSet rs = con.createStatement().executeQuery(query);
        ArrayList<Integer> NumOm = new ArrayList<>();
        while (rs.next()) {
            NumOm.add(rs.getInt("Numero"));
        }
        return NumOm;

    }

    public ArrayList<Integer> getNumeroOmbrelloni() throws SQLException {
        Connection con = Database.getConnection();
        String query = "SELECT * from Ombrelloni ORDER BY Numero;";
        ResultSet rs = con.createStatement().executeQuery(query);
        ArrayList<Integer> NumOm = new ArrayList<>();
        while (rs.next()) {
            NumOm.add(rs.getInt("Numero"));
        }
        return NumOm;

    }

    public String getidOmbrellone(Integer value) throws SQLException {
        Connection con = Database.getConnection();
        String query = "SELECT Id from Ombrelloni WHERE Numero=" + value + ";";
        ResultSet rs = con.createStatement().executeQuery(query);
        if (rs.next())
            return rs.getString("Id");
        throw new IllegalArgumentException("errore ombrellone non esiste");
    }

    public Cliente getCliente(String idCliente) {
        Connection con = null;
        try {
            con = Database.getConnection();
            String query = "SELECT * from Clienti WHERE Id='" + idCliente + "';";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                return new Cliente(
                        rs.getString("Id"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Residenza"),
                        rs.getString("Telefono"),
                        rs.getString("nomeUtente"),
                        rs.getString("email"),
                        rs.getFloat("Conto"));
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, "il cliente non esiste", ButtonType.OK);
            alert.show();
            throw new IllegalArgumentException("errore cliente non esiste");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("errore cliente");
    }

    public Attivita getAttivitaSingola(String idAttivita) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Attivita WHERE Id='" + idAttivita + "';";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                return new Attivita(rs.getString("Id"),
                        rs.getString("nome"),
                        rs.getInt("postiMax"),
                        rs.getInt("postiMin"),
                        rs.getFloat("costo"),
                        rs.getTimestamp("orario"));
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cliente getClienteToNomeUtente(String NomeUtente) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Clienti WHERE nomeUtente='" + NomeUtente + "';";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                return new Cliente(
                        rs.getString("Id"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Residenza"),
                        rs.getString("Telefono"),
                        rs.getString("nomeUtente"),
                        rs.getString("email"),
                        rs.getFloat("Conto"));
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, "il cliente non esiste", ButtonType.OK);
            alert.show();
            throw new IllegalArgumentException("errore ricerca cliente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Bagnino getBagnino(String text) {
        try {
            Connection connection = Database.getConnection();
            String query = "SELECT * FROM Dipendenti WHERE nomeUtente='" + text + "';";
            ResultSet rs = connection.createStatement().executeQuery(query);
            if (rs.next()) {
                return new Bagnino(
                        rs.getString("Id"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Residenza"),
                        rs.getString("Telefono"),
                        rs.getString("nomeUtente"),
                        rs.getString("Email")
                );
            } else throw new IllegalArgumentException("Errore ricomposizione bagnino");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Errore ricomposizione bagnino");

    }

    public void aggiungiPertecipantiAttivita(String idAttivita, int numeroPersone) {
        Connection con = null;
        try {
            con = Database.getConnection();
            String query = "UPDATE Attivita SET Componenti = Componenti + " + numeroPersone + " WHERE Id='" + idAttivita + "';";
            con.createStatement().executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Ombrellone> getOmbrelloniCliente(String idCliente) {
        ArrayList<Ombrellone> out = new ArrayList<>();
        try {
            Connection con = Database.getConnection();
            String query = "SELECT idOmbrellone FROM Prenotazioni WHERE idCliente='" + idCliente + "' && fine > '" + Timestamp.valueOf(LocalDateTime.now()) + "';";
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                query = "SELECT * FROM Ombrelloni WHERE Id='" + rs.getString("idOmbrellone") + "';";
                ResultSet om = con.createStatement().executeQuery(query);
                while (om.next()) {
                    out.add(new Ombrellone(
                            om.getString("Id"),
                            om.getInt("Fila"),
                            om.getFloat("Tariffa"),
                            om.getInt("Numero"),
                            om.getString("Disponibilita"),
                            om.getTimestamp("Fine")));

                }
            }
            return out;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return out;
    }

    public int getQuantitaMateriale(String nomeMateriale) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT Quantita FROM Materiali WHERE Nome='" + nomeMateriale + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                return rs.getInt("Quantita");
            } else
                return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Cassiere getCassiereToNomeUtente(String nomeUtente) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT * FROM Dipendenti WHERE nomeUtente='" + nomeUtente + "'";
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                return new Cassiere(
                        rs.getString("Id"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Residenza"),
                        rs.getString("Telefono"),
                        rs.getString("nomeUtente"),
                        rs.getString("Email"));
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getOmbrelloneToNumero(int numero) {
        try {
            Connection con = Database.getConnection();
            String query = "SELECT Id FROM Ombrelloni WHERE Numero=" + numero;
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next())
                return rs.getString("Id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Prodotto> getMenu() {
        try {
            ArrayList<Prodotto> out = new ArrayList<>();
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Prodotto");
            while (rs.next()) {
                Prodotto p = new Prodotto(rs.getString("Id"),
                        rs.getString("nome"),
                        rs.getFloat("Costo"),
                        rs.getString("Descrizione"));
                out.add(p);
            }
            return out;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Prodotto getProdotto(String nome) {
        ArrayList<Prodotto> out = new ArrayList<>();
        Connection con = null;
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Prodotto where nome= '" + nome + "'");
            while (rs.next()) {
                return new Prodotto(rs.getString("Id"),
                        rs.getString("nome"),
                        rs.getFloat("Costo"),
                        rs.getString("Descrizione"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    public  ArrayList<ComandaRistorazione> getComandeRistorazione() {
        ArrayList<ComandaRistorazione> Lcr = new ArrayList<>();
        ComandaRistorazione cr;
        try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM comandaRistorazione Where not Status='Consegnato'");
            while (rs.next()) {
                cr = new ComandaRistorazione(rs.getString("id"),
                        rs.getString("idCliente"),
                        rs.getString("idOmbrellone"),
                        rs.getString("prodotti"),
                        rs.getString("Status"));
                Lcr.add(cr);
            }
            return Lcr;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getOmbrellonePrenotatati(Cliente c) {
        ArrayList<String> om = new ArrayList<>();
        try {
            Connection con = Database.getConnection();
            String query = "SELECT idOmbrellone from Prenotazioni where idCliente='"+c.getId()+"'";
           ResultSet rs = con.createStatement().executeQuery(query);
           while(rs.next()){
               om.add(rs.getString("idOmbrellone"));
           }
           return om;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return om;
    }
}