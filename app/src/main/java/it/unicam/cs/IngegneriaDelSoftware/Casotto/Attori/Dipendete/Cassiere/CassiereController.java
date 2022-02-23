package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Cassiere;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CassiereController implements Initializable {
    private static Cassiere c;

    @FXML
    private StackPane board;
    public static void setC(Cassiere cassiereToNomeUtente) {
        c = cassiereToNomeUtente;
    }

    public static Cassiere getCassiere() {
        return c;
    }

    public static void setCassiere(Cassiere cassiere) {
        c = cassiere;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void gestioneClienti() {
        AnchorPane Tab ;
        try {
            Tab =  FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneClienti.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gestioneComande() {
        AnchorPane Tab;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneComande.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ricaricaConto() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("ricaricaConto.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
