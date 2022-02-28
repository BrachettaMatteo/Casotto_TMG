package it.unicam.cs.IngegneriaDelSoftware.Casotto;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class registrazione {

    @FXML
    private Button btnExit;

    @FXML
    private Button btnRegistra;

    @FXML
    private TextField lbCognome;

    @FXML
    private TextField lbEmail;

    @FXML
    private TextField lbResidenza;

    @FXML
    private TextField lbTelefono;

    @FXML
    private TextField lbnome;

    @FXML
    void Registrazione(ActionEvent event) {
        String nomeUtente = lbnome.getText() + lbCognome.getText();
        while (Database.checkUsername(nomeUtente)) {
            nomeUtente = nomeUtente + "1";
        }
        Cliente cl = new Cliente(lbnome.getText(), lbCognome.getText(), lbResidenza.getText(), lbTelefono.getText(), nomeUtente, lbEmail.getText());

    }

    @FXML
    void logout(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("login-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            Stage stage = Chalet.getCurrentStage();
            stage.setTitle("Casotto!");
            stage.setScene(scene);
            stage.show();
            Chalet.setCurrentStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
