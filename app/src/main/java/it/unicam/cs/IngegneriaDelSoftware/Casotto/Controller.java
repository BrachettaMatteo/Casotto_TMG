package it.unicam.cs.IngegneriaDelSoftware.Casotto;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.*;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    static Gestore gestore;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static Gestore getGestore() {
        return gestore;
    }

    @FXML
    protected void Login(ActionEvent event) throws IOException {
        String Ruolo = Database.login(Username.getText(), Password.getText());
        switch (Ruolo) {
            case "Bagnino": {
                bagninoController.setBagnino(Casotto.getInstance().getBagnino(Username.getText()));

                root = FXMLLoader.load(Chalet.class.getResource("bagnino-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Dashboard Bagnino");
                stage.show();
                break;
            }
            case "Gestore": {
                root = FXMLLoader.load(Chalet.class.getResource("gestore-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Dashboard Gestore");
                Chalet.setCurrentStage(stage);
                stage.show();
                break;
            }
            case "Cliente": {
                ClienteController.setC(Casotto.getInstance().getClienteToNomeUtente(Username.getText()));
                root = FXMLLoader.load(Chalet.class.getResource("cliente-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Casotto");
                stage.show();
                break;
            }

            case "Cassiere": {
                CassiereController.setC(Casotto.getInstance().getCassiereToNomeUtente(Username.getText()));
                root = FXMLLoader.load(Chalet.class.getResource("Cassiere-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Casotto");
                stage.show();
                break;
            }
            default: {
                Alert alert = new Alert(Alert.AlertType.ERROR, "nome utente o password errati", ButtonType.OK);
                alert.show();
                this.Username.clear();
                this.Password.clear();
            }
        }

    }

    public void Registrazione() {

    }
}
