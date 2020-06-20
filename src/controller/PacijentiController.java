package controller;

import database.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Doktor;
import model.Pacijent;
import utils.Session;

public class PacijentiController implements Initializable {

    @FXML
    private TableView<Pacijent> tabela;

    @FXML
    private TableColumn<Pacijent, String> imeColumn;

    @FXML
    private TableColumn<Pacijent, String> prezimeColumn;

    @FXML
    private TableColumn<Pacijent, String> polColumn;

    @FXML
    private TableColumn<Pacijent, String> datumRodjenjaColumn;

    @FXML
    private TableColumn<Pacijent, String> jmbgColumn;

    @FXML
    private TableColumn<Pacijent, String> emailColumn;

    @FXML
    private TableColumn<Pacijent, String> brojTelefonaColumn;

    @FXML
    private TableColumn<Pacijent, String> adresaColumn;

    @FXML
    private Label ime;

    @FXML
    private Label prezime;

    @FXML
    private Label pol;

    @FXML
    private Label datumRodjenja;

    @FXML
    private Label jmbg;

    @FXML
    private Label email;

    @FXML
    private Label brojTelefona;

    @FXML
    private Label adresa;

    @FXML
    private Label imeText;

    @FXML
    private Label prezimeText;

    @FXML
    private Label polText;

    @FXML
    private Label datumRodjenjaText;

    @FXML
    private Label jmbgText;

    @FXML
    private Label emailText;

    @FXML
    private Label brojTelefonaText;

    @FXML
    private Label adresaText;

    @FXML
    private Button zakazivanjePregleda;

    @FXML
    private Button prikazPregleda;

    private Executor executor;

    private Doktor doktor;

    private Session sesija;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        doktor = DBHelper.selectDoktor(id);
        if (doktor != null) {

            imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
            prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
            polColumn.setCellValueFactory(new PropertyValueFactory<>("pol"));
            datumRodjenjaColumn.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
            jmbgColumn.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            brojTelefonaColumn.setCellValueFactory(new PropertyValueFactory<>("brojTelefona"));
            adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));

            Task<List<Pacijent>> selectAllPacijentTask = new Task<List<Pacijent>>() {
                @Override
                public List<Pacijent> call() throws Exception {
                    return DBHelper.selectAllPacijent(id);
                }
            };

            selectAllPacijentTask.setOnFailed(e -> {
                selectAllPacijentTask.getException().printStackTrace();
                System.out.println("GRESKA!");
            });

            selectAllPacijentTask.setOnSucceeded(e -> tabela.getItems().setAll(FXCollections.observableArrayList(selectAllPacijentTask.getValue())));

            executor.execute(selectAllPacijentTask);
        } else {
            System.exit(0);
        }
    }

    @FXML
    public void prikaziPacijenta() {

        Pacijent pacijent = tabela.getSelectionModel().getSelectedItem();

        if (pacijent != null) {
            
            prikaziTekst();

            sesija.setAttribute("izabraniPacijent", String.valueOf(pacijent.getPacijentId()));

            setVrednosti(pacijent);

            EventHandler<ActionEvent> event1 = (ActionEvent e) -> {
                prikaziScenu("ZakazivanjePregleda", "Zakazivanje pregleda");
            };

            EventHandler<ActionEvent> event2 = (ActionEvent e) -> {
                prikaziScenu("PacijentPregledi", "Pregled - " + pacijent.getIme() + " " + pacijent.getPrezime());
            };

            zakazivanjePregleda.setOnAction(event1);
            prikazPregleda.setOnAction(event2);
        }

    }

    @FXML
    private void prikaziTekst() {
        imeText.setVisible(true);
        prezimeText.setVisible(true);
        polText.setVisible(true);
        datumRodjenjaText.setVisible(true);
        jmbgText.setVisible(true);
        emailText.setVisible(true);
        brojTelefonaText.setVisible(true);
        adresaText.setVisible(true);
        ime.setVisible(true);
        prezime.setVisible(true);
        pol.setVisible(true);
        datumRodjenja.setVisible(true);
        jmbg.setVisible(true);
        email.setVisible(true);
        brojTelefona.setVisible(true);
        adresa.setVisible(true);
        zakazivanjePregleda.setVisible(true);
        prikazPregleda.setVisible(true);
    }

    @FXML
    private void setVrednosti(Pacijent pacijent) {
        ime.setText(pacijent.getIme());
        prezime.setText(pacijent.getPrezime());
        pol.setText(pacijent.getPol());
        datumRodjenja.setText(pacijent.getDatumRodjenja());
        jmbg.setText(pacijent.getJmbg());
        email.setText(pacijent.getEmail());
        brojTelefona.setText(pacijent.getBrojTelefona());
        adresa.setText(pacijent.getAdresa());
    }

    @FXML
    private void prikaziScenu(String ime, String naslov) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/" + ime + ".fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(naslov);
            //reg.getIcons().add(new Image("file:img/globe.png"));
            stage.setResizable(false);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
