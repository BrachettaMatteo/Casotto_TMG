package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestoreControllerHome implements Initializable {

    @FXML
    private Button btnCreaSpiaggia;

    @FXML
    private Button btnModificaSpiaggia;

    @FXML
    private Spinner<Integer> spFila;

    @FXML
    private Spinner<Integer> spNumeroFileSpiaggia;

    @FXML
    private Spinner<Integer> spNumeroOmbrelloneSpiaggia;

    @FXML
    private Spinner<Double> spTariffa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> fil = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        spNumeroFileSpiaggia.setValueFactory(fil);
        SpinnerValueFactory<Integer> Nombrelloni = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        spNumeroOmbrelloneSpiaggia.setValueFactory(Nombrelloni);

        SpinnerValueFactory<Double> tar = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1, 0.5);
        spTariffa.setValueFactory(tar);
        int max = Casotto.getInstance().getOmbrelloni().size();
        SpinnerValueFactory<Integer> fill = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, max, 1, 1);
        spFila.setValueFactory(fill);

    }

    public void modificaTariffa(ActionEvent event) {
        String dettaglio = "Riepilogo: \n fila: " + spFila.getValue() + "\n costo: " + spTariffa.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, dettaglio + " \n procedere?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Aggiornamento tariffe Ombrelloni");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection con = null;
            try {
                con = Database.getConnection();
                String query = "UPDATE Ombrelloni  SET Tariffa='" + spTariffa.getValue().floatValue() + "' WHERE Fila = '" + spFila.getValue() + "';";
                con.createStatement().executeUpdate(query);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void creaSpiaggia(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "La creazione di una nuova Spiaggia elimina tutti gli ombrelloni presenti. " +
                "\n creare Spiaggia di "+spNumeroOmbrelloneSpiaggia.getValue()+" " +
                "ombrelloni per "+spNumeroFileSpiaggia.getValue()+"\nPorcedere con la creazione di una nuova spiaggia?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Creazione nuova Spiaggia");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection con = null;
            try {
                con = Database.getConnection();
                con.createStatement().executeUpdate("DELETE FROM Ombrelloni");
                String query;
                int numero = 1;
                for (int i = 1; i <= spNumeroFileSpiaggia.getValue(); i++) {
                    for (int k = 1; k <= spNumeroOmbrelloneSpiaggia.getValue(); k++) {
                        Ombrellone o = new Ombrellone(i, 0.0f, numero);
                        numero++;
                         query = "INSERT INTO Ombrelloni (Id, Numero, Fila, Tariffa, Disponibilità) VALUES (" +
                                "'" + o.getId() + "'," +
                                "'" + o.getNumero() + "'," +
                                "'" + o.getFila() + "'," +
                                "'" + o.getTariffa() + "'," +
                                "'" + o.getDisponibilita() + "');";
                        con.createStatement().executeUpdate(query);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}
