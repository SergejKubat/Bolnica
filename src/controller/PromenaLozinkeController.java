package controller;

import database.DBHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Doktor;
import utils.HashUtil;
import utils.Session;
import utils.Validation;

public class PromenaLozinkeController implements Initializable {

    @FXML
    private TextField lozinkaInput;

    @FXML
    private TextField ponovljenaLozinkaInput;

    @FXML
    private Button potvrda;

    @FXML
    private ImageView lozinkaError;

    @FXML
    private ImageView ponovljenaLozinkaError;

    @FXML
    private Label lozinkaSavet;

    @FXML
    private Label ponovljenaLozinkaSavet;

    @FXML
    private Label poruka;

    @FXML
    private Session sesija;

    @FXML
    private Doktor doktor;

    private String sigurnosniKod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        sesija = Session.getInstance();
        int doktorId = Integer.parseInt(sesija.getAttribute("promenaLozinkeDoktorId"));
        doktor = DBHelper.selectDoktor(doktorId);

        if (doktor == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Morate se prijaviti da bi ste pristupili ovoj stranici.");

            alert.showAndWait();
            System.exit(0);
        }

    }

    @FXML
    void promenaLozinke() {
        String lozinka = lozinkaInput.getText();
        String ponovljenaLozinka = ponovljenaLozinkaInput.getText();
        boolean valid = Validation.proveriLozinku(lozinka) && Validation.proveriLozinku(ponovljenaLozinka);

        poruka.setVisible(true);

        if (valid) {
            Platform.runLater(() -> {
                String hash = HashUtil.getSHA(lozinka);
                try {
                    DBHelper.updateDoktor(doktor.getId(), doktor.getIme(), doktor.getPrezime(), doktor.getPol(), doktor.getJmbg(), doktor.getEmail(), doktor.getBrojTelefona(),
                            doktor.getAdresa(), doktor.getOpis(), hash, doktor.isBlokiran());
                    poruka.setText("Lozinka je uspesno promenjena.");
                } catch (Exception ex) {
                    poruka.setText("Doslo je do greske. Pokusajte ponovo.");
                }
            });
        } else {
            poruka.setText("Podaci nisu u validnom formatu.");
        }
    }

    @FXML
    void validateLozinkaInput(KeyEvent e) {
        String lozinka = lozinkaInput.getText();
        if (!Validation.proveriLozinku(lozinka)) {
            lozinkaError.setVisible(true);
            lozinkaSavet.setVisible(true);
            lozinkaInput.setStyle("-fx-border-color: #CF0808");
        } else {
            if (e.getCode() == KeyCode.ENTER) {
                ponovljenaLozinkaInput.requestFocus();
            }
            lozinkaError.setVisible(false);
            lozinkaSavet.setVisible(false);
            lozinkaInput.setStyle("-fx-border-color: #41E443");
            checkValues();
        }
    }

    @FXML
    void validatePonovljenaLozinkaInput(KeyEvent e) {
        String ponovljenaLozinka = ponovljenaLozinkaInput.getText();
        if (!Validation.proveriLozinku(ponovljenaLozinka)) {
            ponovljenaLozinkaError.setVisible(true);
            ponovljenaLozinkaSavet.setVisible(true);
            ponovljenaLozinkaInput.setStyle("-fx-border-color: #CF0808");
        } else {
            if (e.getCode() == KeyCode.ENTER) {
                promenaLozinke();
            }
            ponovljenaLozinkaError.setVisible(false);
            ponovljenaLozinkaSavet.setVisible(false);
            ponovljenaLozinkaInput.setStyle("-fx-border-color: #41E443");
            checkValues();
        }
    }

    @FXML
    public void checkValues() {
        String lozinka = lozinkaInput.getText();
        String ponovljenaLozinka = ponovljenaLozinkaInput.getText();
        if (Validation.proveriLozinku(lozinka) && Validation.proveriLozinku(ponovljenaLozinka)) {
            potvrda.setDisable(false);
        } else {
            potvrda.setDisable(false);
        }
    }

}
