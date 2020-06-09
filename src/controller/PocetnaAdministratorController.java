package controller;

import database.DBHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.Administrator;
import utils.Session;

public class PocetnaAdministratorController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        Administrator admin = DBHelper.selectAdministrator(id);
        System.out.println(admin.toString());
    }    
    
}
