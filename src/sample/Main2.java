package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        primaryStage.setTitle("Add new animal");
        primaryStage.setScene(new Scene(root, 426, 386));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
