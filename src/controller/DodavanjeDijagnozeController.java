package controller;

import database.DBHelper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Bolest;
import model.Doktor;
import model.Lek;
import model.Pregled;
import utils.Session;

public class DodavanjeDijagnozeController implements Initializable {

    @FXML
    private ComboBox<Bolest> bolesti;

    @FXML
    private ComboBox<Lek> lekovi;

    @FXML
    private TextArea opis;

    @FXML
    private Button potvrda;

    @FXML
    private Button izlaz;

    private Doktor doktor;

    private Pregled izabraniPregled;

    private Session sesija;

    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        sesija = Session.getInstance();
        int doktorId = Integer.parseInt(sesija.getAttribute("id"));
        int pregledId = Integer.parseInt(sesija.getAttribute("izabraniPregled"));
        doktor = DBHelper.selectDoktor(doktorId);
        izabraniPregled = DBHelper.selectPregled(pregledId);

        if (doktor != null) {

            if (izabraniPregled != null) {

                Task<List<Bolest>> selectBolestiTask = new Task<List<Bolest>>() {
                    @Override
                    public List<Bolest> call() throws Exception {
                        return DBHelper.selectAllBolest();
                    }
                };

                Task<List<Lek>> selectLekoviTask = new Task<List<Lek>>() {
                    @Override
                    public List<Lek> call() throws Exception {
                        return DBHelper.selectAllLek();
                    }
                };

                selectBolestiTask.setOnFailed(e -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Došlo je do greške priliko učitavanaja podataka.");

                    alert.showAndWait();
                });

                selectBolestiTask.setOnSucceeded(e -> bolesti.setItems(FXCollections.observableArrayList(selectBolestiTask.getValue())));

                selectLekoviTask.setOnFailed(e -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Došlo je do greške priliko učitavanaja podataka.");

                    alert.showAndWait();
                });

                selectLekoviTask.setOnSucceeded(e -> lekovi.setItems(FXCollections.observableArrayList(selectLekoviTask.getValue())));

                executor.execute(selectBolestiTask);
                executor.execute(selectLekoviTask);

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Morate da se prijaviti da bi ste pristupili ovoj stranici.");

            alert.showAndWait();
            System.exit(0);
        }

    }

    @FXML
    void dodavanjeDijagnoze() {
        Bolest bolest = bolesti.getSelectionModel().getSelectedItem();
        Lek lek = lekovi.getSelectionModel().getSelectedItem();
        String opisText = opis.getText();
        Platform.runLater(() -> {
            DBHelper.insertDijagnoza(bolest.getId(), izabraniPregled.getId(), opisText);
            int dijagnozaId = DBHelper.dijagnozaHelper(izabraniPregled.getId());
            DBHelper.insertRecept(lek.getId(), ++dijagnozaId);
            izabraniPregled.setOdrzan(true);
            DBHelper.updatePregled(izabraniPregled.getId(), izabraniPregled.getDatum(), izabraniPregled.getVreme(), izabraniPregled.isOdrzan());
            potvrda.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacija");
            alert.setHeaderText(null);
            alert.setContentText("Dijagnoza je uspešno dodata.");

            alert.showAndWait();
        });
    }

    @FXML
    void zatvaranjeProzora() {
        Stage stage = (Stage) izlaz.getScene().getWindow();
        stage.close();
    }

}
