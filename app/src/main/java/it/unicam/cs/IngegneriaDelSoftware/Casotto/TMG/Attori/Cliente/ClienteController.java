package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Cliente;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.CreaNuovaComanda;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Chalet;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione.ComandaRistorazione;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {
    static Cliente c;

    public static void setC(Cliente cliente) {
        c = cliente;
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

    @FXML
    public void prenotaOmbrellone() {

        AnchorPane Tabombrelloni;
        try {
            Tabombrelloni = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("prenotazioneOmbrellone-view.fxml")));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(Tabombrelloni);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void prenotaAttivita() {

        AnchorPane tabAttivita;
        try {
            tabAttivita = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("prenotazioneAttivita-view.fxml")));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(tabAttivita);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void Logout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Chalet.getCurrentStage();
        stage.setTitle("Casotto!");
        stage.setScene(scene);
        stage.show();
        Chalet.setCurrentStage(stage);
    }

    @FXML
    public void prenotaMateriale() {
        AnchorPane tabAttivita;
        try {
            tabAttivita = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("prenotazioneMeteriale-view.fxml")));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(tabAttivita);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void prenotaRistorazione() {
        AnchorPane tabAttivita;
        try {
            String om = Casotto.getInstance().getOmbrelloneIdCliente(c.getId());
            Ombrellone ombrellone = Casotto.getInstance().getOmbrellone(om);
            ComandaRistorazione cr = new ComandaRistorazione(ombrellone, c);
            CreaNuovaComanda.setCr(cr);
            tabAttivita = FXMLLoader.load(Objects.requireNonNull(Chalet.class.getResource("Dipendenti/creaNuovaComanda.fxml")));
            tabcenter.getChildren().clear();
            tabcenter.getChildren().add(tabAttivita);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
