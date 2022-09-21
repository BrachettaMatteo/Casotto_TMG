package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Bagnino;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Dipendente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Balneare.ComandaBalneare;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Rappresenta un bagninoController all'interno del casotto. Il suo ruolo: la gestione delle comande balneare.
 */
public class Bagnino extends Dipendente {

    private final ArrayList<ComandaBalneare> listaOrdini;

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

}