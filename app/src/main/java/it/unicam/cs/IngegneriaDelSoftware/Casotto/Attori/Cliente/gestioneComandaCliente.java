package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.CreaNuovaComanda;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Chalet;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Ristorazione.ComandaRistorazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class gestioneComandaCliente implements Initializable {

    public TableView<ComandaRistorazione> tabcomande;
    public TableColumn<ComandaRistorazione, String> clComanda;
    public TableColumn<ComandaRistorazione, String> clCliente;
    public TableColumn<ComandaRistorazione, String> clOmbrellone;
    public TableColumn<ComandaRistorazione, String> clStatus;
    public TableColumn<ComandaRistorazione, String> clListaProdotti;

    private static final ObservableList<ComandaRistorazione> listaComande = FXCollections.observableArrayList();
    private final ObservableList<Integer> numeroOmbrelloni = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Integer> cbNumeroOmbrellone;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String ombrellone = Casotto.getInstance().getOmbrelloneToNumero(cbNumeroOmbrellone.getValue());
        String cl = Casotto.getInstance().getIdClientetoOmbrellone(ombrellone);
        listaComande.addAll(Casotto.getInstance().getComandeRistorazioneCliente(cl));

        clComanda.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clCliente.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        clOmbrellone.setCellValueFactory(new PropertyValueFactory<>("IdOmbrellone"));
        clListaProdotti.setCellValueFactory(new PropertyValueFactory<>("listaProdotti"));
        clStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

        tabcomande.setItems(listaComande);

        ArrayList<String> om = Casotto.getInstance().getOmbrellonePrenotatati(ClienteController.getC());

        for (String o : om)
            numeroOmbrelloni.add(Casotto.getInstance().getOmbrellone(o).getNumero());

        cbNumeroOmbrellone.setItems(numeroOmbrelloni);
    }

    @FXML
    void creaNuovaComandaRistorazione() throws IOException {
        String  index =Casotto.getInstance().getOmbrelloneToNumero(cbNumeroOmbrellone.getValue());
        Ombrellone om = Casotto.getInstance().getOmbrellone(index);
        ComandaRistorazione cr = new ComandaRistorazione(om,
                ClienteController.getC());
        CreaNuovaComanda.setCr(cr);
        FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("Dipendenti/creaNuovaComanda.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Prenotazione risotrazione!");
        stage.setScene(scene);
        stage.show();

    }
    public static void  aggiornaComande(){
        listaComande.clear();
        listaComande.addAll(Casotto.getInstance().getComandeRistorazioneCliente(ClienteController.getC().getId()));
    }

}
