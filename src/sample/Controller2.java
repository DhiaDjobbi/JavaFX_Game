package sample;
import java.io.*;
import java.lang.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Files;


public class Controller2 {
    boolean exist;
    String desktop = System.getProperty("user.home") + "\\Desktop";

    public void play_sound(String sound_path) {
        AudioClip voice = new AudioClip(this.getClass().getResource(sound_path).toString());
        voice.play();
    }

    public void add_animal(String ch){
        try (FileWriter f = new FileWriter("src\\sample\\Animals.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {

             p.println(ch);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public void copy(String a, String b, int i) {
        File originalFile = new File(a);
        File newFile = new File(b);
        try {
            Files.copy(originalFile.toPath(), newFile.toPath());
            exist=false;

        } catch (Exception e) {
            String error = e.toString().substring(0, 33);

            if (error.equals("java.nio.file.NoSuchFileException")) {
                if (i == 1) {
                    error("Directory Error", "Error: Cannot find the voices folders");
                } else {
                    error("Directory Error", "Error: Cannot find the pics folders");
                }
            } else if (e.toString().substring(0, 40).equals("java.nio.file.FileAlreadyExistsException")) {
                error("Duplicate Error", "Error: Animal Already Exist in the game");
                name.clear();
                list2.getItems().clear();
                list1.getItems().clear();
                exist=true;
            }
        }
    }


    public void error(String title, String message) {
        play_sound("lib\\error.mp3");
        Stage window = new Stage();
        GridPane grid = new GridPane();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 90);
        scene.getStylesheets().add("sample/CSS/error.css");

        window.setScene(scene);
        window.showAndWait();
    }

    public void success(String title, String message) {
        play_sound("lib\\success.mp3");
        Stage window = new Stage();
        GridPane grid = new GridPane();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 90);
        scene.getStylesheets().add("sample/CSS/error.css");
        window.setScene(scene);
        window.showAndWait();
    }

    @FXML
    private Button Btn1;
    @FXML
    private ListView list1;
    String p1;
    String ex1;

    public void Btn1Action(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(desktop));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3", "*.wav"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            list1.getItems().add(selectedFile.getName());
            p1 = selectedFile.getAbsolutePath();
            ex1 = getFileExtension(selectedFile);
        }
    }

    @FXML
    private Button Btn2;
    @FXML
    private ListView list2;
    String p2;
    String ex2;

    public void Btn2Action(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(desktop));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            list2.getItems().add(selectedFile.getName());
            p2 = selectedFile.getAbsolutePath();
            ex2 = getFileExtension(selectedFile);
        }
    }

    @FXML
    private TextField name;
    @FXML
    private Button Btn3;
    public void Btn3Action(ActionEvent event) throws IOException {
        if (name.getText().isEmpty()) {
            error("Input Error", "Please enter the animal name");
            return;
        }
        if (list1.getItems().isEmpty()) {
            error("File Upload Error", "Please upload the animal sound");
            return;
        }
        if (list2.getItems().isEmpty()) {
            error("File Upload Error", "Please upload the animal picture");
            return;
        } else {
            String path1 = p1.replace("\\", "\\\\");
            String path2 = p2.replace("\\", "\\\\");
            String to1 = "src\\sample\\voices\\" + name.getText() + ex1;
            String to2 = "src\\sample\\pics\\" + name.getText() + ex2;
            exist=false;
            copy(path1, to1, 1);
            copy(path2, to2, 2);
            if (exist == false){
                String line = name.getText() + "#" + name.getText() + ex1 + "#" + name.getText() + ex2;
                add_animal(line);
                success("sucess", "animal is succesfully added");
            }
        }
    }
}