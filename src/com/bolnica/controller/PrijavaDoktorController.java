package com.bolnica.controller;

import com.bolnica.database.DBHelper;
import com.bolnica.model.Doktor;
import com.bolnica.utils.Session;
import com.bolnica.utils.Validation;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PrijavaDoktorController implements Initializable {

    @FXML
    private Pane contentArea;

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
            Doktor doktor = DBHelper.prijavaDoktor(email, lozinka);
            if (doktor != null) {
                Session sesija = Session.getInstance();
                sesija.setAttribute("id", String.valueOf(doktor.getId()));
            } else {
                System.out.println("pogresni kredencijali");
            }
        } else {
            System.out.println("pogresan format");
        }
    }

    @FXML
    public void openZabLozinka() {
        System.out.println("Zaboravljena lozinka");
        String firstPath = "C:/Users/Andrej Kubat/Documents/NetBeansProjects/Bolnica/src/com/bolnica/controller/PrijavaDoktorController.java"; // test example

        String secondPath = "C:/Users/Andrej Kubat/Documents/NetBeansProjects/Bolnica/src/com/bolnica/view/PrijavaAdministrator.fxml"; // test example

        Path first = Paths.get(firstPath);
        Path second = Paths.get(secondPath);
        System.out.println(first.relativize(second));
        System.out.println(second.relativize(first));
    }

    @FXML
    public void openPrijavaAdmin() throws IOException {
        Parent adminLogin = FXMLLoader.load(getClass().getResource("../../view/PrijavaAdministrator.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(adminLogin);
    }

}
