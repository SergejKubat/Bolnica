package controller;

import database.DBHelper;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Doktor;
import model.Pacijent;
import utils.Session;

public class ZakazivanjePregledaController implements Initializable {

    @FXML
    private Button zakazivanje;

    @FXML
    private Label pacijent;

    @FXML
    private Label doktor;

    @FXML
    private DatePicker datum;

    @FXML
    private TextField vreme;

    private Doktor doktor2;

    private Pacijent izabraniPacijent;

    private Session sesija;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sesija = Session.getInstance();
        int doktorId = Integer.parseInt(sesija.getAttribute("id"));
        int pacijentId = Integer.parseInt(sesija.getAttribute("izabraniPacijent"));
        doktor2 = DBHelper.selectDoktor(doktorId);
        izabraniPacijent = DBHelper.selectPacijent(pacijentId);
        if (doktor2 != null && pacijent != null) {
            pacijent.setText(izabraniPacijent.getIme() + " " + izabraniPacijent.getPrezime());
            doktor.setText(doktor2.getIme() + " " + doktor2.getPrezime());
        } else {
            System.exit(0);
        }
    }

    @FXML
    void zakaziPregled() {
        String datumText = datum.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        String vremeText = vreme.getText();
        System.out.println(datumText);
        Platform.runLater(() -> {
            DBHelper.insertPregled(izabraniPacijent.getPacijentId(), doktor2.getId(), datumText, vremeText);
            System.out.println("OK");
        });
        zakazivanje.setDisable(true);
    }

}
