package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CassiereController implements Initializable {
    private static Cassiere c;

    @FXML
    private StackPane board;

    @FXML
    private Button btnGestioneClienti;

    @FXML
    private Button btnGestioneComande;

    @FXML
    private Button btnRicaricaConto;

    public static void setC(Cassiere cassiereToNomeUtente) {
        c=cassiereToNomeUtente;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void gestioneClienti(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestioneClienti.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gestioneComande(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("Dipendenti/gestioneComande.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ricaricaConto(ActionEvent event) {
        AnchorPane Tab = null;
        try {
            Tab = (AnchorPane) FXMLLoader.load(Chalet.class.getResource("ricaricaConto.fxml"));
            board.getChildren().clear();
            board.getChildren().add(Tab);
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
}
