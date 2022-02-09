package it.unicam.cs.IngegneriaDelSoftware.Casotto;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Gestore;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Cliente.ControllerCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        if (Username.getText().equals("gestore")) {
        gestore = new Gestore(Username.getText(), Username.getText(), "camerino", 1234567890, Username.getText(), "gestore@gestore.it");
        root = FXMLLoader.load(Chalet.class.getResource("gestore-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Dashboard Gestore");
        stage.show();
        }
        if(Username.getText().equals("Bagnino")){
            root = FXMLLoader.load(Chalet.class.getResource("bagnino-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Dashboard Bagnino");
            stage.show();
        }
        if(Username.getText().equals("Cliente")){
            root = FXMLLoader.load(Chalet.class.getResource("cliente-view.fxml"));
          
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Casotto");
            stage.show();
        }
    }
}
