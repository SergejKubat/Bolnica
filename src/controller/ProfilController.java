package controller;

import database.DBHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Doktor;
import utils.Session;

public class ProfilController implements Initializable {

    @FXML
    private Label dobrodoslica;

    @FXML
    private Label tip;

    @FXML
    private Label odeljenje;

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

    Doktor doktor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        doktor = DBHelper.selectDoktor(id);
        if (doktor != null) {
            dobrodoslica.setText(dobrodoslica.getText() + " " + doktor.getIme() + " " + doktor.getPrezime());
            tip.setText(doktor.getDoktorTipId().getNaziv());
            odeljenje.setText(doktor.getOdeljenjeId().getNaziv());
            pol.setText(doktor.getPol());
            datumRodjenja.setText(doktor.getDatumRodjenja());
            jmbg.setText(doktor.getJmbg());
            email.setText(doktor.getEmail());
            brojTelefona.setText(doktor.getBrojTelefona());
            adresa.setText(doktor.getAdresa());
        } else {
            System.exit(0);
        }
    }

}