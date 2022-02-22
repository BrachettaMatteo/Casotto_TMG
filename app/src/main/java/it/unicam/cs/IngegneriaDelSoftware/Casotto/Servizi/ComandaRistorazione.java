package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta una Comanda ristorazione e le sue caratteristiche
 */
public class ComandaRistorazione extends Comanda {

    private  String listaProdotti;
    private ArrayList<Prodotto> prodottiOrdinati;
    private Status status;

    /**
     * permette di creare una comanda ristorazione
     *
     * @param o                ombrellone
     * @param c                cliente
     * @param prodottiOrdinati lista dei prodotto da ordinare
     * @throws IllegalArgumentException se la lista vuota
     * @throws NullPointerException     se almeno prodotto della lista &egrave; null
     */
    public ComandaRistorazione(Ombrellone o, Cliente c, ArrayList<Prodotto> prodottiOrdinati) {
        super(o, c);
        if (prodottiOrdinati.isEmpty())
            throw new IllegalArgumentException("la lista è vuota");
        for (Prodotto p : prodottiOrdinati)
            if (p == null)
                throw new IllegalArgumentException("prodotto null");

        this.prodottiOrdinati = prodottiOrdinati;
        super.setStatus(Status.daElaborare);
    }

    public ComandaRistorazione(Ombrellone o, Cliente c) {
        super(o, c);
        super.setStatus(Status.daElaborare);
    }

    public ComandaRistorazione(String id, String idCliente, String idOmbrellone, String prodotti, String status) {
        super(id, Casotto.getInstance().getOmbrellone(idOmbrellone), Casotto.getInstance().getCliente(idCliente));
        this.listaProdotti = prodotti;
        super.setStatus(Status.valueOf(status));
    }

    /**
     * @return la lista dei prodotti della comanda
     */
    public ArrayList<Prodotto> getProdottiOrdinati() {
        return this.prodottiOrdinati;
    }

    @Override
    public float calcolaConto() {
        float importo = 0;
        for (Prodotto p : prodottiOrdinati)
            importo += p.getPrezzo();
        return importo;
    }

    @Override
    public void chiudiComanda() {
        Alert alert;
        if (this.getC().paga(this.calcolaConto())) {
            //creo comanda Lista prodotti
            StringBuilder out = new StringBuilder();
            for (Prodotto p : this.prodottiOrdinati) {
                out.append(p.getNome()).append(" x").append(p.getQuantita()).append(" , ");
            }
            try {
                Connection con = Database.getConnection();
                String query = "insert into ComandaRistorazione(ID, idCliente, idOmbrellone, Prodotti) VALUES (" +
                        "'" + this.getIdComanda() + "' ," +
                        "'" + this.getC().getId() + "' ,"
                        + "'" + this.getO().getId() + "' ,"
                        + "'" + out.toString() + "'"
                        + ");";
                con.createStatement().executeUpdate(query);
            alert= new Alert(Alert.AlertType.CONFIRMATION,"prenotazione effettuata");
            alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.ERROR, "credito insufficiente");
            alert.setTitle("ERRORE");
            alert.show();
            throw new IllegalArgumentException("errore credito");
        }
    }

    /**
     * aggiunge un prodotto alla lista dei prodotti ordinati
     *
     * @param p prodotto da aggiungere
     * @return <code>true</code> se il prodotto &egrave; stato aggiunto,
     * altrimenti <code>False</code>
     */
    public boolean aggiungiProdotto(Prodotto p) {
        if (this.prodottiOrdinati == null) {
            this.prodottiOrdinati = new ArrayList<>();
            return this.prodottiOrdinati.add(p);
        }
        return this.prodottiOrdinati.add(p);
    }

    public String getListaProdotti() {
        return listaProdotti;
    }

    /**
     * permette di riuovere un prodotto dalla lista
     *
     * @param p prodotto da rimuovere
     * @return <code>True</code> se il prodotto &egrave; stato rimosso
     * altrimenti  <code>False</code>
     * @throws IllegalArgumentException se il prodotto non &egrave; contenuto nella
     *                                  lista dei prodotti;
     */
    public boolean rimuoviProdotto(Prodotto p) {
        if (this.prodottiOrdinati.contains(p)) {
            return this.prodottiOrdinati.remove(p);
        } else
            throw new IllegalArgumentException("il prodotto non è contenuto");
    }

    public String getProdotti() {
        StringBuilder out = new StringBuilder();
        System.out.println(prodottiOrdinati.toString());
        for (Prodotto p : prodottiOrdinati) {
            out.append(p.getNome()).append(" ").append(p.getQuantita()).append(" , ");
        }
        return out.toString();
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("ComandaRistorazione:\n" + "Cliente: ")
                .append(this.getC().getId()).append("\n")
                .append("Ombrellone: "
                ).append(this.getO().getNumero()).append("\n").append("Lista Prodotti: \n").append(listaProdotti);
        out.append("Status : ").append(this.getStatus());

        return out.toString();
    }
    public void rimuoviProdotti() {
        this.prodottiOrdinati.clear();
    }
    public String getId(){
        return this.getIdComanda().toString();
    }
    public String getIdCliente(){
        return super.getC().getId();
    }

}