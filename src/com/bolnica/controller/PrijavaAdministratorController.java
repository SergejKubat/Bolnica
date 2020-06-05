package com.bolnica.controller;

import com.bolnica.database.DBHelper;
import com.bolnica.model.Administrator;
import com.bolnica.model.Doktor;
import com.bolnica.utils.Session;
import com.bolnica.utils.Validation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrijavaAdministratorController implements Initializable {
    
    @FXML
    private TextField emailInput;

    @FXML
    private TextField lozinkaInput;

    @FXML
    private Label zabLozinkaText;

    @FXML
    private Button prijavaBtn;

    @FXML
    private Label prijavaAdminText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    public void prijava() {
        String email = emailInput.getText();
        String lozinka = lozinkaInput.getText();
        boolean valid = Validation.proveriEmail(email) && Validation.proveriLozinku(lozinka);
        if (valid) {
            Administrator admin = DBHelper.prijavaAdministrator(email, lozinka);
            if (admin != null) {
                Session sesija = Session.getInstance();
                sesija.setAttribute("id", String.valueOf(admin.getId()));
            }
            else {
                System.out.println("pogresni kredencijali");
            }
        }
        else {
            System.out.println("pogresan format");
        }
    }
    
    @FXML 
    public void openZabLozinka() {
        System.out.println("Zaboravljena lozinka");
    }
    
    @FXML
    public void openPrijavaDoktor() {
        System.out.println("Prijava admin");
    }
    
}
