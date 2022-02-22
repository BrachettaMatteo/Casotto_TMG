package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ClienteController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione.IntervalloDate;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class prenotazioneOmbrelloneController implements Initializable {

    private PrenotazioneOmbrellone bozza = null;
    public Label lbPrezzo;
    public ToggleButton tbInizio;
    public ToggleButton tbFine;
    public ToggleButton tbInizio1;
    public ToggleButton tbFine1;
    @FXML
    private DatePicker dataFine;

    @FXML
    private DatePicker dataInizio;

    @FXML
    private ComboBox<Integer> numOmbrellone;

    private ObservableList<Integer> nombreloni = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Casotto.getInstance().getOmbrelloni();
            nombreloni.addAll(Casotto.getInstance().getNumeroOmbrelloni());
            numOmbrellone.setItems((nombreloni));
            dataInizio.setValue(LocalDate.now());
            ToggleGroup tbI = new ToggleGroup();
            tbInizio.setToggleGroup(tbI);
            tbFine.setToggleGroup(tbI);

            ToggleGroup tbF = new ToggleGroup();
            tbInizio1.setToggleGroup(tbF);
            tbFine1.setToggleGroup(tbF);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean verificaData(LocalDate item, ArrayList<IntervalloDate> occupato) {
        for (IntervalloDate o : occupato) {
            LocalDateTime l = LocalDateTime.of(item.getYear(), item.getMonthValue(), item.getDayOfMonth(), 8, 0, 0);
            Timestamp L = Timestamp.valueOf(l);
            if (L == o.getFine() || L == o.getInizio())
                return true;
            if (o.getInizio().before(L) && o.getFine().after(L)) {
                return true;
            }
        }
        return false;
    }

    public void prenota(ActionEvent event) {
        this.calolaConto();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Riepilogo dati:\n" + bozza.toString(), ButtonType.APPLY, ButtonType.CLOSE);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.APPLY) {
            this.bozza.chiudiPrenotazione();
            this.bozza = null;
        }

    }


    public void calolaConto() {
        String cliente = ClienteController.getC().getId();
        LocalDateTime inizio;
        LocalDateTime fine;
        //creo data
        if (tbInizio.isSelected()) {
            inizio = LocalDateTime.of(dataInizio.getValue().getYear(), dataInizio.getValue().getMonthValue(), dataInizio.getValue().getDayOfMonth(), 8, 0, 0);
        } else {
            inizio = LocalDateTime.of(dataInizio.getValue().getYear(), dataInizio.getValue().getMonthValue(), dataInizio.getValue().getDayOfMonth(), 14, 0, 0);
        }
        if (tbInizio1.isSelected()) {
            fine = LocalDateTime.of(dataFine.getValue().getYear(), dataFine.getValue().getMonthValue(), dataFine.getValue().getDayOfMonth(), 14, 0, 0);
        } else {
            fine = LocalDateTime.of(dataFine.getValue().getYear(), dataFine.getValue().getMonthValue(), dataFine.getValue().getDayOfMonth(), 19, 0, 0);
        }
        if (bozza == null || Casotto.getInstance().getOmbrelloneToNumero(numOmbrellone.getValue()).equals(bozza.getIdOmbrellone()))
            bozza = new PrenotazioneOmbrellone(cliente, Casotto.getInstance().getidOmbrellone(numOmbrellone.getValue()), fine, inizio);
        else {
            //cambio data
            bozza.setDataFine(fine);
            bozza.setDataInizio(inizio);
        }

        lbPrezzo.setText(String.valueOf(bozza.calcolaConto()));
    }

    public void setDate(ActionEvent event) {
/*
        Callback<DatePicker, DateCell> dayCellFactory = this.eliminaDateoccupate();
        dataFine.setDayCellFactory(dayCellFactory);
        dataInizio.setDayCellFactory(dayCellFactory);*/
        dataInizio.setValue(LocalDate.now());
        dataFine.setValue(LocalDate.now());
        dataFine.setDayCellFactory(eliminaDate());
        dataInizio.setDayCellFactory(eliminaDate());
    }

    public void setDateFine(ActionEvent event) {
        dataFine.setDayCellFactory(eliminaDate());
    }


    private Callback<DatePicker, DateCell> eliminaDate() {
        ArrayList<IntervalloDate> io = new ArrayList<>();
        try {
            Connection con = Database.getConnection();
            String query = "SELECT Inizio , Fine from Prenotazione WHERE idOmbrellone='" +
                    Casotto.getInstance().getOmbrelloneToNumero(numOmbrellone.getValue()) + "'";
            ResultSet rs = con.createStatement().executeQuery(query);

            while (rs.next()) {
                io.add(new IntervalloDate(rs.getTimestamp("Inizio"), rs.getTimestamp("Fine")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate inizio = dataInizio.getValue();
                        LocalDate today = LocalDate.now();
                        if (empty || item.compareTo(inizio) < 0 || verificaData(item, io) || item.compareTo(today) < 0) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }

                    }

                };
            }

        };
        return callB;
    }
}
