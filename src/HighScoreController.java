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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HighScoreController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<User> userList;
    @FXML
    private TableView<User> tableView;

    public void back(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println("Problem Encountered While loading High score scene");
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        tableView=new TableView<>();
        userList=new ArrayList<>();
        TableColumn<User,String> column1=new TableColumn<>("Player Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<User,String> column2=new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));
        tableView.setItems(addEntry());
        tableView.getColumns().addAll(column1,column2);

    }
    public ObservableList<User> addEntry(){
        userList.add(new User("Michael",5000));
        ObservableList<User> users= FXCollections.observableList(userList);
        return users;
    }
}
