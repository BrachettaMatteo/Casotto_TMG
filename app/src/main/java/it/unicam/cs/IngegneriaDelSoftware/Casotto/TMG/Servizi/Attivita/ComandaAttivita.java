package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Attivita;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Cliente.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Comanda;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Rappresenta una comanda per prenotare un'attivit&agrave;
 */
public class ComandaAttivita extends Comanda {

    private final Attivita a;
    private final int numeroPersone;


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
        Connection con ;
        Cliente cliente = Casotto.getInstance().getCliente(this.getIdCliente());
        //pago conto
        if (cliente.paga(this.calcolaConto())) {
            try {
                con = Database.getConnection();
                String query = "INSERT INTO ComandaAttivita(ID, idOmbrellone, idCliente, idAttivita) VALUES ('" +
                        this.getIdComanda().toString() + "', '"
                        + this.getIdOmbrellone() + "', '"
                        + this.getIdCliente() + "', '"
                        + this.a.getIdAttivita() + "');";
                con.createStatement().executeUpdate(query);
                //aggiungi partecipanti all'attivita
                Casotto.getInstance().aggiungiPertecipantiAttivita(a.getIdAttivita(), numeroPersone);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Prenotazione effettuata", ButtonType.OK);
                alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            //erorre
            Alert alert = new Alert(Alert.AlertType.ERROR, "Credito insufficiente", ButtonType.OK);
            alert.show();
        }
    }

    @Override
    public String toString() {
        return "ComandaAttivita \n" +
                "Attivita: " + a.getNome() + "\n" +
                "Posti prenotati: " + numeroPersone + "\n" +
                "Costo: " + a.getCosto() + "\n" +
                "Totale: " + this.calcolaConto();
    }
}