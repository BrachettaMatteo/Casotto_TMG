package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class GestoreController implements Initializable {


    private static Gestore g;


    public Button btnGestioneClienti;
    public Button btnGestionePrenotazioni;
    public Button btnGestioneAttivita;
    public Label lbLocation;

    @FXML
    private StackPane board;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.Home();
    }

    public static Gestore getG() {
        return g;
    }

    public static void setG(Gestore g) {
        GestoreController.g = g;
    }

    @FXML
    void btnGestioneDipendenti() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestionePersonale.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Dipendenti");

    }

    @FXML
    void gestioneMateriali() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneMateriali.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Materiali");
    }

    @FXML
    void gestioneRistorazione() {
        AnchorPane Tab ;
        try {
            Tab =  FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneComande.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Prenotazioni");
    }

    public void gestioneSpiaggia() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneOmbrelloni.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Spiaggia");
    }

    public void btnGestioneClienti() {
        AnchorPane Tab ;
        try {
            Tab =  FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneClienti.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }

        lbLocation.setText("Gestione Clienti");
    }

    public void btnGestionePrenotazioni() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestionePrenotazioni.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Prenotazioni");
    }

    public void btnGestioneAttivita() {
        AnchorPane Tab ;
        try {
            Tab =  FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneAttivita.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Gestione Attivita");
    }

    public void Logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Chalet.getCurrentStage();
        stage.setTitle("Casotto!");
        stage.setScene(scene);
        stage.show();
        Chalet.setCurrentStage(stage);
    }

    public void Home() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestoreHome.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbLocation.setText("Home");
    }
}
