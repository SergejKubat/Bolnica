package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.Session;

public class Main extends Application {

    public static Stage stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        
        Session sesija = Session.getInstance();
        
        String putanja = sesija.getAttribute("id") != null ? "/view/PocetnaDoktor.fxml" : "/view/PrijavaDoktor.fxml";
        
        Parent root = FXMLLoader.load(getClass().getResource(putanja));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Prijava");
        stage.getIcons().add(new Image("file:img/logo.png"));
        stage.setResizable(false);
        stage.sizeToScene();
        Main.stage = stage;
        MetaData.parent = root;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
