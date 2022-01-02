package com.example.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException{

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.setTitle("Will Hero");
            
            Image image = new Image("file:///C:/Users/shubh/IdeaProjects/Game/src/main/resources/com/example/game/Photos/Icon.png");
            stage.getIcons().setAll(image);
            stage.setResizable(false);
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume();
                exit(stage);
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Quit game");
        alert.setContentText("Do you want to exit the game?");

        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("Game closed.");
            stage.close();
        }
    }
}
