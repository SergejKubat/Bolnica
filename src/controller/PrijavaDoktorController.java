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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    @FXML
    private Label emailSavet;

    @FXML
    private Label lozinkaSavet;

    private Session sesija;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*sesija = Session.getInstance();
        String id = sesija.getAttribute("id");
        if (id != null) {
            try {
                prikaziPocetnu();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {*/
        emailError.setVisible(false);
        lozinkaError.setVisible(false);
        emailSavet.setVisible(false);
        lozinkaSavet.setVisible(false);
        prijavaBtn.setDisable(true);
        /*}*/
    }

    @FXML
    public void validateEmailInput(KeyEvent e) {

        String email = emailInput.getText();
        if (!Validation.proveriEmail(email)) {
            emailError.setVisible(true);
            emailSavet.setVisible(true);
            emailInput.setStyle("-fx-border-color: #CF0808");
        } else {
            if (e.getCode() == KeyCode.ENTER) {
                lozinkaInput.requestFocus();
            }
            emailError.setVisible(false);
            emailSavet.setVisible(false);
            emailInput.setStyle("-fx-border-color: #41E443");
            checkValues();
        }
    }

    @FXML
    public void validateLozinkaInput(KeyEvent e) {
        String lozinka = lozinkaInput.getText();
        if (!Validation.proveriLozinku(lozinka)) {
            lozinkaError.setVisible(true);
            lozinkaSavet.setVisible(true);
            lozinkaInput.setStyle("-fx-border-color: #CF0808");
        } else {
            if (e.getCode() == KeyCode.ENTER) {
                prijava();
            }
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
        } else {
            prijavaBtn.setDisable(true);
        }
    }

    @FXML
    public void prijava() {
        // email: nikola@gmail.com, lozinka: Testovi123@
        String email = emailInput.getText();
        String lozinka = lozinkaInput.getText();
        boolean valid = Validation.proveriEmail(email) && Validation.proveriLozinku(lozinka);
        if (valid) {
            Doktor doktor = DBHelper.prijavaDoktor(email, lozinka);
            if (doktor != null) {
                sesija = Session.getInstance();
                sesija.setAttribute("id", String.valueOf(doktor.getId()));
                System.out.println(doktor.toString());
                try {
                    prikaziPocetnu();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println("Pogresni kredencijali");
            }
        } else {
            System.out.println("pogresan format");
        }
    }

    @FXML
    public void openZabLozinka() throws IOException {
        Parent zaboravljenaLozinka = FXMLLoader.load(getClass().getResource("/view/ZaboravljenaLozinka.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(zaboravljenaLozinka);
    }

    @FXML
    public void openPrijavaAdmin() throws IOException {
        Parent adminLogin = FXMLLoader.load(getClass().getResource("/view/PrijavaAdministrator.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(adminLogin);
    }

    @FXML
    public void prikaziPocetnu() throws IOException {
        emailInput.getScene().getWindow().hide();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/PocetnaDoktor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Doktor - Pocetna");
        //reg.getIcons().add(new Image("file:img/globe.png"));
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

}
