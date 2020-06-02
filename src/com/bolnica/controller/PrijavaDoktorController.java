package com.bolnica.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PrijavaDoktorController implements Initializable {
    
    @FXML
    private Pane contentArea;
    
    @FXML
    private TextField emailInput;

    @FXML
    private TextField lozinkaInput;

    @FXML
    private Label zabLozinkaText;

    @FXML
    private Button prijavaBtn;

    @FXML
    private Label prijavaAdminText;

    @FXML
    void open_prijava_admin(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("PrijavaAdministrator.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_zab_lozinka(MouseEvent event) {

    }

    @FXML
    void prijavi_se(MouseEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
