package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static Stage stage = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/PrijavaDoktor.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        Main.stage = stage;
        MetaData.parent = root;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}