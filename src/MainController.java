package com.example.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void playGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playGameWindow.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void highScore(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HighScore.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadGame(ActionEvent event) throws IOException{
        System.out.println("GAME LOADED");
    }

    public void credits(ActionEvent event) throws IOException{
        System.out.println("Developed by TUSHAR");
    }

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane scenePane;

    public void exit(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Game");
//        alert.setHeaderText("Quit game");
        alert.setContentText("Do you want to quit game?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            System.out.println("Game closed.");
            stage.close();
        }
    }
}
