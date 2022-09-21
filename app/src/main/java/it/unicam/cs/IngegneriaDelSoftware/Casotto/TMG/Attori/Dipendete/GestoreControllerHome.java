package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione.Prodotto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GestoreControllerHome implements Initializable {

    public VBox boxSetTariffe;
    public TextArea taDescrizioneProdotto;
    public Button btnCreaProdotto;
    public Spinner<Double> spPrezzoProdotto;
    public TextField tfNomeProdotto;
    public VBox boxMenu;
    @FXML
    private Spinner<Integer> spFila;

    @FXML
    private Spinner<Integer> spNumeroFileSpiaggia;

    @FXML
    private Spinner<Integer> spNumeroOmbrelloneSpiaggia;

    @FXML
    private Spinner<Double> spTariffa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> fil = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        spNumeroFileSpiaggia.setValueFactory(fil);
        SpinnerValueFactory<Integer> Nombrelloni = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        spNumeroOmbrelloneSpiaggia.setValueFactory(Nombrelloni);

        if (Casotto.getInstance().getOmbrelloni().isEmpty()) {
            boxSetTariffe.setVisible(false);
        } else {
            SpinnerValueFactory<Double> tar = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1, 0.5);
            spTariffa.setValueFactory(tar);
            int max = Casotto.getInstance().getFilaMax();
            SpinnerValueFactory<Integer> fill = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max, 1, 1);
            spFila.setValueFactory(fill);
        }
        this.preparaBoxProdotto();
    }

    public void modificaTariffa() {
        String dettaglio = "Riepilogo: \n fila: " + spFila.getValue() + "\n costo: " + spTariffa.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, dettaglio + " \n procedere?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Aggiornamento tariffe Ombrelloni");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            GestoreController.getG().setTariffe(spFila.getValue(), spTariffa.getValue().floatValue());
        }
    }

    public void creaSpiaggia() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "La creazione di una nuova Spiaggia elimina tutti gli ombrelloni presenti. " +
                "\n creare Spiaggia di " + spNumeroOmbrelloneSpiaggia.getValue() + " " +
                "ombrelloni per " + spNumeroFileSpiaggia.getValue() + "\nPorcedere con la creazione di una nuova spiaggia?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Creazione nuova Spiaggia");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {

            GestoreController.getG().creaSpiaggia(spNumeroFileSpiaggia.getValue(), spNumeroOmbrelloneSpiaggia.getValue());

            SpinnerValueFactory<Double> tar = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1, 0.5);
            spTariffa.setValueFactory(tar);
            int max = Casotto.getInstance().getOmbrelloni().size();
            SpinnerValueFactory<Integer> fill = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max, 1, 1);
            spFila.setValueFactory(fill);
            boxSetTariffe.setVisible(true);

            this.preparaBoxProdotto();

        }
    }

    public void creaProdotto() {
        Prodotto p = new Prodotto(tfNomeProdotto.getText(), spPrezzoProdotto.getValue().floatValue(), taDescrizioneProdotto.getText());
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, p.toString(), ButtonType.APPLY);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.APPLY)) {
            if (Casotto.getInstance().aggiungiProdotto(p)) {
                alert = new Alert(Alert.AlertType.INFORMATION, "prodotto aggiunto");
                alert.setTitle("conferma prodotto aggiunto");
                alert.show();
                this.preparaBoxProdotto();
            } else {
                alert = new Alert(Alert.AlertType.ERROR, "ERRORE, prodotto non aggiunto");
                alert.setTitle("ERRORE");
                alert.show();
            }
        }
    }

    private void preparaBoxProdotto() {
        tfNomeProdotto.clear();
        taDescrizioneProdotto.clear();
        SpinnerValueFactory<Double> costo = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 1000, 1, 0.5);
        spPrezzoProdotto.setValueFactory(costo);
        spPrezzoProdotto.setEditable(true);
    }
}
