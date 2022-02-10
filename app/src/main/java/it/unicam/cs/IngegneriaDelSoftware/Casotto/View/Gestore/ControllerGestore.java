package it.unicam.cs.IngegneriaDelSoftware.Casotto.View.Gestore;

import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Bagnino;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cassiere;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Cliente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Attori.Dipendente;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Balneare.Ombrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Service.Database;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Attivita;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Materiale;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.PrenotazioneOmbrellone;
import it.unicam.cs.IngegneriaDelSoftware.Casotto.Servizi.Prodotto;
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

public class ControllerGestore implements Initializable {

    //!-------------------------Sezione Cliente---------------------------!
    @FXML
    private Text ClientiPrenotati;

    @FXML
    private Text clientiTot;

    ObservableList<Cliente> listaCliente = FXCollections.observableArrayList();
    @FXML
    private TableView<Cliente> tableClienti;

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
    private TextField nomeNuovoCliente;
    @FXML
    private TextField cognomeNuovoCliente;
    @FXML
    private TextField residenzaNuovoCliente;
    @FXML
    private TextField telefonoNuovoCliente;
    @FXML
    private TextField emailNuovoCliente;
    @FXML
    private Button aggiungiCliente;

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

//!-------------------------Sezione Dipendenti---------------------------!

    @FXML
    private Button btAggiungiDipendente;

    @FXML
    private Button btAggiungiOmbrellone;

    @FXML
    private Button btCreaSpiaggia;

    @FXML
    private Button btEliminaDipendente;


    @FXML
    private Spinner<Integer> filaSetTariffa;

    @FXML
    private Spinner<Integer> fileCreaChalet;

    ObservableList<Dipendente> listDipendenti = FXCollections.observableArrayList();

    @FXML
    private TableView<Dipendente> tableDipendenti;
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
    private TableColumn<Dipendente, Integer> dipendenteTelefono;

    @FXML
    TextField nomeNuovoDipendente;
    @FXML
    TextField cognomeNuovoDipendente;
    @FXML
    TextField residenzaNuovoDipendente;
    @FXML
    TextField telefonoNuovoDipendente;
    @FXML
    TextField emailNuovoDipendente;
    @FXML
    ComboBox<String> ruoloNuovoDipendente;

    ObservableList<String> ruoli = FXCollections.observableArrayList();

    //!-------------------------Sezione Ombrelloni---------------------------!
    @FXML
    private Button btRimuoviOmbrellone;

    @FXML
    private Button btSetTariffa;
    @FXML
    private PieChart ombrelloni;

    @FXML
    private Spinner<Integer> ombrelloniCreaChalet;

    @FXML
    private Text ombrelloniDiponibili;

    @FXML
    private Text ombrelloniOccupati;

    @FXML
    private Text ombrelloniTot;
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
//!-------------------------Sezione Prenotazione---------------------------!

    @FXML
    private Button btnAggiungiPrenotazione;
    @FXML
    private Button btnRimuoviPrenotazione;

    ObservableList<PrenotazioneOmbrellone> listPrenotazione = FXCollections.observableArrayList();
    @FXML
    private TableView<PrenotazioneOmbrellone> tablePrenotazione;

    @FXML
    private TableColumn<PrenotazioneOmbrellone, String> prenotazioneCliente;

    @FXML
    private TableColumn<PrenotazioneOmbrellone, LocalDateTime> prenotazioneDataFine;

    @FXML
    private TableColumn<PrenotazioneOmbrellone, String> prenotazioneId;

    @FXML
    private TableColumn<PrenotazioneOmbrellone, String> prenotazioneOmbrellone;

    ObservableList<PrenotazioneOmbrellone> listaPrenotazioni = FXCollections.observableArrayList();


//!-------------------------Sezione Menu---------------------------!

    @FXML
    private Button btnAggiungiProdotto;

    @FXML
    private Button btnNuovoMenu;

    @FXML
    private Button btnRimuoviProdotto;

    ObservableList<Prodotto> listProdotti = FXCollections.observableArrayList();
    @FXML
    private TableView<Prodotto> tableMenu;

