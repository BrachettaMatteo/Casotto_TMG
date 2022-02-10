package it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Bagnino;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ControllerBagnino implements Initializable {

    //!-------------------------Sezione Ombrelloni---------------------------!
    @FXML
    private Button btRimuoviOmbrellone;

    ObservableList<Ombrellone> ListaOmbrelloni = FXCollections.observableArrayList();
    @FXML
    private TableView<Ombrellone> tableOmbrelloni;
    @FXML
    private TableColumn<Ombrellone, String> ombrelloneDipso;

    @FXML
    private TableColumn<Ombrellone, Integer> ombrelloneFila;

    @FXML
    private TableColumn<Ombrellone, LocalDateTime> ombrelloneFine;

    @FXML
    private TableColumn<Ombrellone, String> ombrelloneID;

    @FXML
    private TableColumn<Ombrellone, Integer> ombrelloneNumero;

    @FXML
    private TableColumn<Ombrellone, Float> ombrelloneTariffa;


    @FXML
    private Spinner<Integer> FilaNuovoOmbrellone;
    @FXML
    private Spinner<Integer> numeroNuovoOmbrellone;
    //!-------------------------Sezione Attivita---------------------------!

    @FXML
    private TableView<Attivita> tableAttivita;
    @FXML
    private TableColumn<Attivita, Float> attivitaCosto;

    @FXML
    private TableColumn<Attivita, String> attivitaId;

    @FXML
    private TableColumn<Attivita, String> attivitaNome;

    @FXML
    private TableColumn<Attivita, LocalDateTime> attivitaOrario;

    @FXML
    private TableColumn<Attivita, Integer> attivitaPartecipanti;

    @FXML
    private TableColumn<Attivita, Integer> attivitaPostiMin;
    @FXML
    private TableColumn<Attivita, Integer> attivitaPostiMax;

    @FXML
    private Button btnAggiungiAttivita;

    @FXML
    private Button btnRimuoviAttivita;
    @FXML
    private TextField nomeNuovaAttvita;
    @FXML
    private Spinner<Integer> postiMinNuovaAttivita;
    @FXML
    private Spinner<Integer> postiMaxNuovaAttivita;
    @FXML
    private Spinner<Double> costonuovaAttivita;
    @FXML
    private DatePicker OrarioNuovaAttivita;
    @FXML
    private Spinner<Integer> hNuovaAttivita;
    @FXML
    private Spinner<Integer> mNuovaAttivita;
    ObservableList<Attivita> listaAttivita = FXCollections.observableArrayList();
    @FXML
    private Spinner<Integer> numeroComponentiDAggiungere;
    //!---------------------Sezione Materiali-----------!
    @FXML
    private TextField nomeNuovoMateriale;
    @FXML
    private Spinner<Double> costoNuovoMateriale;
    @FXML
    private Spinner<Integer> quantitaNuovoMateriale;

    ObservableList<Materiale> listaMateriali = FXCollections.observableArrayList();
    @FXML
    private TableView<Materiale> tableMateriali;

    @FXML
    private TableColumn<Materiale, String> materialeId;

    @FXML
    private TableColumn<Materiale, String> materialeNome;

    @FXML
    private TableColumn<Materiale, Float> materialeCosto;
    @FXML
    private TableColumn<Materiale, Integer> materialeQuantita;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> postiMin =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        postiMinNuovaAttivita.setValueFactory(postiMin);
        SpinnerValueFactory<Integer> postiMax =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        postiMaxNuovaAttivita.setValueFactory(postiMax);
        SpinnerValueFactory<Integer> h =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1, 1);
        h.setValue(LocalDateTime.now().getHour());
        hNuovaAttivita.setValueFactory(h);
        SpinnerValueFactory<Integer> m =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0, 1);
        m.setValue(LocalDateTime.now().getMinute());
        mNuovaAttivita.setValueFactory(m);
        SpinnerValueFactory<Double> prezzoNuovaAttivita = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, 0, 0.5);
        costonuovaAttivita.setValueFactory(prezzoNuovaAttivita);
        OrarioNuovaAttivita.setValue(LocalDate.now());
        SpinnerValueFactory<Integer> fileOmbrellone = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        FilaNuovoOmbrellone.setValueFactory(fileOmbrellone);
        SpinnerValueFactory<Double> costoMateriale = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1, 0.5);
        costoNuovoMateriale.setValueFactory(costoMateriale);
        SpinnerValueFactory<Integer> quantitaMateriale = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        quantitaNuovoMateriale.setValueFactory(quantitaMateriale);

        Connection con = null;
        try {
            con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Ombrelloni");
            while (rs.next()) {
                ListaOmbrelloni.add(new Ombrellone(rs.getString("Id"),
                        rs.getInt("Fila"),
                        rs.getFloat("Tariffa"),
                        rs.getInt("Numero"),
                        rs.getString("Disponibilità"),
                        rs.getTimestamp("Fine")
                ));
            }
            rs = con.createStatement().executeQuery("select * From Attivita");
            while (rs.next()) {
                listaAttivita.add(new Attivita(
                        rs.getString("Id"),
                        rs.getString("nome"),
                        rs.getInt("postiMax"),
                        rs.getInt("postiMin"),
                        //rs.getInt("Componenti"),
                        rs.getFloat("costo"),
                        rs.getTimestamp("orario")
                ));
            }
            rs = con.createStatement().executeQuery("SELECT * FROM Materiali");
            while (rs.next())
                listaMateriali.add(new Materiale(rs.getString("idMateriale"),
                        rs.getString("Nome"),
                        rs.getFloat("costo"),
                        rs.getInt("Quantità")));

        } catch (SQLException e) {

            e.printStackTrace();
        }

        ombrelloneID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        ombrelloneDipso.setCellValueFactory(new PropertyValueFactory<>("Disponibilita"));
        ombrelloneFila.setCellValueFactory(new PropertyValueFactory<>("Fila"));
        ombrelloneNumero.setCellValueFactory(new PropertyValueFactory<>("Numero"));
        ombrelloneTariffa.setCellValueFactory(new PropertyValueFactory<>("Tariffa"));
        ombrelloneFine.setCellValueFactory(new PropertyValueFactory<>("Fine"));
        tableOmbrelloni.setItems(ListaOmbrelloni);

        attivitaId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        attivitaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        attivitaPostiMax.setCellValueFactory(new PropertyValueFactory<>("PostiMax"));
        attivitaPostiMin.setCellValueFactory(new PropertyValueFactory<>("PostiMin"));
        attivitaCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        attivitaOrario.setCellValueFactory(new PropertyValueFactory<>("Orario"));
        attivitaPartecipanti.setCellValueFactory(new PropertyValueFactory<>("Partecipanti"));
        tableAttivita.setItems(listaAttivita);

        materialeId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        materialeNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        materialeCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        materialeQuantita.setCellValueFactory(new PropertyValueFactory<>("Quantita"));
        tableMateriali.setItems(listaMateriali);

        SpinnerValueFactory<Integer> numeroOmbrellone = new SpinnerValueFactory.IntegerSpinnerValueFactory(ListaOmbrelloni.size(), 200, 1);
        numeroNuovoOmbrellone.setValueFactory(numeroOmbrellone);

    }


    @FXML
    public void rimuoviMateriale(ActionEvent event) throws SQLException {
        Materiale materiale = tableMateriali.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, materiale.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Materiale");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //rimuovo il maeriale dal db
            Connection con = Database.getConnection();
            String Query = "DELETE FROM Casotto.Materiali WHERE (Id = " +
                    materiale.getId() + ");";
            con.createStatement().executeUpdate(Query);

            listaMateriali.remove(materiale);
        }

    }

    @FXML
    public void aggiungiMateriale(ActionEvent event) throws SQLException {
        String dettagli = "Materiale: \n nome:" + nomeNuovoMateriale.getText() + "\n quntità:" + quantitaNuovoMateriale.getValue() + "\n prezzo:"
                + costoNuovoMateriale.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, dettagli + " \n procedi con l'aggiunta dell materiale?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Conferma aggiunta Materiale");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            Materiale materiale = new Materiale(nomeNuovoMateriale.getText(), costoNuovoMateriale.getValue().floatValue(), quantitaNuovoMateriale.getValue());
            Connection con = Database.getConnection();

            String Query = "INSERT INTO Casotto.Materiali (idMateriale, Nome, Quantità, costo) VALUES (" +
                     materiale.getId() + "," +
                     materiale.getNome() + ","
                    +  materiale.getQuantita() + "," +
                     materiale.getCosto() + ");";
            con.createStatement().executeUpdate(Query);
            listaMateriali.add(materiale);
        }
    }

    public void aggiungiAttivita(ActionEvent event) throws SQLException {

        String dettagli = "attvita: \n nome:" + nomeNuovaAttvita.getText() + "\n postiMin: " + postiMinNuovaAttivita.getValue() +
                "\n posti Max:" + postiMaxNuovaAttivita.getValue() + "\n costo:" + costonuovaAttivita.getValue() +
                "\n Data: " + OrarioNuovaAttivita.getValue() + "\n orario: " + hNuovaAttivita.getValue() + ":" + mNuovaAttivita.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, dettagli + " \n procedi con l'aggiunta dell'attività?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Conferma aggiunta Attivta");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            LocalDateTime date = LocalDateTime.of(OrarioNuovaAttivita.getValue(), LocalTime.of(hNuovaAttivita.getValue(), mNuovaAttivita.getValue()));
            Attivita a = new Attivita(nomeNuovaAttvita.getText(), postiMaxNuovaAttivita.getValue(), postiMinNuovaAttivita.getValue(), date, costonuovaAttivita.getValue().floatValue());

            Connection con = Database.getConnection();
            int comp = a.getComponenti() != null ? a.getComponenti().size() : 0;
            String Query = "INSERT INTO Casotto.Attivita (Id, Nome, postiMax, postiMin, costo, orario, Componenti) VALUES (" +
                     a.getIdAttivita() + "," +
                     a.getNome() + ","
                    +  a.getPostiMax() + "," +
                     a.getPostiMin() + "," +
                     a.getCosto() + "," +
                     a.getOrario() + "," +
                     comp + ");";

            con.createStatement().executeUpdate(Query);
            listaAttivita.add(a);
        }
    }

    public void rimuoviAttivita(ActionEvent event) throws SQLException {
        Attivita attivita = tableAttivita.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, attivita.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Attività");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            //rimuovo l'attivita dal db
            Connection con = Database.getConnection();

            String Query = "DELETE FROM Casotto.Attivita WHERE (Id =" +
                    attivita.getId() + ");";
            con.createStatement().executeUpdate(Query);

            listaAttivita.remove(attivita);
        }
    }

    public void aggiungiOmbrellone(ActionEvent event) throws SQLException {
        String det = "ombrellone \n Fila: " + FilaNuovoOmbrellone.getValue() + "\n Numero: " + numeroNuovoOmbrellone.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, det + " \n procedi con l'aggiunta dell'ombrellone?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Conferma aggiunta ombrellone");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT DISTINCT Tariffa FROM Ombrelloni WHERE Fila='" + FilaNuovoOmbrellone.getValue() + "';");
            Ombrellone ombrellone;
            if (rs.next()) {

                ombrellone = new Ombrellone(FilaNuovoOmbrellone.getValue(), rs.getFloat("Tariffa"), numeroNuovoOmbrellone.getValue());

            } else {
                ombrellone = new Ombrellone(FilaNuovoOmbrellone.getValue(), 0.0f, numeroNuovoOmbrellone.getValue());
            }

            String query = "INSERT INTO Casotto.Ombrelloni (Id, Numero, Fila, Tariffa,Disponibilità) VALUES (" +
                      ombrellone.getId() + "," +
                      ombrellone.getNumero() + "," +
                      ombrellone.getFila() + "," +
                      ombrellone.getTariffa() + "," +
                      ombrellone.getDisponibilita() + ");";
            con.createStatement().executeUpdate(query);

            ListaOmbrelloni.add(ombrellone);
            SpinnerValueFactory<Integer> numeroOmbrelloni = new SpinnerValueFactory.IntegerSpinnerValueFactory(ListaOmbrelloni.size() + 1, ListaOmbrelloni.size() + 100, ListaOmbrelloni.size() + 1);
            FilaNuovoOmbrellone.setValueFactory(numeroOmbrelloni);
        }
    }

    public void rimuoviOmbrellone(ActionEvent event) throws SQLException {
        Ombrellone ombrellone = tableOmbrelloni.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ombrellone.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Ombrellone");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            ListaOmbrelloni.remove(ombrellone);
            //rimuovo il Dipendente dal db
            Connection con = Database.getConnection();

            String Query = "DELETE FROM Casotto.Ombrelloni WHERE (Id = '" +
                    ombrellone.getId() + "');";
            con.createStatement().executeUpdate(Query);
        }
    }

    public void liberaOmbrellone(ActionEvent event) {

    }

    public void evadiComanda(ActionEvent event) {
    }
}
