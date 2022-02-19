package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ClienteController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class prenotazioneBalneareController implements Initializable {

    public DatePicker dataFine;
    public Spinner<Integer> spinnereQuantita;
    public Label lbTotale;
    private ComandaBalneare bozza;

    public ComboBox<Integer> comboOmbrelloni;
    public ComboBox<String> comboMateriali;

    ObservableList<Integer> numOmbrelloni = FXCollections.observableArrayList();
    ObservableList<String> nomeMateriale = FXCollections.observableArrayList();
    private ArrayList<Materiale> Materiali = new ArrayList<>();
    private ArrayList<Ombrellone> Ombrelloni = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cliente cliente = ClienteController.getC();
        Ombrelloni.clear();
        Ombrelloni.addAll(Casotto.getInstance().getOmbrelloniCliente(cliente.getId()));
        numOmbrelloni.clear();
        for (Ombrellone o : Ombrelloni)
            numOmbrelloni.add(o.getNumero());

        comboOmbrelloni.setItems(numOmbrelloni);

        Materiali.clear();
        Materiali.addAll(Casotto.getInstance().getMagazzino());
        for (Materiale m : Materiali) {
            nomeMateriale.add(m.getNome());
        }
        comboMateriali.setItems(nomeMateriale);
        dataFine.setValue(LocalDate.now());

    }

    public void setQuantita(ActionEvent event) {
        int max = Casotto.getInstance().getQuantitaMateriale(comboMateriali.getValue());
        SpinnerValueFactory<Integer> quantita = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max, 1, 1);
        spinnereQuantita.setValueFactory(quantita);
    }

    public void calcolaConto(ActionEvent event) {
        if (bozza == null)
            this.creaBozza();
        this.lbTotale.setText(String.valueOf(this.bozza.calcolaConto()));
    }

    private void creaBozza() {
        Cliente cl = ClienteController.getC();
        Ombrellone omb = Ombrelloni.stream().filter(o -> o.getNumero() == comboOmbrelloni.getValue()).collect(Collectors.toList()).get(0);
        Materiale mat = Materiali.stream().filter(m -> m.getNome().equals(comboMateriali.getValue())).collect(Collectors.toList()).get(0);
        LocalDateTime fine = LocalDateTime.of(dataFine.getValue().getYear(), dataFine.getValue().getMonthValue(), dataFine.getValue().getDayOfMonth(), 19, 0, 0);
        bozza = new ComandaBalneare(omb.getId(),
                cl.getId(),
                mat,
                Timestamp.valueOf(fine),
                spinnereQuantita.getValue());
    }

    public void prenota(ActionEvent event) {
        if (bozza == null)
            this.creaBozza();
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Conferma ordine:" + bozza.toString(), ButtonType.APPLY);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.APPLY) {
            bozza.chiudiComanda();
            alert = new Alert(Alert.AlertType.INFORMATION, "Prenotazione effettuata");
            alert.show();
        } else
            alert = new Alert(Alert.AlertType.ERROR, "Errore prenotazione non effettuata");

    }

}
