package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente.ClienteController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.ControllerGestione.gestioneComande;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Ristorazione.ComandaRistorazione;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Ristorazione.Prodotto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CreaNuovaComanda implements Initializable {
    private static ComandaRistorazione cr;
    public Spinner<Integer> spQuantita;
    public ComboBox<String> comboNomeProdotto;
    public Button btnAggiungi;
    public Label lbcosto;
    @FXML
    private Label lbDettagliComanda;

    ObservableList<String> ListaProdotti = FXCollections.observableArrayList();


    public static void aggiungiProdotto(Prodotto p) {
        cr.aggiungiProdotto(p);
    }

    public static void setCr(ComandaRistorazione cr) {
        CreaNuovaComanda.cr = cr;
    }


    @FXML
    void prenotaComanda() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "riepilogo:\n" + cr.toString(), ButtonType.APPLY);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.APPLY) {
            ClienteController.getC().prenotaServizioRistorazione(cr);
            gestioneComande.aggiornaComande();
            cr.rimuoviProdotti();
            this.lbDettagliComanda.setText("");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Casotto.getInstance().getMenu() != null) {
            for (Prodotto p : Casotto.getInstance().getMenu()) {
                ListaProdotti.add(p.getNome());
            }
            comboNomeProdotto.setItems(ListaProdotti);

            SpinnerValueFactory<Integer> quantita = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
            spQuantita.setValueFactory(quantita);

        }

    }

    public void aggiungiProdotto() {
        Prodotto p = Casotto.getInstance().getProdotto(comboNomeProdotto.getValue());
        p.setQuantita(spQuantita.getValue());
        cr.aggiungiProdotto(p);
        String text = lbDettagliComanda.getText();
        if (text.isEmpty()) {
            lbDettagliComanda.setText(comboNomeProdotto.getValue() + " " + spQuantita.getValue() + " ,\n ");
        } else
            lbDettagliComanda.setText(text + comboNomeProdotto.getValue() + " " + spQuantita.getValue() + " ,\n ");
        comboNomeProdotto.setItems(ListaProdotti);
        SpinnerValueFactory<Integer> quantita = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        spQuantita.setValueFactory(quantita);
    }

    public void setCosto() {

        lbcosto.setText(Casotto.getInstance().getProdotto(comboNomeProdotto.getValue()).getPrezzo() + "€");

    }

}
