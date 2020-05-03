package com.bolnica.controller;

import com.bolnica.database.DatabaseHelper;
import java.net.URL;
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
        Platform.runLater(new Runnable() {
            @Override public void run() {
                DatabaseHelper.insertGrad("Subotica");
                System.out.println("Uspesno ste uneli podatke u bazu.");
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
