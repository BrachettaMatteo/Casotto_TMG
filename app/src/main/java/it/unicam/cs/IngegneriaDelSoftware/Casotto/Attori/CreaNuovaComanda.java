package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.ComandaRistorazione;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Prodotto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreaNuovaComanda implements Initializable {
    private static ComandaRistorazione cr;
    public Spinner<Integer> spQuantita;
    public ComboBox<String> comboNomeProdotto;
    public Button btnAggiungi;
    public Label lbcosto;
    @FXML
    private Label lbDettagliComanda;
    @FXML
    private Button btnPrenota;
    ObservableList<String> ListaProdotti = FXCollections.observableArrayList();


    public static void aggiungiProdotto(Prodotto p) {
        cr.aggiungiProdotto(p);
    }

    public static void setCr(ComandaRistorazione cr) {
        CreaNuovaComanda.cr = cr;
    }

    public static ComandaRistorazione getCr() {
        return cr;
    }

    @FXML
    void prenotaComanda(ActionEvent event) {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION,"riepilogo:\n"+cr.toString(),ButtonType.APPLY);
        alert.showAndWait();
        if(alert.getResult()== ButtonType.APPLY){
            cr.chiudiComanda();
            cr.rimuoviProdotti();
            this.lbDettagliComanda.setText("");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Prodotto p : Casotto.getInstance().getMenu()) {
            ListaProdotti.add(p.getNome());
        }
        comboNomeProdotto.setItems(ListaProdotti);

        SpinnerValueFactory<Integer> quantita = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        spQuantita.setValueFactory(quantita);

    }

    public void aggiungiProdotto(ActionEvent event) throws SQLException {
        Prodotto p = Casotto.getInstance().getProdotto(comboNomeProdotto.getValue());
        p.setQuantita(spQuantita.getValue());
        cr.aggiungiProdotto(p);
        String text = lbDettagliComanda.getText();
        if(text.isEmpty()){
            lbDettagliComanda.setText(comboNomeProdotto.getValue()+" "+spQuantita.getValue()+" ,\n ");
        }else
            lbDettagliComanda.setText(text+comboNomeProdotto.getValue()+" "+spQuantita.getValue()+" ,\n ");
        comboNomeProdotto.setItems(ListaProdotti);
        SpinnerValueFactory<Integer> quantita = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        spQuantita.setValueFactory(quantita);
    }

    public void setCosto(ActionEvent event) throws SQLException {

        lbcosto.setText(String.valueOf(Casotto.getInstance().getProdotto(comboNomeProdotto.getValue()).getPrezzo())+"€");

    }

    @Override
    public String toString() {
        return "CreaNuovaComanda{" +
                "spQuantita=" + spQuantita +
                ", comboNomeProdotto=" + comboNomeProdotto +
                ", btnAggiungi=" + btnAggiungi +
                ", lbcosto=" + lbcosto +
                ", lbDettagliComanda=" + lbDettagliComanda +
                ", btnPrenota=" + btnPrenota +
                ", ListaProdotti=" + ListaProdotti +
                '}';
    }
}
