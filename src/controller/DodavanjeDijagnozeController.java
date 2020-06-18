package controller;

import database.DBHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import model.Bolest;
import model.Doktor;
import model.Lek;
import model.Pregled;
import utils.Session;

public class DodavanjeDijagnozeController implements Initializable {
    
    @FXML
    private ComboBox<Bolest> bolesti;

    @FXML
    private ComboBox<Lek> lekovi;

    @FXML
    private TextArea opis;

    @FXML
    private Button dodajDijagnozu;

    private Doktor doktor;
    
    private Pregled izabraniPregled;
    
    private Session sesija;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sesija = Session.getInstance();
        int doktorId = Integer.parseInt(sesija.getAttribute("id"));
        int pregledId = Integer.parseInt(sesija.getAttribute("izabraniPregled"));
        doktor = DBHelper.selectDoktor(doktorId);
        izabraniPregled = DBHelper.selectPregled(pregledId);
        
        if (doktor != null) {
            
        } else {
            System.exit(0);
        }
        
    }
    
    @FXML
    void dodavanjeDijagnoze() {
        Bolest bolest = bolesti.getSelectionModel().getSelectedItem();
        Lek lek = lekovi.getSelectionModel().getSelectedItem();
        String opisText = opis.getText();
        Platform.runLater(() -> {
            
        });
    }
    
}
