package it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.ControllerGestione;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Bagnino.Bagnino;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Cassiere.Cassiere;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendete.Dipendente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Casotto;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GestionePersonale implements Initializable {

    @FXML
    private ComboBox<String> cbNuovoDipendente;

    @FXML
    private TableColumn<Dipendente, String> dipendenteCognome;

    @FXML
    private TableColumn<Dipendente, String> dipendenteEmail;

    @FXML
    private TableColumn<Dipendente, String> dipendenteID;

    @FXML
    private TableColumn<Dipendente, String> dipendenteNome;

    @FXML
    private TableColumn<Dipendente, String> dipendenteResidenza;

    @FXML
    private TableColumn<Dipendente, String> dipendenteRuolo;

    @FXML
    private TableColumn<Dipendente, String> dipendenteTelefono;

    @FXML
    private TableView<Dipendente> tableDipendenti;

    @FXML
    private TextField tfCognomeNuovoDipendente;

    @FXML
    private TextField tfEmailNuovoDipendente;

    @FXML
    private TextField tfNomeNuovoDipendente;

    @FXML
    private TextField tfResidenzaNuovoDipendente;

    @FXML
    private TextField tfTelefonoNuovoDipendente;

    private final ObservableList<Dipendente> ListaPersonale = FXCollections.observableArrayList();
    private final ObservableList<String> ruoli = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListaPersonale.clear();
        ListaPersonale.addAll(Casotto.getInstance().getPersonale());

        dipendenteID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        dipendenteNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        dipendenteCognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        dipendenteResidenza.setCellValueFactory(new PropertyValueFactory<>("Residenza"));
        dipendenteTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        dipendenteRuolo.setCellValueFactory(new PropertyValueFactory<>("Ruolo"));
        dipendenteEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tableDipendenti.setItems(ListaPersonale);

        ruoli.add("Bagnino");
        ruoli.add("Cassiere");
        cbNuovoDipendente.setItems(ruoli);
    }

    @FXML
    void creaNuovoDipendente() throws SQLException {

        Alert alert;
        Dipendente dipendente;
        String nomeUtente = tfNomeNuovoDipendente.getText() + tfCognomeNuovoDipendente.getText();

        if (cbNuovoDipendente.getValue().equals("Bagnino")) {
            dipendente = new Bagnino(tfNomeNuovoDipendente.getText(),
                    tfCognomeNuovoDipendente.getText(),
                    tfResidenzaNuovoDipendente.getText(),
                    tfTelefonoNuovoDipendente.getText(),
                    nomeUtente,
                    tfEmailNuovoDipendente.getText());

        } else {

            dipendente = new Cassiere(tfNomeNuovoDipendente.getText(),
                    tfCognomeNuovoDipendente.getText(),
                    tfResidenzaNuovoDipendente.getText(),
                    tfTelefonoNuovoDipendente.getText(),
                    nomeUtente,
                    tfEmailNuovoDipendente.getText());

        }
        alert = new Alert(Alert.AlertType.CONFIRMATION, dipendente + " \n procedi con l'aggiunta del Dipendente?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Creazione nuovo  Dipendente");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {


            Connection con = Database.getConnection();
            //todo: creare generatore password e invio mail.
            //Aggiungo utente
            String query="Insert Into  Utente(Username, Password, Ruolo) Values ('"+dipendente.getNomeUtente()+"'," +
                    "'1234' , '"+dipendente.getRuolo()+"')";
            con.createStatement().executeUpdate(query);

             query = "Insert Into Dipendente(ID, Nome, Cognome, Residenza, Telefono, Ruolo, Email, nomeUtente)  VALUES (" +
                    "'" + dipendente.getId() + "',"
                    + "'" + dipendente.getNome() + "',"
                    + "'" + dipendente.getCognome() + "',"
                    + "'" + dipendente.getResidenza() + "',"
                    + "'" + dipendente.getTelefono() + "',"
                    + "'" + dipendente.getRuolo() + "',"
                    + "'" + dipendente.getEmail() + "',"
                    + "'" + dipendente.getNomeUtente() + "');";
            con.createStatement().executeUpdate(query);

            ListaPersonale.add(dipendente);

            tfNomeNuovoDipendente.setText("");
            tfCognomeNuovoDipendente.setText("");
            tfTelefonoNuovoDipendente.setText("");
            tfEmailNuovoDipendente.setText("");
            tfResidenzaNuovoDipendente.setText("");
        }
    }

    @FXML
    void rimuoviDipendente() {

        Dipendente i = tableDipendenti.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, i.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Dipendente");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection con ;
            try {
                con = Database.getConnection();
                String query;
                query= "DELETE from Dipendente where ID='" + i.getId() + "'";
                con.createStatement().executeUpdate(query);
                 query = "DELETE from Utente where Username='" + i.getNomeUtente() + "'";
                con.createStatement().executeUpdate(query);
                ListaPersonale.remove(i);
            } catch (SQLException e) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Errore Sistema");
                alert1.show();
                e.printStackTrace();
            }

        }
    }

}
