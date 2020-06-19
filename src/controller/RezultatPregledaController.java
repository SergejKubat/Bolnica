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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Dijagnoza;
import model.Doktor;
import model.Pregled;
import model.Recept;
import utils.Session;

public class RezultatPregledaController implements Initializable {

    @FXML
    private Button izlaz;

    @FXML
    private TableView<Dijagnoza> dijagnozeTabela;

    @FXML
    private TableColumn<Dijagnoza, String> bolestColumn;

    @FXML
    private TableColumn<Dijagnoza, String> opisDijagnozeColumn;

    @FXML
    private Label prepisaniLekoviText;

    @FXML
    private Label prepisaniLekovi;

    private Doktor doktor;

    private Pregled izabraniPregled;

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
        int pregledId = Integer.parseInt(sesija.getAttribute("izabraniPregled"));
        doktor = DBHelper.selectDoktor(doktorId);
        izabraniPregled = DBHelper.selectPregled(pregledId);

        if (doktor != null) {

            if (izabraniPregled != null) {

                bolestColumn.setCellValueFactory(new PropertyValueFactory<>("bolestId"));
                opisDijagnozeColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));

                Task<List<Dijagnoza>> selectDijagnozeTask = new Task<List<Dijagnoza>>() {
                    @Override
                    public List<Dijagnoza> call() throws Exception {
                        return DBHelper.selectAllDijagnoza(pregledId);
                    }
                };

                selectDijagnozeTask.setOnFailed(e -> {
                    selectDijagnozeTask.getException().printStackTrace();
                    System.out.println("GRESKA!");
                });

                selectDijagnozeTask.setOnSucceeded(e -> dijagnozeTabela.getItems().setAll(FXCollections.observableArrayList(selectDijagnozeTask.getValue())));

                executor.execute(selectDijagnozeTask);

            }

        } else {
            System.exit(0);
        }
    }

    @FXML
    void prikaziLekove() {
        
        Dijagnoza izabranaDijagnoza = dijagnozeTabela.getSelectionModel().getSelectedItem();

        executor = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        Task<List<Recept>> selectReceptiTask = new Task<List<Recept>>() {
            @Override
            public List<Recept> call() throws Exception {
                return DBHelper.selectAllRecept(izabranaDijagnoza.getId());
            }
        };

        selectReceptiTask.setOnFailed(e -> {
            selectReceptiTask.getException().printStackTrace();
            System.out.println("GRESKA!");
        });

        selectReceptiTask.setOnSucceeded(e -> {
            List<Recept> recepti = selectReceptiTask.getValue();
            prepisaniLekoviText.setVisible(true);
            prepisaniLekovi.setVisible(true);
            StringBuilder sb = new StringBuilder();
            recepti.forEach((recept) -> {
                sb.append(recept.getLekId().getNaziv()).append(", ");
            });
            prepisaniLekovi.setText(sb.delete(sb.length() - 2, sb.length() - 1).toString());
        });
        
        executor.execute(selectReceptiTask);

    }

    @FXML
    void zatvori() {
        Stage stage = (Stage) izlaz.getScene().getWindow();
        stage.close();
    }

}
