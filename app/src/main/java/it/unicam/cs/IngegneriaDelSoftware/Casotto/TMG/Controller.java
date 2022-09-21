package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Bagnino.bagninoController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Cassiere.CassiereController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Cliente.ClienteController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.Gestore;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.GestoreController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Service.Database;
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
                GestoreController.setG(Casotto.getInstance().getGestore(Username.getText()));
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

    public void registrazione(ActionEvent event) {
        try {
            root = FXMLLoader.load(Chalet.class.getResource("Registrazione.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Registrazione");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
