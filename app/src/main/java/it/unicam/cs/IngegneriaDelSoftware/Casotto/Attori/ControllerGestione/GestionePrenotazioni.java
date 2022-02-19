package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.PrenotazioneOmbrellone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class GestionePrenotazioni implements Initializable {
    @FXML
    public TableColumn<PrenotazioneOmbrellone, Timestamp> prenotazioneDataInizio;
    @FXML
    private TableColumn<PrenotazioneOmbrellone, String> prenotazioneCliente;

    @FXML
    private TableColumn<PrenotazioneOmbrellone, String> prenotazioneDataFine;

    @FXML
    private TableColumn<PrenotazioneOmbrellone, String> prenotazioneId;

    @FXML
    private TableColumn<PrenotazioneOmbrellone, String> prenotazioneOmbrellone;

    @FXML
    private TableView<PrenotazioneOmbrellone> tablePrenotazione;

    private ObservableList<PrenotazioneOmbrellone> ListaPrenotazioni = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaPrenotazioni.clear();
        ListaPrenotazioni.addAll(Casotto.getInstance().getPrenotazioni());

        prenotazioneId.setCellValueFactory(new PropertyValueFactory<>("IdComanda"));
        prenotazioneCliente.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        prenotazioneOmbrellone.setCellValueFactory(new PropertyValueFactory<>("IdOmbrellone"));
        prenotazioneDataInizio.setCellValueFactory(new PropertyValueFactory<>("dataInizio"));
        prenotazioneDataFine.setCellValueFactory(new PropertyValueFactory<>("dataFine"));

        tablePrenotazione.setItems(ListaPrenotazioni);


    }
}
