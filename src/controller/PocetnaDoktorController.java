package controller;

import database.DBHelper;
import java.io.IOException;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
    private Pane prikaz;

    Doktor doktor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        System.out.println(id);
        doktor = DBHelper.selectDoktor(id);
        if (doktor != null) {
            imePrezime.setText(doktor.getIme() + " " + doktor.getPrezime());
            try {
                prikaziProfil();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
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

}
