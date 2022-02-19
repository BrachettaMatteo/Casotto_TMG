package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class GestioneAttivita implements Initializable {

    @FXML
    private DatePicker OrarioNuovaAttivita;

    @FXML
    private TableView<Attivita> tableAttivita;
    @FXML
    private TableColumn<Attivita, Float> attivitaCosto;

    @FXML
    private TableColumn<Attivita, String> attivitaId;

    @FXML
    private TableColumn<Attivita, String> attivitaNome;

    @FXML
    private TableColumn<Attivita, Timestamp> attivitaOrario;

    @FXML
    private TableColumn<Attivita, Integer> attivitaPartecipanti;

    @FXML
    private TableColumn<Attivita, Integer> attivitaPostiMax;

    @FXML
    private TableColumn<Attivita, Integer> attivitaPostiMin;

    @FXML
    private Button btnAggiungiAttivita;

    @FXML
    private Button btnRimuoviAttivita;

    @FXML
    private Spinner<Float> costonuovaAttivita;

    @FXML
    private Spinner<Integer> hNuovaAttivita;

    @FXML
    private Spinner<Integer> mNuovaAttivita;

    @FXML
    private TextField nomeNuovaAttvita;

    @FXML
    private Spinner<Integer> postiMaxNuovaAttivita;

    @FXML
    private Spinner<Integer> postiMinNuovaAttivita;

    private ObservableList<Attivita> ListaAttivita = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ListaAttivita.clear();

        ListaAttivita.addAll(Casotto.getInstance().getAttivita());

        attivitaId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        attivitaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        attivitaPostiMax.setCellValueFactory(new PropertyValueFactory<>("PostiMax"));
        attivitaPostiMin.setCellValueFactory(new PropertyValueFactory<>("PostiMin"));
        attivitaCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        attivitaOrario.setCellValueFactory(new PropertyValueFactory<>("Orario"));
        attivitaPartecipanti.setCellValueFactory(new PropertyValueFactory<>("Partecipanti"));
        tableAttivita.setItems(ListaAttivita);

        SpinnerValueFactory<Integer> h =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, 1, 1);
        h.setValue(LocalDateTime.now().getHour());
        hNuovaAttivita.setValueFactory(h);
        SpinnerValueFactory<Integer> m =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 1);
        m.setValue(LocalDateTime.now().getMinute());
        mNuovaAttivita.setValueFactory(m);


    }

    @FXML
    void aggiungiAttivita(ActionEvent event) {

        String dettagli = "attvita: \n nome:" + nomeNuovaAttvita.getText() + "\n postiMin: " + postiMinNuovaAttivita.getValue() +
                "\n posti Max:" + postiMaxNuovaAttivita.getValue() + "\n costo:" + costonuovaAttivita.getValue() +
                "\n Data: " + OrarioNuovaAttivita.getValue() + "\n orario: " + hNuovaAttivita.getValue() + ":" + mNuovaAttivita.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, dettagli + " \n procedi con l'aggiunta dell'attività?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Conferma aggiunta Attivita");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            LocalDateTime date = LocalDateTime.of(OrarioNuovaAttivita.getValue(), LocalTime.of(hNuovaAttivita.getValue(), mNuovaAttivita.getValue()));
            Attivita a = new Attivita(nomeNuovaAttvita.getText(), postiMaxNuovaAttivita.getValue(), postiMinNuovaAttivita.getValue(), date, costonuovaAttivita.getValue().floatValue());

            Connection con = null;
            try {
                con = Database.getConnection();
                int comp = a.getComponenti() != null ? a.getComponenti().size() : 0;
                String Query = "INSERT INTO Casotto.Attivita (Id, Nome, postiMax, postiMin, costo, orario, Componenti) VALUES (" +
                        "'" + a.getIdAttivita() + "'," +
                        "'" + a.getNome() + "',"
                        + "'" + a.getPostiMax() + "'," +
                        "'" + a.getPostiMin() + "'," +
                        "'" + a.getCosto() + "'," +
                        "'" + a.getOrario() + "'," +
                        "'" + comp + "');";

                con.createStatement().executeUpdate(Query);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ListaAttivita.add(a);
        }

    }

    @FXML
    void rimuoviAttivita(ActionEvent event) {
        Attivita attivita = tableAttivita.getSelectionModel().getSelectedItem();
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, attivita.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Attività");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //rimuovo l'attivita dal db
            Connection con = null;
            try {
                con = Database.getConnection();
                String Query = "DELETE FROM Casotto.Attivita WHERE (Id = '" +
                        attivita.getId() + "');";
                con.createStatement().executeUpdate(Query);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            ListaAttivita.remove(attivita);
            alert= new Alert(Alert.AlertType.INFORMATION, "Attivita Rimossa",ButtonType.OK);
            alert.setTitle("conferam Eliminazione");
            alert.show();
        }
    }

}
