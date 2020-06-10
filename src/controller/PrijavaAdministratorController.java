package controller;

import main.Main;
import database.DBHelper;
import model.Administrator;
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
    private Label prijavaDoktorText;
    
     @FXML
    private ImageView emailError;

    @FXML
    private ImageView lozinkaError;
    
    @FXML
    private Label emailSavet;

    @FXML
    private Label lozinkaSavet;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emailError.setVisible(false);
        lozinkaError.setVisible(false);
        emailSavet.setVisible(false);
        lozinkaSavet.setVisible(false);
        prijavaBtn.setDisable(true);
    }
    
    @FXML
    public void validateEmailInput() {
        String email = emailInput.getText();
        if (!Validation.proveriEmail(email)) {
            emailError.setVisible(true);
            emailSavet.setVisible(true);
            emailInput.setStyle("-fx-border-color: #CF0808");
        } else {
            emailError.setVisible(false);
            emailSavet.setVisible(false);
            emailInput.setStyle("-fx-border-color: #41E443");
            checkValues();
        }
    }
    
    @FXML
    public void validateLozinkaInput() {
        String lozinka = lozinkaInput.getText();
        if (!Validation.proveriLozinku(lozinka)) {
            lozinkaError.setVisible(true);
            lozinkaSavet.setVisible(true);
            lozinkaInput.setStyle("-fx-border-color: #CF0808");
        } else {
            lozinkaError.setVisible(false);
            lozinkaSavet.setVisible(false);
            lozinkaInput.setStyle("-fx-border-color: #41E443");
            checkValues();
        }
    }
    
    @FXML
    public void checkValues() {
        String email = emailInput.getText();
        String lozinka = lozinkaInput.getText();
        if (Validation.proveriEmail(email) && Validation.proveriLozinku(lozinka)) {
            prijavaBtn.setDisable(false);
        }
        else {
            prijavaBtn.setDisable(true);
        }
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
                System.out.println(admin.toString());
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
    public void openPrijavaDoktor() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/PrijavaDoktor.fxml"));
        Main.stage.getScene().setRoot(root);
    }
    
}