    @FXML
    private TableColumn<Prodotto, String> prodottoDescrizione;

    @FXML
    private TableColumn<Prodotto, String> prodottoNome;

    @FXML
    private TableColumn<Prodotto, Float> prodottoPrezzo;
    @FXML
    private TableColumn<Prodotto, String> idProdotto;


    //!---------------------Sezione Struttura-----------!
    @FXML
    private Spinner<Double> prezzoSetTariffa;
    @FXML
    private Tab tabAttivita;

    @FXML
    private Tab tabCliente;

    @FXML
    private Tab tabDipendneti;

    @FXML
    private Tab tabMenu;

    @FXML
    private Tab tabOmbrelloni;

    @FXML
    private Tab tabPrenotazione;
    @FXML
    private Tab tabMateriali;

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

        SpinnerValueFactory<Integer> File =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);

        fileCreaChalet.setValueFactory(File);
        SpinnerValueFactory<Integer> Ombrelloni =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);

        ombrelloniCreaChalet.setValueFactory(Ombrelloni);

        SpinnerValueFactory<Double> tariffe = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 100, 1, 0.5);
        prezzoSetTariffa.setValueFactory(tariffe);
        ruoli.addAll("Bagnino", "Cassiere");
        ruoloNuovoDipendente.setItems(ruoli);

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

            rs = con.createStatement().executeQuery("select * from Dipendenti");
            while (rs.next()) {
                listDipendenti.add(new Dipendente(rs.getString("Id"),
                        rs.getString("Nome"),
                        rs.getString("Cognome"),
                        rs.getString("Residenza"),
                        rs.getString("Telefono"),
                        rs.getString("Email"),
                        rs.getString("Email"),
                        rs.getString("Ruolo")));
            }
            rs = con.createStatement().executeQuery("select * from Clienti");
            while (rs.next()) {
                listaCliente.add(
                        new Cliente(rs.getString("Id"),
                                rs.getString("Nome"),
                                rs.getString("Cognome"),
                                rs.getString("Residenza"),
                                rs.getString("Telefono"),
                                //todo: nome utenete = email?
                                rs.getString("Email"),
                                rs.getString("Email")));
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

        dipendenteID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        dipendenteNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        dipendenteCognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        dipendenteResidenza.setCellValueFactory(new PropertyValueFactory<>("Residenza"));
        dipendenteTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        dipendenteRuolo.setCellValueFactory(new PropertyValueFactory<>("Ruolo"));
        dipendenteEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tableDipendenti.setItems(listDipendenti);

        clienteId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        clienteNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        clienteCognome.setCellValueFactory(new PropertyValueFactory<>("Cognome"));
        clienteResidenza.setCellValueFactory(new PropertyValueFactory<>("Residenza"));
        clienteTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        clienteEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        tableClienti.setItems(listaCliente);

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

        if (ListaOmbrelloni.size() > 0) {
            SpinnerValueFactory<Integer> Fila =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, ListaOmbrelloni.size(), 1, 1);
            filaSetTariffa.setValueFactory(Fila);
            ombrelloniTot.setText(String.valueOf(ListaOmbrelloni.size()));
        } else {
            SpinnerValueFactory<Integer> Fila =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100, 1, 1);
            filaSetTariffa.setValueFactory(Fila);
            ombrelloniTot.setText("0");
        }

        if (listaCliente.size() > 0) {
            clientiTot.setText(String.valueOf(listaCliente.size()));
        }

        ombrelloniTot.setText(String.valueOf(ListaOmbrelloni.size()));
        SpinnerValueFactory<Integer> numeroOmbrellone = new SpinnerValueFactory.IntegerSpinnerValueFactory(ListaOmbrelloni.size(), 200, 1);
        numeroNuovoOmbrellone.setValueFactory(numeroOmbrellone);
    }


    @FXML
    public void setTariffe(ActionEvent event) throws SQLException {

        String dettaglio = "Riepilogo: \n fila: " + filaSetTariffa.getValue() + "\n costo: " + prezzoSetTariffa.getValue();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, dettaglio + " \n procedere?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Aggiornamento tariffe Ombrelloni");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection con = Database.getConnection();
            String query = "UPDATE Ombrelloni  SET Tariffa='" + prezzoSetTariffa.getValue() + "' WHERE Fila = '" + filaSetTariffa.getValue() + "';";

            con.createStatement().executeUpdate(query);
            ListaOmbrelloni.clear();
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
            tableOmbrelloni.setItems(ListaOmbrelloni);
        }
    }


    @FXML

    public void aggiungiDipendente(ActionEvent event) throws SQLException {

        Dipendente tb;
        if (ruoloNuovoDipendente.getValue().equals("Bagnino")) {
            tb = new Bagnino(nomeNuovoDipendente.getText(),
                    cognomeNuovoDipendente.getText(),
                    residenzaNuovoDipendente.getText(),
                    telefonoNuovoDipendente.getText(),
                    emailNuovoDipendente.getText(),
                    emailNuovoDipendente.getText());

        } else {
            tb = new Cassiere(nomeNuovoDipendente.getText(),
                    cognomeNuovoDipendente.getText(),
                    residenzaNuovoDipendente.getText(),
                    telefonoNuovoDipendente.getText(),
                    emailNuovoDipendente.getText(),
                    emailNuovoDipendente.getText());

        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, tb.toString() + " \n procedi con l'aggiunta del Dipendente?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Creazione nuovo  Dipendente");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            listDipendenti.add(tb);

            Connection con = Database.getConnection();
            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO Dipendenti (Id, Nome, Cognome, Residenza, Telefono, Ruolo, Email) VALUES (");
            query.append("'" + tb.getId() + "',");
            query.append("'" + tb.getNome() + "',");
            query.append("'" + tb.getCognome() + "',");
            query.append("'" + tb.getResidenza() + "',");
            query.append("'" + tb.getTelefono() + "',");
            query.append("'" + tb.getRuolo() + "',");
            query.append("'" + tb.getEmail() + "');");

            con.createStatement().executeUpdate(query.toString());
            nomeNuovoDipendente.setText("");
            cognomeNuovoDipendente.setText("");
            telefonoNuovoDipendente.setText("");
            emailNuovoDipendente.setText("");
            residenzaNuovoDipendente.setText("");
        }
    }


    public void eliminaDipendente(ActionEvent event) throws SQLException {
        Dipendente i = tableDipendenti.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, i.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Dipendente");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            listDipendenti.remove(i);
            //rimuovo il Dipendente dal db
            Connection con = Database.getConnection();

            StringBuilder query = new StringBuilder();
            query.append(" DELETE FROM Casotto.Dipendenti WHERE (Id = '");
            query.append(i.getId() + "');");
            con.createStatement().executeUpdate(query.toString());
        }
    }

    public void creaSpiaggia(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "La creazione di una nuova Spiaggia elimina tutti gli ombrelloni presenti. \n Porcedere con la creazione di una nuova spiaggia?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Creazione nuova Spiaggia");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Connection con = Database.getConnection();
            con.createStatement().executeUpdate("DELETE FROM Ombrelloni");
            ListaOmbrelloni.clear();
            int numero = 1;

            for (int i = 1; i <= fileCreaChalet.getValue(); i++) {
                for (int k = 1; k <= ombrelloniCreaChalet.getValue(); k++) {
                    ListaOmbrelloni.add(new Ombrellone(i, 0.0f, numero));
                    numero++;
                }
            }
            StringBuilder subquery;
            for (Ombrellone t : ListaOmbrelloni) {
                subquery = new StringBuilder();
                subquery.append("INSERT INTO Ombrelloni (Id, Numero, Fila, Tariffa, Disponibilità) VALUES (");
                subquery.append("'" + t.getId() + "',");
                subquery.append("'" + t.getNumero() + "',");
                subquery.append("'" + t.getFila() + "',");
                subquery.append("'" + t.getTariffa() + "',");
                subquery.append("'" + t.getDisponibilita() + "');");
                con.createStatement().executeUpdate(subquery.toString());
            }
            ombrelloniTot.setText(String.valueOf(ListaOmbrelloni.size()));

            //aggiorno Spienner nuovo ombrellone
            SpinnerValueFactory<Integer> numeroOmbrellone = new SpinnerValueFactory.IntegerSpinnerValueFactory(ListaOmbrelloni.size(), ListaOmbrelloni.size() + 100, 1);
            numeroNuovoOmbrellone.setValueFactory(numeroOmbrellone);

        }
    }

    public void aggiungiCliente(ActionEvent event) throws SQLException {
        String descrizione = "Cliente: \n nome: " + nomeNuovoCliente.getText() + "\n Cognome:" + cognomeNuovoCliente.getText() + "\nResidenza: " + residenzaNuovoCliente.getText() + "\n Telefono:" + Integer.valueOf(telefonoNuovoCliente.getText()) + "\n Email" + emailNuovoCliente.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, descrizione + " \n procedi con l'aggiunta del Cliente?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Creazione nuovo  Cliente");
        alert.showAndWait();
        if (alert.getResult().equals(ButtonType.YES)) {
            Cliente cliente = new Cliente(nomeNuovoCliente.getText(), cognomeNuovoCliente.getText(), residenzaNuovoCliente.getText(), (telefonoNuovoCliente.getText()), emailNuovoCliente.getText(), emailNuovoCliente.getText());
            Connection con = Database.getConnection();
            String query = "INSERT INTO Casotto.Clienti (Id, Nome, Cognome, Residenza, Telefono,Email) VALUES (" +
                    "'" + cliente.getId() + "',"
                    + "'" + cliente.getNome() + "',"
                    + "'" + cliente.getCognome() + "',"
                    + "'" + cliente.getResidenza() + "',"
                    + "'" + cliente.getTelefono() + "',"
                    + "'" + cliente.getEmail() + "');";
            con.createStatement().executeUpdate(query.toString());
            listaCliente.add(cliente);
        }
    }

    public void rimuoviCliente(ActionEvent event) throws SQLException {
        Cliente cliente = tableClienti.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, cliente.toString() + " \n procedi con l'eliminazione?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminazione Cliente");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            listaCliente.remove(cliente);
            //rimuovo il cliente dal db
            Connection con = Database.getConnection();
            String query = " DELETE FROM Casotto.Clienti WHERE (Id = '" + cliente.getId() + "');";
            con.createStatement().executeUpdate(query);
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
                    "'" + ombrellone.getId() + "'," +
                    "'" + ombrellone.getNumero() + "'," +
                    "'" + ombrellone.getFila() + "'," +
                    "'" + ombrellone.getTariffa() + "'," +
                    "'" + ombrellone.getDisponibilita() + "');";
            con.createStatement().executeUpdate(query);

            ListaOmbrelloni.add(ombrellone);
            ombrelloniTot.setText(String.valueOf(ListaOmbrelloni.size()));
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
                    "'" + a.getIdAttivita() + "'," +
                    "'" + a.getNome() + "',"
                    + "'" + a.getPostiMax() + "'," +
                    "'" + a.getPostiMin() + "'," +
                    "'" + a.getCosto() + "'," +
                    "'" + a.getOrario() + "'," +
                    "'" + comp + "');";

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

            String Query = "DELETE FROM Casotto.Attivita WHERE (Id = '" +
                    attivita.getId() + "');";
            con.createStatement().executeUpdate(Query);

            listaAttivita.remove(attivita);
        }
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
            String Query = "DELETE FROM Casotto.Materiali WHERE (Id = '" +
                    materiale.getId() + "');";
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
                    "'" + materiale.getId() + "'," +
                    "'" + materiale.getNome() + "',"
                    + "'" + materiale.getQuantita() + "'," +
                    "'" + materiale.getCosto() + "');";
            con.createStatement().executeUpdate(Query);
            listaMateriali.add(materiale);
        }
    }


    public void aggiungiProdotto(ActionEvent event) {
    }

    public void rimuoviPtrodotto(ActionEvent event) {
    }

    public void nuovoMenu(ActionEvent event) {
    }


}
