package com.example.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayGameController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void up(ActionEvent event){

    }

    public void down(ActionEvent event){

    }

    public void left(ActionEvent event){

    }

    public void right(ActionEvent event){

    }

    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
