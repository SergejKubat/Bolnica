package com.bolnica.controller;

import com.bolnica.database.DBHelper;
import com.bolnica.model.Pacijent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PocetnaController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        label.setText("Hello World!");
        Platform.runLater(() -> {
            List<Pacijent> pacijenti = DBHelper.selectAllPacijent(1);
            pacijenti.forEach((p) -> {
                System.out.println(p.toString());
            });
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
