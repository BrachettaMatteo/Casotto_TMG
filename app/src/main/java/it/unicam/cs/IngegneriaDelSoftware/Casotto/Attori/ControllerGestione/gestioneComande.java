package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.CreaNuovaComanda;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.ComandaRistorazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class gestioneComande implements Initializable {

    public TableView<ComandaRistorazione> tabcomande;
    public TextField tfOmbrellone;
    public TextField tdCLiente;
    @FXML
    private Button btnCambiaStatus;

    @FXML
    private Button btnCreaNuovComanda;

    @FXML
    private ComboBox<String> cbCambiaStatus;

    @FXML
    private TableColumn<ComandaRistorazione, String> clCliente;

    @FXML
    private TableColumn<ComandaRistorazione, String> clComanda;

    @FXML
    private TableColumn<ComandaRistorazione, String> clListaProdotti;

    @FXML
    private TableColumn<ComandaRistorazione, String> clOmbrellone;

    @FXML
    private TableColumn<ComandaRistorazione, String> clStatus;
    private static ObservableList<ComandaRistorazione> listaComande = FXCollections.observableArrayList();
    private ObservableList<String> listaStatus = FXCollections.observableArrayList();

    @FXML
    void cambiaStatus(ActionEvent event) {
        ComandaRistorazione cr = tabcomande.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "impostare status:" + cbCambiaStatus.getValue() + " \nalla comanda: " + cr.getId(), ButtonType.APPLY);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.APPLY) {
            try {
                Connection con = Database.getConnection();
                String query = "UPDATE ComandaRistorazione set Status='" + cbCambiaStatus.getValue() + "'WHERE ID='" + cr.getId() + "'";
                con.createStatement().executeUpdate(query);
                aggiornaComande();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void creaNuovaComnda(ActionEvent event) throws IOException {
        ComandaRistorazione cr;
        if (tdCLiente.getText().length() == 36 && tfOmbrellone.getText().length() == 36) {
            cr = new ComandaRistorazione(Casotto.getInstance().getOmbrellone(tfOmbrellone.getText()),
                    Casotto.getInstance().getCliente(tdCLiente.getText()));
        } else {
            String idom = Casotto.getInstance().getOmbrelloneToNumero(
                    Integer.valueOf(tfOmbrellone.getText()));
            Ombrellone o = Casotto.getInstance().getOmbrellone(idom);
            cr = new ComandaRistorazione(o, Casotto.getInstance().getClienteToNomeUtente(tdCLiente.getText()));
        }

        CreaNuovaComanda.setCr(cr);
        FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("Dipendenti/creaNuovaComanda.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Comanda Ristorazione!");
        stage.setScene(scene);
        stage.show();
        Chalet.setCurrentStage(stage);
    }

    public static void aggiornaComande() {
        listaComande.clear();
        listaComande.addAll(Casotto.getInstance().getComandeRistorazione());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaComande.addAll(Casotto.getInstance().getComandeRistorazione());

        clComanda.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clCliente.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        clOmbrellone.setCellValueFactory(new PropertyValueFactory<>("IdOmbrellone"));
        clListaProdotti.setCellValueFactory(new PropertyValueFactory<>("listaProdotti"));
        clStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        tabcomande.setItems(listaComande);

        listaStatus.addAll("Consegnato", "daElaborare", "InLavorazione", "ProntaPerIlRitiro");
        cbCambiaStatus.setItems(listaStatus);
    }

    public void eliminaComanda(ActionEvent event) {
        ComandaRistorazione cr = tabcomande.getSelectionModel().getSelectedItem();
        Alert alert;
        alert= new Alert(Alert.AlertType.CONFIRMATION,"eliminare comanda:"+cr.toString(),ButtonType.APPLY);
        alert.showAndWait();
        if(alert.getResult()==ButtonType.APPLY){
            try {
                Connection con = Database.getConnection();
                String query = "DELETE FROM ComandaRistorazione where ID='"+cr.getId()+"'";
                con.createStatement().executeUpdate(query);
                aggiornaComande();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}
