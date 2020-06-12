package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception{
        TabPane big = new TabPane ();

        FlowPane root1 = new FlowPane(5, 5);
        Tab tab1 = new Tab();
        tab1.setText("-Animals-");
        tab1.setContent(root1);


        HBox root2 = new HBox(50);
        root2.setPadding(new Insets(50,50,50,50));

        Tab tab2 = new Tab();
        tab2.setText("-Quiz Test-");


        int s=0;
        String[] sounds = new String[100];
        String[] names = new String[100];
        String[] images = new String[100];
        String line2;
        try {
            BufferedReader buffered = new BufferedReader(new FileReader("src\\sample\\Animals.txt"));
            while ((line2 = buffered.readLine()) != null) {
                String[] words = line2.split("#");
                FXMLLoader loader = new FXMLLoader( getClass().getResource("sample.fxml") );
                s++;
                names[s]=words[0];
                sounds[s]=words[1];
                images[s]=words[2];
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        big.getTabs().addAll(tab1,tab2);
        big.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        primaryStage.setTitle("Animal Sound");

            for (int i=1; i<=s;i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
                Parent audioButton = loader.load();
                AudioButtonController abc = loader.getController();
                abc.setAudioLocation(sounds[i]);
                abc.setImageLocation(images[i]);
                abc.writeText(names[i]);
                root1.getChildren().add(audioButton);
            }
        Scene scene = new Scene(big, 790, 675);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("sample/CSS/styles.css");
        primaryStage.show();
    }

}