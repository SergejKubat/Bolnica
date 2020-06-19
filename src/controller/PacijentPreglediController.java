package controller;

import database.DBHelper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Doktor;
import model.Pacijent;
import model.Pregled;
import utils.Session;

public class PacijentPreglediController implements Initializable {

    @FXML
    private TableView<Pregled> preglediTabela;

    @FXML
    private TableColumn<Pregled, String> datumColumn;

    @FXML
    private TableColumn<Pregled, String> vremeColumn;

    @FXML
    private TableColumn<Pregled, String> odrzanColumn;

    @FXML
    private Button izlaz;

    @FXML
    private Label bolestText;

    @FXML
    private Label lekText;

    @FXML
    private Label opisText;

    @FXML
    private TextArea opis;

    @FXML
    private Label bolest;

    @FXML
    private Label lek;
    
    private Doktor doktor;
    
    private Pacijent izabraniPacijent;
    
    private Session sesija;
    
    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        sesija = Session.getInstance();
        int doktorId = Integer.parseInt(sesija.getAttribute("id"));
        int pacijentId = Integer.parseInt(sesija.getAttribute("izabraniPacijent"));
        doktor = DBHelper.selectDoktor(doktorId);
        izabraniPacijent = DBHelper.selectPacijent(pacijentId);
        
        if (doktor != null && izabraniPacijent != null) {
            
            datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
            vremeColumn.setCellValueFactory(new PropertyValueFactory<>("vreme"));
            odrzanColumn.setCellValueFactory(new PropertyValueFactory<>("odrzan"));
            
            Task<List<Pregled>> selectPreglediTask = new Task<List<Pregled>>() {
                @Override
                public List<Pregled> call() throws Exception {
                    return DBHelper.selectAllPregledByPacijent(pacijentId);
                }
            };

            selectPreglediTask.setOnFailed(e -> {
                selectPreglediTask.getException().printStackTrace();
                System.out.println("GRESKA!");
            });

            selectPreglediTask.setOnSucceeded(e -> preglediTabela.setItems(FXCollections.observableArrayList(selectPreglediTask.getValue())));
            
            executor.execute(selectPreglediTask);
            
        }

    }

    @FXML
    void prikaziDijagnozu() {
        Pregled izabraniPregled = preglediTabela.getSelectionModel().getSelectedItem();
        
        if (izabraniPregled != null) {
            
            prikaziText();
            
            setVrednosti(izabraniPregled);
            
        }
    }
    
    @FXML
    void prikaziText() {
        bolestText.setVisible(true);
        lekText.setVisible(true);
        opisText.setVisible(true);
        bolest.setVisible(true);
        lek.setVisible(true);
        opis.setVisible(true);
    }
    
    @FXML
    void setVrednosti(Pregled pregled) {
        bolest.setText(pregled.getDijagnoza().get(0).getBolestId().getNaziv());
        lek.setText(pregled.getDijagnoza().get(0).getRecepti().get(0).getLekId().getNaziv());
        opis.setText(pregled.getDijagnoza().get(0).getOpis());
    }
    
    @FXML
    void zatvaranjeProzora() {
        Stage stage = (Stage) izlaz.getScene().getWindow();
        stage.close();
    }

}
