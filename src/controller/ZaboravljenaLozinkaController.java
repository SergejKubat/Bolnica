package controller;

import database.DBHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import model.Doktor;
import utils.Mail;
import utils.Session;
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
    private Label poruka;

    @FXML
    private Button posaljiKodBtn;
    
    @FXML
    private Doktor doktor;
    
    @FXML
    private Session sesija;
    
    private String sigurnosniKod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sesija = Session.getInstance();
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
        doktor = DBHelper.selectDoktorByEmail(email);
        System.out.println(sigurnosniKod);
        if (doktor != null) {
            poruka.setVisible(true);
            Platform.runLater(() -> {
                try {
                    boolean isMailPoslat = Mail.sendMail(doktor, sigurnosniKod);
                    if (isMailPoslat) {
                        poruka.setText("Sigurnosni kod je poslat na vasu email adresu.");
                        sigurnosniKodInput.setDisable(false);
                    } else {
                        poruka.setText("Poruka nije poslata. Probajte ponovo.");
                    }
                } catch (Exception ex) {
                    poruka.setText("Doslo je do greske prilikom slanja sigurnosnog koda.");
                }
            });
        }
    }

    @FXML
    public void promeniLozinku() {
        String uneseniSigurnosniKod = sigurnosniKodInput.getText();
        if (uneseniSigurnosniKod.equals(sigurnosniKod)) {
            sesija.setAttribute("promenaLozinkeDoktorId", String.valueOf(doktor.getId()));
            prikaziScenu("PromenaLozinke", doktor.getIme() + " " + doktor.getPrezime() + " - Promena lozinke");
        } else {
            System.out.println("Sigurnosni kod je netacan!");
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

    @FXML
    private void prikaziScenu(String ime, String naslov) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/" + ime + ".fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(naslov);
            stage.getIcons().add(new Image("file:img/logo.png"));
            stage.setResizable(false);
            stage.sizeToScene();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

}
