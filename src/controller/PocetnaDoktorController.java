package controller;

import database.DBHelper;
import java.io.IOException;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Doktor;
import utils.Session;

public class PocetnaDoktorController implements Initializable {

    @FXML
    private VBox navigacija;

    @FXML
    private Label imePrezime;

    @FXML
    private Button profil;

    @FXML
    private Button pacijenti;

    @FXML
    private Button pregledi;

    @FXML
    private Button odjava;

    @FXML
    private Pane prikaz;

    private Doktor doktor;

    private Session sesija;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        doktor = DBHelper.selectDoktor(id);
        if (doktor != null) {
            imePrezime.setText(doktor.getIme() + " " + doktor.getPrezime());
            try {
                prikaziProfil();
            } catch (IOException ex) {
                System.out.println(ex);
            }
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
    void prikaziPacijente() throws IOException {
        Parent pacijenti = FXMLLoader.load(getClass().getResource("/view/Pacijenti.fxml"));
        prikaz.getChildren().setAll(pacijenti);
    }

    @FXML
    void prikaziPreglede() throws IOException {
        Parent pregledi = FXMLLoader.load(getClass().getResource("/view/Pregledi.fxml"));
        prikaz.getChildren().setAll(pregledi);
    }

    @FXML
    void prikaziProfil() throws IOException {
        Parent profil = FXMLLoader.load(getClass().getResource("/view/Profil.fxml"));
        prikaz.getChildren().setAll(profil);
    }

    @FXML
    void odjaviSe() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Odjava");
        alert.setHeaderText(null);
        alert.setContentText("Da li ste siguri da želite da se odjavite?");
        Optional<ButtonType> akcija = alert.showAndWait();

        if (akcija.get() == ButtonType.OK) {
            try {
                sesija.clear();
                Platform.exit();
            } catch (BackingStoreException ex) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Greška");
                alert1.setHeaderText(null);
                alert1.setContentText("Došlo je do greške prilikom odjave.");

                alert1.showAndWait();
            }
        }
    }

}
