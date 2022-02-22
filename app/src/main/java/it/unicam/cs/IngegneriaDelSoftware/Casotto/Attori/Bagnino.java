package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.ComandaBalneare;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Rappresenta un bagninoController all'interno del casotto. Il suo ruolo: la gestione delle comande balneare.
 */
public class Bagnino extends Dipendente {

    @FXML
    private TableView<Ombrellone> tabOm;

    private ArrayList<ComandaBalneare> listaOrdini;

    /**
     * crea un nuovo bagninoController
     *
     * @param nome       nome del bagnina
     * @param cognome    cognome del bagninoController
     * @param residenza  residenza del bagninoController
     * @param telefono   telefono del bagninoController
     * @param nomeUtente nomeUtente del bagninoController
     * @param email      email del bagninoController
     */
    public Bagnino(String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        super(nome, cognome, residenza, telefono, nomeUtente, email, "Bagnino");
        this.listaOrdini = new ArrayList<>();
       // getC().aggiungiDipendente(this);
    }

    public Bagnino(String id, String nome, String cognome, String residenza, String telefono, String nomeUtente, String email) {
        super(id, nome, cognome, residenza, telefono, nomeUtente, email, "Bagnino");
        this.listaOrdini= new ArrayList<>();
    }

    /**
     * libera un ombrellone, quindi svuoterà i materiali aggiuntivi e impostera la disponibilità per essere prenotatato
     *
     * @param ombrellone ombrellone da sistemare.
     * @throws IllegalArgumentException se l'ombrellone non appartiene al casotto.
     */
    public void liberaOmbrellone(Ombrellone ombrellone) {
        //controllo che l'ombrellone sia contenuto
        if (this.getC().getOmbrelloni().contains(ombrellone)) {
            //controllo che l'ombrellone sia occupato
            int index = this.getC().getOmbrelloni().indexOf(ombrellone);
            if (!this.getC().getOmbrelloni().get(index).getIsDisponibile()) {
                ombrellone.libera();
                try {
                    Connection con = Database.getConnection();
                    con.createStatement().executeUpdate("UPDATE Ombrellone SET Disponibilita = 'disponibile' WHERE ID='" + ombrellone.getId() + "'");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                this.getC().aggiungiOmbrellone(ombrellone);
            } else
                throw new IllegalArgumentException("l'ombrellone è libero");
        } else
            throw new IllegalArgumentException("l'ombrellone non appartiene al casotto");
    }

    /**
     * permette di aggiungere un'Attivit&agrave; al casotto
     *
     * @param a Attivit&agrave; da aggiungere
     */
    public void aggiungiAttivita(Attivita a) {
        if (this.getC().getAttivita().contains(a))
            throw new IllegalArgumentException("l'attività è gia presente");
        else
            this.getC().aggiungiAttivita(a);
    }

    /**
     * permette di modifcare un Attivit&agrave; gi&agrave; esistenete
     *
     * @param idAttivita identificativo dell' Attivit&agrave;.
     * @param nome       nuovo nome dell Attivit&agrave;
     * @param orario     nuovo orario dell Attivit&agrave;
     * @param postiMax   nuovi posti massimi dell Attivit&agrave;
     * @param postiMin   nuovi posti min dell  Attivit&agrave;
     */
    public void modificaAttivita(String idAttivita, String nome, LocalDateTime orario, int postiMax, int postiMin) {
        this.getC().aggiornaAttivita(idAttivita, nome, orario, postiMax, postiMin);
    }

    /**
     * aggiunge una comanda balneare alla lista del bagninoController
     *
     * @param c comanda da aggiungere
     * @throws IllegalArgumentException se la comanda non è contenuta.
     */
    public void aggiungiComanda(ComandaBalneare c) {
        if (this.listaOrdini.contains(c))
            throw new IllegalArgumentException("la comanda è contenuta");
        else
            this.listaOrdini.add(c);
    }


    /**
     * @param idcomanda identificativo comanda da evadere
     */
    public void evadiComanda(String idcomanda) {
        try {
            Connection con = Database.getConnection();
            con.createStatement().executeUpdate("UPDATE ComandaBalneare  Set Status = 'Consegnato' WHERE ID='" + idcomanda + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * @return la lista degli ordini del bagninoController
     */
    public ArrayList<ComandaBalneare> getListaOrdini() {
        this.listaOrdini.clear();
        try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM ComandaBalneare WHERE NOT Status = 'Consegnato';");

            while (rs.next()) {
                ComandaBalneare cm = new ComandaBalneare(
                        rs.getString("ID"),
                        rs.getString("idOmbrellone"),
                        rs.getString("idCliente"),
                        //ottieniMateriale(rs.getString("idMateriale")),
                        rs.getString("idMateriale"),
                        rs.getTimestamp("Durata"),
                        rs.getString("Status"),
                        rs.getInt("Quantita")
                );
                listaOrdini.add(cm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdini;
    }

    @FXML
    public void gestioneOmbrelloni(ActionEvent event) {
        TableColumn<Ombrellone, String> idOmbrellone = new TableColumn<>("id ");
        TableColumn<Ombrellone, Integer> numeroOmbrellone = new TableColumn<>("id ");
        TableColumn<Ombrellone, Integer> filaOmbrellone = new TableColumn<>("id ");
        TableColumn<Ombrellone, Timestamp> fineOmbrellone = new TableColumn<>("id ");
        TableColumn<Ombrellone, String> dipsonibilitaOmbrellone = new TableColumn<>("id ");
        tabOm.getColumns().addAll(idOmbrellone, numeroOmbrellone, filaOmbrellone, fineOmbrellone, dipsonibilitaOmbrellone);
        tabOm.setItems((ObservableList<Ombrellone>) Casotto.getInstance().getOmbrelloni());

    }

    public void evadiComanda(ActionEvent event) {
    }
}