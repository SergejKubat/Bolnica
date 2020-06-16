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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Doktor;
import model.Pacijent;
import utils.Session;

public class PacijentiController implements Initializable {

    @FXML
    private TableView<Pacijent> tabela;

    @FXML
    private TableColumn<Pacijent, String> imeColumn;

    @FXML
    private TableColumn<Pacijent, String> prezimeColumn;

    @FXML
    private TableColumn<Pacijent, String> polColumn;

    @FXML
    private TableColumn<Pacijent, String> datumRodjenjaColumn;

    @FXML
    private TableColumn<Pacijent, String> jmbgColumn;

    @FXML
    private TableColumn<Pacijent, String> emailColumn;

    @FXML
    private TableColumn<Pacijent, String> brojTelefonaColumn;

    @FXML
    private TableColumn<Pacijent, String> adresaColumn;

    private Executor executor;

    private Doktor doktor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        Session sesija = Session.getInstance();
        int id = Integer.parseInt(sesija.getAttribute("id"));
        doktor = DBHelper.selectDoktor(id);
        if (doktor != null) {

            imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
            prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
            polColumn.setCellValueFactory(new PropertyValueFactory<>("pol"));
            datumRodjenjaColumn.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
            jmbgColumn.setCellValueFactory(new PropertyValueFactory<>("jmbg"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            brojTelefonaColumn.setCellValueFactory(new PropertyValueFactory<>("brojTelefona"));
            adresaColumn.setCellValueFactory(new PropertyValueFactory<>("adresa"));

            Task<List<Pacijent>> selectAllPacijentTask = new Task<List<Pacijent>>() {
                @Override
                public List<Pacijent> call() throws Exception {
                    return DBHelper.selectAllPacijent(id);
                }
            };

            selectAllPacijentTask.setOnFailed(e -> {
                selectAllPacijentTask.getException().printStackTrace();
                System.out.println("GRESKA!");
            });

            selectAllPacijentTask.setOnSucceeded(e -> tabela.getItems().setAll(FXCollections.observableArrayList(selectAllPacijentTask.getValue())));

            executor.execute(selectAllPacijentTask);
        } else {
            System.exit(0);
        }
    }

}
