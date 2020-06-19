package controller;

import database.DBHelper;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Doktor;
import model.Pacijent;
import utils.Session;

public class ZakazivanjePregledaController implements Initializable {

    @FXML
    private Button zakazivanje;
    
    @FXML
    private Button izlaz;

    @FXML
    private ComboBox<Pacijent> pacijenti;

    @FXML
    private Label doktor;

    @FXML
    private DatePicker datum;

    @FXML
    private TextField vreme;

    private Doktor doktor2;

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
        doktor2 = DBHelper.selectDoktor(doktorId);
        izabraniPacijent = DBHelper.selectPacijent(pacijentId);
        if (doktor2 != null) {

            Task<List<Pacijent>> selectPacijentiTask = new Task<List<Pacijent>>() {
                @Override
                public List<Pacijent> call() throws Exception {
                    return DBHelper.selectAllPacijent(doktorId);
                }
            };

            selectPacijentiTask.setOnFailed(e -> {
                selectPacijentiTask.getException().printStackTrace();
                System.out.println("GRESKA!");
            });

            selectPacijentiTask.setOnSucceeded(e -> pacijenti.setItems(FXCollections.observableArrayList(selectPacijentiTask.getValue())));
            
            executor.execute(selectPacijentiTask);

            pacijenti.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    sesija.setAttribute("izabraniPacijent", String.valueOf(newValue.getPacijentId()));
                }
            );

            if (izabraniPacijent != null) {
                pacijenti.getSelectionModel().select(izabraniPacijent);
            }

            doktor.setText(doktor2.getIme() + " " + doktor2.getPrezime());
        } else {
            System.exit(0);
        }
    }

    @FXML
    void zakaziPregled() {
        String datumText = datum.getValue().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
        String vremeText = vreme.getText();
        System.out.println(datumText);
        Platform.runLater(() -> {
            DBHelper.insertPregled(izabraniPacijent.getPacijentId(), doktor2.getId(), datumText, vremeText);
            System.out.println("OK");
            zakazivanje.setDisable(true);
        });
    }
    
    @FXML
    void zatvaranjeProzora() {
        Stage stage = (Stage) izlaz.getScene().getWindow();
        stage.close();
    }

}
