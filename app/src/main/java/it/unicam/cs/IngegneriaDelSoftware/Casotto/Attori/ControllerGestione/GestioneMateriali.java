package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;
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
import java.util.ResourceBundle;

public class GestioneMateriali implements Initializable {

    private ObservableList<Materiale> listaMateriali = FXCollections.observableArrayList();

    public Button btnAggiornaMateriale;
    @FXML
    private Button btnAggiungiMateriale;

    @FXML
    private Button btnRimuoviMateriale;

    @FXML
    private TableColumn<Materiale, Float> materialeCosto;

    @FXML
    private TableColumn<Materiale, String> materialeId;

    @FXML
    private TableColumn<Materiale, String> materialeNome;

    @FXML
    private TableColumn<Materiale, Integer> materialeQuantita;

    @FXML
    private Spinner<Integer> spModificaQuantita;

    @FXML
    private Spinner<Integer> spNuovaQauntita;

    @FXML
    private Spinner<Double> spNuvoCosto;

    @FXML
    private TableView<Materiale> tableMateriali;

    @FXML
    private TextField tfNuovoMateriale;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaMateriali.clear();
        listaMateriali.addAll(Casotto.getInstance().getMagazzino());

        materialeId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        materialeNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        materialeCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        materialeQuantita.setCellValueFactory(new PropertyValueFactory<>("Quantita"));

        tableMateriali.setItems(listaMateriali);

        SpinnerValueFactory<Integer> quantita = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1, 1);
        spNuovaQauntita.setValueFactory(quantita);

        SpinnerValueFactory<Double> Costo = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0, 0.5);
        spNuvoCosto.setValueFactory(Costo);

        SpinnerValueFactory<Integer> quan = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1, 1);
        spModificaQuantita.setValueFactory(quan);
    }

    @FXML
    void aggiungiMateriale(ActionEvent event) throws SQLException {
        String dettagli = "Materiale: \n nome:" + tfNuovoMateriale.getText() + "\n quantità:" + spNuovaQauntita.getValue() + "\n prezzo:"
                + spNuvoCosto.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, dettagli + " \n procedi con l'aggiunta dell materiale?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Conferma aggiunta Materiale");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            Materiale materiale = new Materiale(tfNuovoMateriale.getText(), spNuvoCosto.getValue().floatValue(), spNuovaQauntita.getValue());
            Connection con = Database.getConnection();
            String Query = "INSERT INTO Materiale(Idmateriale, Nome, Quantita, Costo)  VALUES (" +
                    "'" + materiale.getId() + "'," +
                    "'" + materiale.getNome() + "',"
                    + "'" + materiale.getQuantita() + "'," +
                    "'" + materiale.getCosto() + "');";
            con.createStatement().executeUpdate(Query);
            listaMateriali.add(materiale);
        }
    }

    @FXML
    void rimuoviMateriale(ActionEvent event) throws SQLException {
        Materiale materiale = tableMateriali.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, materiale.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Materiale");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //rimuovo il maeriale dal db
            Connection con = Database.getConnection();
            String Query = "DELETE FROM Materiale WHERE IdMateriale = '" +
                    materiale.getId() + "';";
            con.createStatement().executeUpdate(Query);

            listaMateriali.remove(materiale);

        }
    }

    public void aggiornaQauntitaMateriale(ActionEvent event) throws SQLException {
        Materiale materiale = tableMateriali.getSelectionModel().getSelectedItem();
        String det = materiale.toString() + " aggiungere " + spModificaQuantita.getValue() + "componenti?";
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, det, ButtonType.APPLY);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.APPLY) {
            Connection con = Database.getConnection();
            String query = "UPDATE Materiale SET Quantita = Quantita +" + spModificaQuantita.getValue() + " Where IdMateriale='" + materiale.getId() + "'";
            con.createStatement().executeUpdate(query);
            this.listaMateriali.clear();
            this.listaMateriali.addAll(Casotto.getInstance().getMagazzino());

        }

    }
}

