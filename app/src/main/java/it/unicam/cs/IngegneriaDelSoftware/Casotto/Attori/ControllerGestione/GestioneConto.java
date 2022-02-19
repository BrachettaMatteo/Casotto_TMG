package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GestioneConto implements Initializable {

    @FXML
    private TextField Identificativo;

    @FXML
    private Spinner<Double> Importo;

    @FXML
    private Button btnRicaricaConto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Double> imp = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0, 0.5);
        Importo.setValueFactory(imp);
    }

    @FXML
    void ricaricaConto(ActionEvent event) {
        String det = "identificativo Cliente: " + Identificativo.getText() + " importo:" + Importo.getValue() + "€";
        Alert alert;
        alert = new Alert(Alert.AlertType.INFORMATION, det, ButtonType.APPLY);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.APPLY)) {
            Cliente c;
            if (Identificativo.getText().length() == 36) {
                c = Casotto.getInstance().getCliente(Identificativo.getText());
            } else {
                c = Casotto.getInstance().getClienteToNomeUtente(Identificativo.getText());
            }
            if (c != null) {
                c.ricarica(Importo.getValue().floatValue());
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "Errore identificativo", ButtonType.OK);
                alert.setTitle("Errore inserimento dati");
                alert.show();
            }
            Identificativo.clear();
            SpinnerValueFactory<Double> imp = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0, 0.5);
            Importo.setValueFactory(imp);

        }

    }

}
