package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Bagnino;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class bagninoController implements Initializable {
    public static Bagnino bagnino;

    public Button btnMateriali;
    public Button btnHome;
    public StackPane board;


    public static void setBagnino(Bagnino b) {
        bagnino = b;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (bagnino != null) {
            this.home();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Errore sistema caricamento bagnino");
            alert.show();
        }

    }

    public void home() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestionePrenotazioneBalneare.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void attivita() {
        AnchorPane Tab ;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneAttivita.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ombrelloni() {
        AnchorPane Tab;
        try {
            Tab =  FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneOmbrelloni.fxml")));
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

    public void materiali() {
        AnchorPane Tab;
        try {
            Tab = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/gestioneMateriali.fxml")));
            board.getChildren().clear();
            board.getChildren().add(Tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
