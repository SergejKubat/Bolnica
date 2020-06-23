package controller;

import database.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Doktor;
import model.Pregled;
import utils.Session;

public class PreglediController implements Initializable {

    @FXML
    private TableView<Pregled> zakazaniPreglediTabela;

    @FXML
    private TableColumn<Pregled, String> datumColumn;

    @FXML
    private TableColumn<Pregled, String> vremeColumn;

    @FXML
    private TableView<Pregled> obavljeniPreglediTabela;

    @FXML
    private TableColumn<Pregled, String> datumColumn1;

    @FXML
    private TableColumn<Pregled, String> vremeColumn1;

    @FXML
    private Label pacijentText;

    @FXML
    private Label pacijent;

    @FXML
    private Label datumText;

    @FXML
    private Label datum;

    @FXML
    private Label vremeText;

    @FXML
    private Label vreme;

    @FXML
    private Label odrzanText;

    @FXML
    private Label odrzan;

    @FXML
    private Button rezultatPregleda;

    @FXML
    private Button dodajDijagnozu;

    @FXML
    private Button noviPregled;

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

            datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
            vremeColumn.setCellValueFactory(new PropertyValueFactory<>("vreme"));

            datumColumn1.setCellValueFactory(new PropertyValueFactory<>("datum"));
            vremeColumn1.setCellValueFactory(new PropertyValueFactory<>("vreme"));

            Task<List<Pregled>> selectZakazaniPreglediTask = new Task<List<Pregled>>() {
                @Override
                public List<Pregled> call() throws Exception {
                    return DBHelper.selectAllPregled(id).stream().filter(e -> !e.isOdrzan()).collect(Collectors.toList());
                }
            };

            Task<List<Pregled>> selectObavljeniPreglediTask = new Task<List<Pregled>>() {
                @Override
                public List<Pregled> call() throws Exception {
                    return DBHelper.selectAllPregled(id).stream().filter(e -> e.isOdrzan()).collect(Collectors.toList());
                }
            };

            selectZakazaniPreglediTask.setOnFailed(e -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Upozorenje");
                alert.setHeaderText(null);
                alert.setContentText("Došlo je do greške prilikom učitavanja podataka.");

                alert.showAndWait();
            });

            selectZakazaniPreglediTask.setOnSucceeded(e -> zakazaniPreglediTabela.getItems().setAll(FXCollections.observableArrayList(selectZakazaniPreglediTask.getValue())));

            selectObavljeniPreglediTask.setOnFailed(e -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Upozorenje");
                alert.setHeaderText(null);
                alert.setContentText("Došlo je do greške prilikom učitavanja podataka.");

                alert.showAndWait();
            });

            selectObavljeniPreglediTask.setOnSucceeded(e -> obavljeniPreglediTabela.getItems().setAll(FXCollections.observableArrayList(selectObavljeniPreglediTask.getValue())));

            executor.execute(selectZakazaniPreglediTask);
            executor.execute(selectObavljeniPreglediTask);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Morate se prijaviti da bi ste pristupili ovoj stranici.");

            alert.showAndWait();
            System.exit(0);
        }

    }

    @FXML
    void dodajPregled() {
        prikaziScenu("ZakazivanjePregleda", "Zakazivanje pregleda");
    }

    @FXML
    void prikaziZakazanPregled() {

        Pregled pregled = zakazaniPreglediTabela.getSelectionModel().getSelectedItem();

        if (pregled != null) {

            prikaziText(pregled.isOdrzan());

            sesija.setAttribute("izabraniPregled", String.valueOf(pregled.getId()));
            sesija.setAttribute("izabraniPacijent", String.valueOf(pregled.getPacijentId().getPacijentId()));

            setVrednosti(pregled);

            EventHandler<ActionEvent> event1 = (ActionEvent e) -> {
                prikaziScenu("RezultatPregleda", "Rezultat pregleda");
            };

            EventHandler<ActionEvent> event2 = (ActionEvent e) -> {
                prikaziScenu("DodavanjeDijagnoze", "Dodavanje dijagnoze");
            };

            if (pregled.isOdrzan()) {
                rezultatPregleda.setOnAction(event1);
            }
            dodajDijagnozu.setOnAction(event2);
        }

    }

    @FXML
    void prikaziObavljenPregled() {
        Pregled pregled = obavljeniPreglediTabela.getSelectionModel().getSelectedItem();

        if (pregled != null) {

            prikaziText(pregled.isOdrzan());

            sesija.setAttribute("izabraniPregled", String.valueOf(pregled.getId()));
            sesija.setAttribute("izabraniPacijent", String.valueOf(pregled.getPacijentId().getPacijentId()));

            setVrednosti(pregled);

            EventHandler<ActionEvent> event1 = (ActionEvent e) -> {
                prikaziScenu("RezultatPregleda", "Rezultat pregleda");
            };

            EventHandler<ActionEvent> event2 = (ActionEvent e) -> {
                prikaziScenu("DodavanjeDijagnoze", "Dodavanje dijagnoze");
            };

            if (pregled.isOdrzan()) {
                rezultatPregleda.setOnAction(event1);
            }
            dodajDijagnozu.setOnAction(event2);
        }
    }

    @FXML
    private void prikaziText(boolean isOdrzan) {
        pacijentText.setVisible(true);
        datumText.setVisible(true);
        vremeText.setVisible(true);
        odrzanText.setVisible(true);
        pacijent.setVisible(true);
        datum.setVisible(true);
        vreme.setVisible(true);
        odrzan.setVisible(true);
        rezultatPregleda.setVisible(isOdrzan);
        dodajDijagnozu.setVisible(true);
    }

    @FXML
    private void setVrednosti(Pregled pregled) {
        pacijent.setText(pregled.getPacijentId().getIme() + " " + pregled.getPacijentId().getPrezime());
        datum.setText(pregled.getDatum());
        vreme.setText(pregled.getVreme());
        odrzan.setText(pregled.isOdrzan() ? "Da" : "Ne");
    }

    @FXML
    private void prikaziScenu(String ime, String naslov) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/" + ime + ".fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(naslov);
            stage.getIcons().add(new Image("file:img/logo.png"));
            stage.setResizable(false);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Stranica nije pronadjena.");

            alert.showAndWait();
        }
    }

}
