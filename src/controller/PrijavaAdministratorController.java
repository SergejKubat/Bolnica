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
    private ImageView nazad;

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
            Administrator admin = DBHelper.prijavaAdministrator(email, lozinka);
            if (admin != null) {
                Session sesija = Session.getInstance();
                sesija.setAttribute("id", String.valueOf(admin.getId()));
                System.out.println(admin.toString());
            }
            else {
                System.out.println("p ogresni kredencijali");
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
