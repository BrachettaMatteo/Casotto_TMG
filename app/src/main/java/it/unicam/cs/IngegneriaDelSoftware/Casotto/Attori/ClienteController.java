package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.ComandaRistorazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {
    static Cliente c;

    public static void setC(Cliente c) {
        ClienteController.c = c;
    }

    @FXML
    private Label labInt;
    @FXML
    private Label lbNomeCliente;
    @FXML
    private Label lbResidenza;
    @FXML
    private Label lbTelefono;
    @FXML
    private Label lbEmailCliente;
    @FXML
    private Button btnPrenotaOmbrellone;
    @FXML
    private Button btnRicaricaConto;
    @FXML
    private Button btnPrenotaAttività;
    @FXML
    private Button btnLogout;

    @FXML
    private StackPane tabcenter;

    public static Cliente getC() {
        return c;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labInt.setText(c.getCognome() + " " + c.getNome());
        lbNomeCliente.setText(c.getCognome() + " " + c.getNome());
        lbResidenza.setText(c.getResidenza());
        lbTelefono.setText(c.getTelefono());
        lbEmailCliente.setText(c.getEmail());

    }

    public void prenotaOmbrellone(ActionEvent event) {

        AnchorPane Tabombrelloni = null;
        try {
            Tabombrelloni = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("prenotazioneOmbrellone-view.fxml"));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(Tabombrelloni);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void prenotaAttivita(ActionEvent event) {

        AnchorPane tabAttivita = null;
        try {
            tabAttivita = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("prenotazioneAttivita-view.fxml"));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(tabAttivita);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ricaricaConto(ActionEvent event) {
        AnchorPane tabAttivita = null;
        try {
            tabAttivita = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("creaNuovaComanda.fxml"));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(tabAttivita);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Chalet.getCurrentStage();
        stage.setTitle("Casotto!");
        stage.setScene(scene);
        stage.show();
        Chalet.setCurrentStage(stage);
    }

    public void prenotaMateriale(ActionEvent event) {
        AnchorPane tabAttivita = null;
        try {
            tabAttivita = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("prenotazioneMeteriale-view.fxml"));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(tabAttivita);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void prenotaRistorazione(ActionEvent event) {
        AnchorPane tabAttivita = null;
        try {
            String om = Casotto.getInstance().getOmbrelloneIdCliente(c.getId());
            Ombrellone ombrellone = Casotto.getInstance().getOmbrellone(om);
            ComandaRistorazione cr = new ComandaRistorazione(ombrellone, c);
            CreaNuovaComanda.setCr(cr);
            tabAttivita = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/creaNuovaComanda.fxml"));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(tabAttivita);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
