package it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ClienteController;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class comnadaAttivitaController implements Initializable {
    private ComandaAttivita bozza;
    @FXML
    private Button btnPrenota;

    @FXML
    private ComboBox<Timestamp> comboOrario;

    @FXML
    private Label lbprezzo;

    @FXML
    private Spinner<Integer> nPartecipanti;

    @FXML
    private ComboBox<String> texAttivita;
    private ObservableList<String> ob = FXCollections.observableArrayList();

    private ObservableList<Timestamp> or = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ob.addAll(Casotto.getInstance().getNomiAttivita());
        texAttivita.setItems(ob);

    }

    public void cercaOrari(ActionEvent event) {
        or.addAll(Casotto.getInstance().getOrariAttivita(texAttivita.getValue()));
        comboOrario.setItems(or);
    }


    public void cercaPartecipanti(ActionEvent event) {

        Connection con = null;
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select  postiMax-Attivita.Componenti as num from Attivita where nome='" + this.texAttivita.getValue() + "' && orario='" + this.comboOrario.getValue() + "';");
            rs.next();
            if (rs.getInt("num") >= 1) {
                SpinnerValueFactory<Integer> in = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rs.getInt("num"), 1, 1);
                this.nPartecipanti.setValueFactory(in);
            } else
                this.nPartecipanti.setPromptText("Posti terminati");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void prenota(ActionEvent event) throws SQLException {
        if (bozza == null) {
            bozza = new ComandaAttivita(
                    Casotto.getInstance().getOmbrelloneIdCliente(ClienteController.getC().getId()),
                    ClienteController.getC().getId(),
                    Casotto.getInstance().getIdAttivita(texAttivita.getValue(), comboOrario.getValue()),
                    this.nPartecipanti.getValue()
            );
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, bozza.toString(), ButtonType.OK, ButtonType.CLOSE);
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.OK)) {
            bozza.chiudiComanda();
        }
        bozza = null;
    }


    public void creaConto(ActionEvent event) throws SQLException {
        bozza = new ComandaAttivita(
                Casotto.getInstance().getOmbrelloneIdCliente(ClienteController.getC().getId()),
                ClienteController.getC().getId(),
                Casotto.getInstance().getIdAttivita(texAttivita.getValue(), comboOrario.getValue()),
                this.nPartecipanti.getValue()
        );
        lbprezzo.setText(String.valueOf(bozza.calcolaConto()));

    }
}


