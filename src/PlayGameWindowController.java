package com.example.game;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.css.converter.FontConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private int position;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user;

    private void Animate(Node myNode,Duration duration,int type,double value){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(myNode);
        translateTransition.setDuration(duration);
        if (type==1)
            translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setByY(value);
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private ImageView island1;

    @FXML
    private Label coordinate;

    public void back(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setContentText("Do you want to go to main menu?");
        try {
            if (alert.showAndWait().get() == ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private Group textGroup;
    @FXML
    private TextField textField;
    @FXML
    private ImageView myImage;
    @FXML
    private ImageView myImage1;
    @FXML
    private ImageView orc;
    @FXML
    private ImageView island;
    @FXML
    private Group myGroup;
    @FXML
    private Group myGroup2;
    @FXML
    private Group myGroup3;
    @FXML
    private ImageView bossOrc;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        position=0;
        user=new User("",0);
        takeInput();
        Animate(myImage,Duration.millis(600),1,-60);
        Animate(myImage1,Duration.millis(650),1,-70);
        Animate(island,Duration.millis(2000),1,-15);
        Animate(myGroup3,Duration.millis(2000),1,-15);
        Animate(orc,Duration.millis(800),1,-50);
        Animate(island1,Duration.millis(2000),1,-10);
        Animate(bossOrc,Duration.millis(500),1,-30);
    }
    public void fadeAnimation(Node node,Duration duration,double from,double to){
        FadeTransition fade=new FadeTransition(duration, node);
        fade.setFromValue(from);
        fade.setToValue(to);
        fade.play();
    }
    public void takeInput(){
        fadeAnimation(myGroup,Duration.millis(3000),1.0,0.36);
        fadeAnimation(myGroup2,Duration.millis(3000),1.0,0.36);
        Animate(textGroup,Duration.millis(50),0,175);
    }
    public void getUserName(){
        user.setName(textField.getText());
        Animate(textGroup,Duration.millis(650),0,-175);
        fadeAnimation(myGroup,Duration.millis(3000),0.36,1.0);
        fadeAnimation(myGroup2,Duration.millis(3000),0.36,1.0);
        System.out.println(user.getName());
    }

    public void pressSettingButton() throws IOException {
        Stage stage = new Stage();
        AnchorPane anchorPane = new AnchorPane();
        stage.setTitle("Settings");
        stage.setHeight(200);
        stage.setWidth(200);
        Button button1 = new Button("Save Game");
        button1.setPrefWidth(80);
        button1.setTextAlignment(TextAlignment.LEFT);
        button1.setLayoutX(0); button1.setLayoutY(5);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Game saved");
            }
        });

        CheckBox checkBox = new CheckBox("Music");
        checkBox.setLayoutX(55); checkBox.setLayoutY(90);

        anchorPane.getChildren().addAll(button1, checkBox);

        Image image = new Image("file:///C:/Users/shubh/IdeaProjects/Game/src/main/resources/com/example/game/Photos/1.0.png");

        Scene scene = new Scene(anchorPane);
        stage.setWidth(200);
        stage.setHeight(300);
        stage.setResizable(false);
        stage.getIcons().setAll(image);
        stage.setScene(scene);
        stage.show();
    }

    public void moveHero(ActionEvent event) throws IOException{
        position+=25;
        user.setScore(user.getScore()+1);
        coordinate.setText(String.valueOf(user.getScore()));
        myGroup.setTranslateX(-position);
        myGroup2.setTranslateX(-position);
    }
}
