package controller;

import database.DBHelper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Dijagnoza;
import model.Doktor;
import model.Pacijent;
import model.Pregled;
import model.Recept;
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
    private TableView<Dijagnoza> dijagnozeTabela;

    @FXML
    private TableColumn<Dijagnoza, String> bolestColumn;

    @FXML
    private TableColumn<Dijagnoza, String> opisColumn;

    @FXML
    private Label dijagnozeText;

    @FXML
    private Label prepisaniLekoviText;

    @FXML
    private Label prepisaniLekovi;

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
            odrzanColumn.setCellValueFactory(cellData -> {
                boolean odrzan = cellData.getValue().isOdrzan();
                String odrzanString;
                if (odrzan) {
                    odrzanString = "Da";
                } else {
                    odrzanString = "Ne";
                }

                return new ReadOnlyStringWrapper(odrzanString);
            });

            Task<List<Pregled>> selectPreglediTask = new Task<List<Pregled>>() {
                @Override
                public List<Pregled> call() throws Exception {
                    return DBHelper.selectAllPregledByPacijent(pacijentId);
                }
            };

            selectPreglediTask.setOnFailed(e -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText(null);
                alert.setContentText("Došlo je do greške priliko učitavanaja podataka.");

                alert.showAndWait();
            });

            selectPreglediTask.setOnSucceeded(e -> preglediTabela.setItems(FXCollections.observableArrayList(selectPreglediTask.getValue())));

            executor.execute(selectPreglediTask);

        }

    }

    @FXML
    private void prikaziDijagnoze() {
        Pregled pregled = preglediTabela.getSelectionModel().getSelectedItem();

        if (pregled != null) {

            if (pregled.isOdrzan()) {
                executor = Executors.newCachedThreadPool(runnable -> {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
                });

                dijagnozeText.setVisible(true);
                dijagnozeTabela.setVisible(true);

                bolestColumn.setCellValueFactory(new PropertyValueFactory<>("bolestId"));
                opisColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));

                Task<List<Dijagnoza>> selectDijagnozeTask = new Task<List<Dijagnoza>>() {
                    @Override
                    public List<Dijagnoza> call() throws Exception {
                        return DBHelper.selectAllDijagnoza(pregled.getId());
                    }
                };

                selectDijagnozeTask.setOnFailed(e -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Došlo je do greške priliko učitavanaja podataka.");

                    alert.showAndWait();
                });

                selectDijagnozeTask.setOnSucceeded(e -> dijagnozeTabela.setItems(FXCollections.observableArrayList(selectDijagnozeTask.getValue())));

                executor.execute(selectDijagnozeTask);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorenje");
                alert.setHeaderText(null);
                alert.setContentText("Pregled nije održan.");

                alert.showAndWait();
            }

        }
    }

    @FXML
    private void prikaziLekove() {
        Dijagnoza dijagnoza = dijagnozeTabela.getSelectionModel().getSelectedItem();

        if (dijagnoza != null) {

            prepisaniLekoviText.setVisible(true);
            prepisaniLekovi.setVisible(true);

            executor = Executors.newCachedThreadPool(runnable -> {
                Thread t = new Thread(runnable);
                t.setDaemon(true);
                return t;
            });

            Task<List<Recept>> selectReceptiTask = new Task<List<Recept>>() {
                @Override
                public List<Recept> call() throws Exception {
                    return DBHelper.selectAllRecept(dijagnoza.getId());
                }
            };

            selectReceptiTask.setOnFailed(e -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText(null);
                alert.setContentText("Došlo je do greške priliko učitavanaja podataka.");

                alert.showAndWait();
            });

            selectReceptiTask.setOnSucceeded(e -> {
                List<Recept> recepti = selectReceptiTask.getValue();
                prepisaniLekoviText.setVisible(true);
                prepisaniLekovi.setVisible(true);
                StringBuilder sb = new StringBuilder();
                recepti.forEach((recept) -> {
                    sb.append(recept.getLekId().getNaziv()).append(", ");
                });
                if (sb.length() > 0) {
                    prepisaniLekovi.setText(sb.delete(sb.length() - 2, sb.length() - 1).toString());
                }
            });

            executor.execute(selectReceptiTask);

        }
    }

    @FXML
    void zatvaranjeProzora() {
        Stage stage = (Stage) izlaz.getScene().getWindow();
        stage.close();
    }

}
