package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
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

public class GestoreController implements Initializable {


    public Button btnGestioneClienti;
    public Button btnGestionePrenotazioni;
    public Button btnGestioneAttivita;
    public Label lbLocation;

    @FXML
    private StackPane board;

    @FXML
    private Button btnGestioneDipendenti;

    @FXML
    private Button btnGestioneMateriali;

    @FXML
    private Button btnGestioneRistorazione;

    @FXML
    private Button btnGestioneSpiaggia;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.Home();
    }

    @FXML
    void btnGestioneDipendenti(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestionePersonale.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Dipendenti");

    }

    @FXML
    void gestioneMateriali(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestioneMateriali.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Materiali");
    }

    @FXML
    void gestioneRistorazione(ActionEvent event) {
        lbLocation.setText("Gestione Ristorazione");
    }

    public void gestioneSpiaggia(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestioneOmbrelloni.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Spiaggia");
    }

    public void btnGestioneClienti(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestioneClienti.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }

        lbLocation.setText("Gestione Clienti");
    }

    public void btnGestionePrenotazioni(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestionePrenotazioni.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Prenotazioni");
    }

    public void btnGestioneAttivita(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestioneAttivita.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Attivita");
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

    public void Home() {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("gestoreHome.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Home");
    }
}
