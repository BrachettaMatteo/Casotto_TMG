package it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Cliente;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.PrenotazioneOmbrellone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerPrenotazioneOmbrellone implements Initializable {
    @FXML
    GridPane ListaOmbrelloni;
    @FXML
    Text FilaOmbrellone;
    @FXML
    Text numeroOmbrellone;
    @FXML
    Text costoOmbrelloe;
    @FXML
    ImageView imgOmbrellone;

    Ombrellone OmbrelloneSelezionato;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaOmbrelloni.autosize();
        Connection con = null;
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT Fila,Numero,Tariffa FROM Ombrelloni order by Fila,Numero");
            int ro = -1;
            while (rs.next()) {
                if (rs.getInt("Fila") != ro + 1)
                    ro = rs.getInt("Fila") - 1;

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Chalet.class.getResource("ombrellone-view.fxml"));
                VBox ombrelloneView = fxmlLoader.load();
                ControllerOmbrellone itemController = fxmlLoader.getController();
                itemController.setPrezzo(rs.getFloat("Tariffa"));

                ListaOmbrelloni.addRow(ro, ombrelloneView);

                //set grid width
                ListaOmbrelloni.setMinWidth(Region.USE_COMPUTED_SIZE);
                ListaOmbrelloni.setPrefWidth(Region.USE_COMPUTED_SIZE);
                ListaOmbrelloni.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                ListaOmbrelloni.setMinHeight(Region.USE_COMPUTED_SIZE);
                ListaOmbrelloni.setPrefHeight(Region.USE_COMPUTED_SIZE);
                ListaOmbrelloni.setMaxHeight(Region.USE_PREF_SIZE);
                ListaOmbrelloni.autosize();
                ListaOmbrelloni.setMargin(ombrelloneView, new Insets(0.5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void selezioneOmbrllone(Ombrellone o) {
        FilaOmbrellone.setText(String.valueOf(o.getFila()));
        numeroOmbrellone.setText(String.valueOf(o.getNumero()));
        costoOmbrelloe.setText(String.valueOf(o.getTariffa() + "€"));

    }

    public void prenotaOmbrellone(ActionEvent event) {

    }
}
