package controller;

import database.DBHelper;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import model.Doktor;
import utils.Session;

public class PocetnaDoktorController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        Doktor doktor = DBHelper.selectDoktor(id);
        System.out.println(doktor.toString());
    }

}

