package com.example.game;

import javafx.animation.TranslateTransition;
import javafx.css.converter.FontConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class PlayGameWindowController implements Initializable {

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

//    public void back(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane scenePane;

    public void back(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
//        alert.setHeaderText("Go to main menu");
        alert.setContentText("Do you want to go to main menu?");

        if(alert.showAndWait().get() == ButtonType.OK){
//            stage = (Stage) scenePane.getScene().getWindow();
//            System.out.println("Game closed.");
//            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private ImageView myImage;

    @FXML
    private ImageView myImage1;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        TranslateTransition translateTransition = new TranslateTransition();
        TranslateTransition translateTransition1 = new TranslateTransition();
        translateTransition.setNode(myImage);
        translateTransition1.setNode(myImage1);
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition1.setDuration(Duration.millis(1000));
//        translateTransition.setByY(150);
        translateTransition.setByY(-80);
//        translateTransition.setFromY(-150);
//        translateTransition1.setByY(150);
        translateTransition1.setByY(-80);
//        translateTransition1.setFromY(-150);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition1.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition1.setAutoReverse(true);
        translateTransition.play();
        translateTransition1.play();
    }

    public void pressSettingButton(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("settingPressPage.fxml"));
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();

        Button button1 = new Button("Save Game");
        button1.setPrefWidth(80);
        button1.setTextAlignment(TextAlignment.CENTER);
        button1.setLayoutX(55); button1.setLayoutY(50);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Game saved");
            }
        });

        CheckBox checkBox = new CheckBox("Music");
        checkBox.setLayoutX(55); checkBox.setLayoutY(90);

        anchorPane.getChildren().addAll(button1, checkBox);

        Image image = new Image("file:///D:/trial/1.0.jpg");

        Scene scene = new Scene(anchorPane);
        stage.setWidth(200);
        stage.setHeight(300);
        stage.setResizable(false);
        stage.getIcons().setAll(image);
        stage.setScene(scene);
        stage.show();
    }

    public void moveHero(ActionEvent event) throws IOException{

    }
}