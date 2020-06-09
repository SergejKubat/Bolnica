package controller;

import database.DBHelper;
import model.Doktor;
import utils.Session;
import utils.Validation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private ImageView emailError;

    @FXML
    private ImageView lozinkaError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailError.setVisible(false);
        lozinkaError.setVisible(false);
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
                System.out.println(doktor.toString());
            } else {
                System.out.println("Pogresni kredencijali");
            }
        } else {
            emailError.setVisible(true);
            lozinkaError.setVisible(true);
            emailInput.setStyle("-fx-border-color: red");
            lozinkaInput.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    public void openZabLozinka() {
        
    }

    @FXML
    public void openPrijavaAdmin() throws IOException {
        Parent adminLogin = FXMLLoader.load(getClass().getResource("/view/PrijavaAdministrator.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(adminLogin);
    }

}
