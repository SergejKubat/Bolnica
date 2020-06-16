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
import model.Pregled;
import utils.Session;

public class PreglediController implements Initializable {

    @FXML
    private TableView<Pregled> tabela;

    @FXML
    private TableColumn<Pregled, String> pacijentColumn;

    @FXML
    private TableColumn<Pregled, String> datumColumn;

    @FXML
    private TableColumn<Pregled, String> vremeColumn;

    @FXML
    private TableColumn<Pregled, String> dijagnozaColumn;

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

            pacijentColumn.setCellValueFactory(new PropertyValueFactory<>("pacijentId"));
            datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
            vremeColumn.setCellValueFactory(new PropertyValueFactory<>("vreme"));
            dijagnozaColumn.setCellValueFactory(new PropertyValueFactory<>("dijagnozaId"));
            
            Task<List<Pregled>> selectAllPregledTask = new Task<List<Pregled>>() {
                @Override
                public List<Pregled> call() throws Exception {
                    return DBHelper.selectAllPregled(id);
                }
            };

            selectAllPregledTask.setOnFailed(e -> {
                selectAllPregledTask.getException().printStackTrace();
                System.out.println("GRESKA!");
            });

            selectAllPregledTask.setOnSucceeded(e -> tabela.getItems().setAll(FXCollections.observableArrayList(selectAllPregledTask.getValue())));

            executor.execute(selectAllPregledTask);

        } else {
            System.exit(0);
        }

    }

}
