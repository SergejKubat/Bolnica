package controller;

import database.DBHelper;
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
import main.Main;
import model.Doktor;
import utils.Mail;
import utils.Utilities;
import utils.Validation;

public class ZaboravljenaLozinkaController implements Initializable {

    @FXML
    private TextField emailInput;

    @FXML
    private Button resetujLozinkuBtn;

    @FXML
    private ImageView emailError;

    @FXML
    private ImageView sigurnosniKodError;

    @FXML
    private Label nazadBtn;

    @FXML
    private Label emailSavet;

    @FXML
    private TextField sigurnosniKodInput;

    @FXML
    private Label sigurnosniKodSavet;

    @FXML
    private Button posaljiKodBtn;

    private String sigurnosniKod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sigurnosniKod = Utilities.generisiSigurnosniKod();

        emailError.setVisible(false);
        sigurnosniKodError.setVisible(false);
        emailSavet.setVisible(false);
        sigurnosniKodSavet.setVisible(false);
        sigurnosniKodInput.setDisable(true);
        posaljiKodBtn.setDisable(true);
        resetujLozinkuBtn.setDisable(true);
    }

    @FXML
    public void nazad() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/PrijavaDoktor.fxml"));
        Main.stage.getScene().setRoot(root);
    }

    @FXML
    public void posaljiKod() {
        String email = emailInput.getText();
        Doktor doktor = DBHelper.selectDoktorByEmail(email);
        System.out.println(sigurnosniKod);
        if (doktor != null) {
            /*Platform.runLater(() -> {
                try {*/
                    Mail.sendMail(doktor, sigurnosniKod);
                    System.out.println("POSLATO");
                /*} catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            });*/
            sigurnosniKodInput.setDisable(false);
        }
    }

    @FXML
    public void resetujLozinku() {
        String uneseniSigurnosniKod = sigurnosniKodInput.getText();
        if (uneseniSigurnosniKod.equals(sigurnosniKod)) {

        } else {

        }
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
            posaljiKodBtn.setDisable(false);
        }
    }

    @FXML
    public void validateSigurnosniKodInput() {
        String sigKod = sigurnosniKodInput.getText();
        if (!Validation.proveriSigurnosniKod(sigKod)) {
            sigurnosniKodError.setVisible(true);
            sigurnosniKodSavet.setVisible(true);
            sigurnosniKodInput.setStyle("-fx-border-color: #CF0808");
        } else {
            sigurnosniKodError.setVisible(false);
            sigurnosniKodSavet.setVisible(false);
            sigurnosniKodInput.setStyle("-fx-border-color: #41E443");
            resetujLozinkuBtn.setDisable(false);
        }
    }

}
