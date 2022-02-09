package it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Cliente;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerCliente implements Initializable {

    @FXML
    private Button btnPrenotaAttivita;

    @FXML
    private Button btnPrenotaOmbrellone;

    @FXML
    private Button btnPrenotaRistorazioen;

    @FXML
    private Button btnprenotaOmbrellone;

    @FXML
    private Button buttonRicarica;

    @FXML
    private VBox infoPane;

    @FXML
    private Text labelCognomeUtente;

    @FXML
    private Text labelCreditoUtente;

    @FXML
    private Text labelNomeUtente;
     Text IdCliente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con = null;
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT Id, Nome, Congome, Conto FROM Clienti" +
                    " WHERE id=");

        } catch (SQLException e) {

            e.printStackTrace();
        }


    }

    @FXML
    void prenotaAttivita(ActionEvent event) {

    }

    @FXML
    void prenotaOmbrellone(ActionEvent event) {

    }

    @FXML
    void prenotaRistorazione(ActionEvent event) {

    }

    @FXML
    void prenotaombrellone(ActionEvent event) {

    }

    @FXML
    void ricaricaConto(ActionEvent event) {

    }

}
