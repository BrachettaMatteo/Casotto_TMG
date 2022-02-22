package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;


public class GestioneOmbrelloniController implements Initializable {

    ObservableList<Ombrellone> ListaOmbrelloni = FXCollections.observableArrayList();
    @FXML
    private Button btnAggiungiOmbrellone;

    @FXML
    private Button btnRimuoviOmbrellone;

    @FXML
    private TableView<Ombrellone> tableOmbrelloni;

    @FXML
    private TableColumn<Ombrellone, String> ombrelloneDipso;

    @FXML
    private TableColumn<Ombrellone, Integer> ombrelloneFila;

    @FXML
    private TableColumn<Ombrellone, Timestamp> ombrelloneFine;

    @FXML
    private TableColumn<Ombrellone, String> ombrelloneID;

    @FXML
    private TableColumn<Ombrellone, Integer> ombrelloneNumero;

    @FXML
    private TableColumn<Ombrellone, Float> ombrelloneTariffa;

    @FXML
    private Spinner<Double> spinCostoNuovoOmbrellone;

    @FXML
    private Spinner<Integer> spinFilaNuovoOmbrellone;

    @FXML
    private Spinner<Integer> spinNumeroNuovoOmbrellone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaOmbrelloni.clear();
        ListaOmbrelloni.addAll(Casotto.getInstance().getOmbrelloni());

        ombrelloneID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        ombrelloneDipso.setCellValueFactory(new PropertyValueFactory<>("Disponibilita"));
        ombrelloneFila.setCellValueFactory(new PropertyValueFactory<>("Fila"));
        ombrelloneNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        ombrelloneTariffa.setCellValueFactory(new PropertyValueFactory<>("Tariffa"));
        ombrelloneFine.setCellValueFactory(new PropertyValueFactory<>("Fine"));
        tableOmbrelloni.setItems(ListaOmbrelloni);
        if (Casotto.getInstance().getOmbrelloni().size() != 0)
            this.aggiornaValori();

    }

    @FXML
    void aggiungiOmbrellone(ActionEvent event) {
        Ombrellone o = new Ombrellone(spinFilaNuovoOmbrellone.getValue(), spinCostoNuovoOmbrellone.getValue().floatValue(), spinNumeroNuovoOmbrellone.getValue());
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, o.toString(), ButtonType.APPLY);
        alert.setTitle("Aggiunta Ombrellone");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.APPLY) {
            if (Casotto.getInstance().aggiungiOmbrellone(o))
                ListaOmbrelloni.add(o);

        }
    }

    private void aggiornaValori() {
        if (Casotto.getInstance().getOmbrelloni().size() != 0) {
            SpinnerValueFactory<Double> costo = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 1, 0.5);
            spinCostoNuovoOmbrellone.setValueFactory(costo);

            int max = Casotto.getInstance().getOmbrelloni().size() + 1;
            SpinnerValueFactory<Integer> numero = new SpinnerValueFactory.IntegerSpinnerValueFactory(max, max + 100, max, 1);
            spinNumeroNuovoOmbrellone.setValueFactory(numero);

            int maxFila = Casotto.getInstance().getFilaMax();
            SpinnerValueFactory<Integer> fila = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, maxFila, 1, 1);
            spinFilaNuovoOmbrellone.setValueFactory(fila);
        }
    }

    @FXML
    void rimuoviOmbrellone(ActionEvent event) {
        Ombrellone ombrellone = tableOmbrelloni.getSelectionModel().getSelectedItem();
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, ombrellone.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Ombrellone");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            ListaOmbrelloni.remove(ombrellone);
            //rimuovo il Dipendente dal db
            Connection con = null;
            try {
                con = Database.getConnection();

                String Query = "DELETE FROM Ombrellone WHERE (ID = '" +
                        ombrellone.getId() + "');";
                con.createStatement().executeUpdate(Query);
                //set tariffe
                this.aggiornaValori();
            } catch (SQLException e) {
                alert = new Alert(Alert.AlertType.ERROR, "errore sitema", ButtonType.OK);
                alert.show();
                e.printStackTrace();
            }
        }
    }

}
