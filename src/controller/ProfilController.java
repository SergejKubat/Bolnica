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
    
    Doktor doktor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        doktor = DBHelper.selectDoktor(id);
        if (doktor != null) {
            dobrodoslica.setText(dobrodoslica.getText() + doktor.getIme());
        } else {
            System.exit(0);
        }
    }

}
