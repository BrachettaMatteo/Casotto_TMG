package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
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

public class GestioneClienti implements Initializable {

    @FXML
    private Button aggiungiCliente;

    @FXML
    private TableColumn<Cliente, String> clienteCognome;

    @FXML
    private TableColumn<Cliente, String> clienteEmail;

    @FXML
    private TableColumn<Cliente, String> clienteId;

    @FXML
    private TableColumn<Cliente, String> clienteNome;

    @FXML
    private TableColumn<Cliente, String> clienteResidenza;

    @FXML
    private TableColumn<Cliente, String> clienteTelefono;

    @FXML
    private TextField cognomeNuovoCliente;

    @FXML
    private TextField emailNuovoCliente;

    @FXML
    private TextField nomeNuovoCliente;

    @FXML
    private TextField residenzaNuovoCliente;

    @FXML
    private TableView<Cliente> tableClienti;

    @FXML
    private TextField telefonoNuovoCliente;

    private ObservableList<Cliente> ListaCliente = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaCliente.clear();
        ListaCliente.addAll(Casotto.getInstance().getClienti());

        clienteId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clienteNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        clienteCognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        clienteResidenza.setCellValueFactory(new PropertyValueFactory<>("Residenza"));
        clienteTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        clienteEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tableClienti.setItems(ListaCliente);

    }

    @FXML
    void aggiungiCliente(ActionEvent event) {
        String descrizione = "Cliente: \n nome: " + nomeNuovoCliente.getText() + "\n Cognome:" + cognomeNuovoCliente.getText() + "\nResidenza: " + residenzaNuovoCliente.getText() + "\n Telefono:" + Integer.valueOf(telefonoNuovoCliente.getText()) + "\n Email" + emailNuovoCliente.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, descrizione + " \n procedi con l'aggiunta del Cliente?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Creazione nuovo  Cliente");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            String nomeUtente = nomeNuovoCliente.getText() + cognomeNuovoCliente.getText();
            Cliente cliente = new Cliente(nomeNuovoCliente.getText(),
                    cognomeNuovoCliente.getText(),
                    residenzaNuovoCliente.getText(),
                    telefonoNuovoCliente.getText(),
                    nomeUtente,
                    emailNuovoCliente.getText());

            Casotto.getInstance().aggiungiCLiente(cliente);
            ListaCliente.add(cliente);
        }
    }

    @FXML
    void rimuoviCliente() {
        Cliente cliente = tableClienti.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, cliente.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Cliente");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection con = null;
            try {
                con = Database.getConnection();
                String query = " DELETE FROM Casotto.Clienti WHERE (Id = '" + cliente.getId() + "');";
                con.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ListaCliente.remove(cliente);

        }
    }

}