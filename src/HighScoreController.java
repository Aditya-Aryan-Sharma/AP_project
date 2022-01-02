package com.example.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HighScoreController implements Initializable,Serializable{

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, Integer> score;
    private static ObservableList<User> board = FXCollections.observableArrayList(new User("Aryan",200), new User("Sharma",100)) ;

    public void back(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Problem Encountered While loading High score scene");
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        score.setCellValueFactory(new PropertyValueFactory<User, Integer>("score"));
        tableView.setItems(board);
    }

    public void addEntry(User user) {
        board.add(user);
    }
}
