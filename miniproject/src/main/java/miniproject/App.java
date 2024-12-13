package miniproject;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            stage.setTitle("Weather Data");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            //test commit
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}