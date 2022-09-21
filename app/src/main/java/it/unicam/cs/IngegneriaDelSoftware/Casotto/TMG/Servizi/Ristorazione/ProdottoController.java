package it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Servizi.Ristorazione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.TMG.Attori.Dipendete.CreaNuovaComanda;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProdottoController implements Initializable {


    private static Prodotto prodotto;
    @FXML
    private Label LbCosto;

    @FXML
    private Label LbNomeProdotto;

    @FXML
    private Spinner<Integer> spQuantita;


    public static void setP(Prodotto p) {
        prodotto = p;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (prodotto != null) {
            LbNomeProdotto.setText(prodotto.getNome());
            LbCosto.setText(String.valueOf(prodotto.getPrezzo()));
            SpinnerValueFactory<Integer> quantita = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
            spQuantita.setValueFactory(quantita);
        }
    }

    //Restituisce la stringa contenente la prenotazione
    @FXML
    void prenota() {
        prodotto.setQuantita(spQuantita.getValue());
        CreaNuovaComanda.aggiungiProdotto(prodotto);
    }

}
