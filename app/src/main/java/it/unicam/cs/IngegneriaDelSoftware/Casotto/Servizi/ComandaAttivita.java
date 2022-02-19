package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Rappresenta una comanda per prenotare un'attivit&agrave;
 */
public class ComandaAttivita extends Comanda {


    private ArrayList<Attivita> attivita;
    private Attivita a;
    private int numeroPersone;

    /**
     * permette di creare una nuova comanda
     *
     * @param o        ombrellone della comanda
     * @param c        cliente della comanda
     * @param attivita attivita da prenotare
     */
    public ComandaAttivita(Ombrellone o, Cliente c, ArrayList<Attivita> attivita) {
        super(o, c);
        this.attivita = attivita;
    }

    public ComandaAttivita(String idOmbrellone, String idCliente, String idAttivita, int numeroPersone) {
        super(idOmbrellone, idCliente);
        a = Casotto.getInstance().getAttivitaSingola(idAttivita);
        this.numeroPersone = numeroPersone;
    }


    @Override
    public float calcolaConto() {
        return a.getCosto() * numeroPersone;
    }

    @Override
    public void chiudiComanda() {
        //salvo comanda nel db
        Connection con = null;
        Cliente cliente = Casotto.getInstance().getCliente(this.getIdCliente());
            //pago conto
            if (cliente.paga(this.calcolaConto())) {
                try {
                    con = Database.getConnection();
                    String query = "INSERT INTO ComandeAttivita(id, idOmbrellone, idCliente, idAttivita) VALUES ('" +
                            this.getIdComanda().toString() + "', '"
                            + this.getIdOmbrellone() + "', '"
                            + this.getIdCliente() + "', '"
                            + this.a.getIdAttivita() + "');";
                    con.createStatement().executeUpdate(query);
                    //aggiungi partecipanti all'attivita
                    Casotto.getInstance().aggiungiPertecipantiAttivita(a.getIdAttivita(), numeroPersone);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        //erorre
        Alert alert = new Alert(Alert.AlertType.ERROR,"Credito insufficiente", ButtonType.OK);
    }

    @Override
    public String toString() {
        return "ComandaAttivita \n" +
                "Attivita: " + a.getNome() + "\n" +
                "Posti prenotati: " + numeroPersone + "\n" +
                "Costo: " + a.getCosto()+ "\n" +
                "Totale: "+this.calcolaConto();
    }
}