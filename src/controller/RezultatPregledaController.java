package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.Dijagnoza;
import model.Doktor;
import model.Pregled;
import utils.Session;

public class RezultatPregledaController implements Initializable {

    @FXML
    private Button izlaz;

    @FXML
    private TableView<Dijagnoza> dijagnoze;
    
    private Doktor doktor;
    
    private Pregled izabraniPregled;
    
    private Session sesija;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void zatvori() {

    }

}
