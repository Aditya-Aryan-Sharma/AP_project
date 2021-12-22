package com.example.game;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class PlayGameWindowController implements Initializable {
    private int position;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private User user;
    private boolean loadedGame=false;
    private WeaponChest chest1;
    private Weapon sword;
    private Weapon hammer;

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
    private ImageView island1;
    @FXML
    private Label coins;

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
    private ImageView TNT1;
    @FXML
    private ImageView TNT2;
    @FXML
    private ImageView TNT3;
    @FXML
    private ImageView treasureChest3;
    @FXML
    private ImageView treasureChest2;
    @FXML
    private Group heroGroup;
    @FXML
    private ImageView mySword;
    @FXML
    private ImageView myHammer;
    @FXML
    private Label swordLevel;
    @FXML
    private Label hammerLevel;
    @FXML
    private ImageView explosion;
    @FXML
    private ImageView hammerIcon;
    @ FXML
    private ImageView swordIcon;
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
    private Group myGroup3;
    @FXML
    private ImageView bossOrc;
    @FXML
    private ImageView openChest;
    @FXML
    private ImageView treasureChest1;


    public void setLoadedGame(Boolean val){
        this.loadedGame=val;
    }
    public boolean isLoadedGame() {
        return loadedGame;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        position=0;
        user=new User("",0);
        mySword.setVisible(false);myHammer.setVisible(false);
        takeInput();
        if (isLoadedGame()){
            try {
                deSerialise();
                System.out.println("GAME LOADED");
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                myGroup.setTranslateX(-user.getScore() * 25);
                coordinate.setText(String.valueOf(user.getScore()));
                coins.setText(String.valueOf(user.getCoinsEarned()));
            }
        }
        Animate(heroGroup,Duration.millis(600),1,-60);
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
        Animate(textGroup,Duration.millis(50),0,175);
    }
    public void getUserName(){
        user.setName(textField.getText());
        Animate(textGroup,Duration.millis(650),0,-175);
        fadeAnimation(myGroup,Duration.millis(2500),0.36,1.0);
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
                try {
                    serialize();
                    System.out.println("Game saved");
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
    public void serialize() throws IOException{
        ObjectOutputStream out=null;
        try{
            out=new ObjectOutputStream(new FileOutputStream("C:\\Users\\shubh\\IdeaProjects\\Game\\src\\main\\java\\com\\example\\game\\savedGame.txt"));
            out.writeObject(this.user);
        }
        catch(Exception e){
            System.out.println("Error were Encountered while Saving Game");
        }
        finally {
            if (out!=null){
                out.close();
            }
        }
    }

    public void deSerialise() throws IOException,ClassNotFoundException{
        ObjectInputStream in = null;
        User myUser=new User("",0);
        try{
            in = new ObjectInputStream(new FileInputStream("C:\\Users\\shubh\\IdeaProjects\\Game\\src\\main\\java\\com\\example\\game\\savedGame.txt"));
            myUser = (User)in.readObject();
        }
        catch(Exception e){
            System.out.println("Error were Encountered while Loading Game");
        }
        finally {
            if (in!=null){
                in.close();
            }
            this.user=myUser;
        }
    }

    public void moveHero(ActionEvent event) throws IOException{
        position+=25;
        user.setScore(user.getScore()+1);
        coordinate.setText(String.valueOf(user.getScore()));
        coins.setText(String.valueOf(user.getCoinsEarned()));
        myGroup.setTranslateX(-position);
        user.getMyHero().setXCoordinates(user.getMyHero().getXCoordinates()+25);
        System.out.println(treasureChest1.getLayoutX()+" "+treasureChest1.getLayoutY());
        System.out.println(user.getMyHero().getXCoordinates()+" "+user.getMyHero().getYCoordinates());
        takeChest(treasureChest1);takeChest(treasureChest2);takeChest(treasureChest3);
        explodeTNT(TNT1);explodeTNT(TNT2);explodeTNT(TNT3);
    }
    public boolean isCollide(int coordinate1,int coordinate2){
        return coordinate1 <= coordinate2 + 15 && coordinate1 >= coordinate2 - 15;
    }
    public void takeChest(ImageView chest)  {
        if (isCollide(user.getMyHero().getXCoordinates(),(int) chest.getLayoutX()) && isCollide(user.getMyHero().getYCoordinates(), (int) chest.getLayoutY())) {                          // event Handler for taking chest
            if (chest == treasureChest1)
                openChest.setLayoutX(-100);
            if (chest == treasureChest2)
                openChest.setLayoutX(695);
            if (chest == treasureChest3)
                openChest.setLayoutX(1670);
            chest1 = new WeaponChest();
            if (chest1.getWeaponType() == 0) {
                myHammer.setVisible(true);
                mySword.setVisible(false);
                if (hammer == null) {
                    hammer = chest1.getWeapon();
                    fadeAnimation(hammerIcon, Duration.millis(20), 0.36, 1.0);
                } else {
                    hammer.setDamagePerHit(hammer.getDamagePerHit() + 20);
                    hammer.setLevel(hammer.getLevel() + 1);
                }
                hammerLevel.setText(String.valueOf(hammer.getLevel()));
            }
            if (chest1.getWeaponType() == 1) {
                mySword.setVisible(true);
                myHammer.setVisible(false);
                if (sword == null) {
                    sword = chest1.getWeapon();
                    fadeAnimation(swordIcon, Duration.millis(20), 0.36, 1.0);
                } else {
                    sword.setDamagePerHit(sword.getDamagePerHit() + 20);
                    sword.setLevel(sword.getLevel() + 1);
                }
                swordLevel.setText(String.valueOf(sword.getLevel()));
            }
        }
    }
    public void explodeTNT(ImageView tnt){
        if (isCollide(user.getMyHero().getXCoordinates(),(int) tnt.getLayoutX()) && isCollide(user.getMyHero().getYCoordinates(), (int) tnt.getLayoutY())){
            int i = 0;
            while(i<2) {
                fadeAnimation(tnt, Duration.millis(400), 1.0, 0.2);
                fadeAnimation(tnt, Duration.millis(400), 0.2, 1.0);
                i++;
            }
            tnt.setVisible(false);
            if (tnt==TNT1)
                explosion.setLayoutX(-240);
            if (tnt==TNT2)
                explosion.setLayoutX(210);
            if (tnt==TNT3)
                explosion.setLayoutX(310);
            fadeAnimation(explosion,Duration.millis(3500),1.0,0);
        }
    }
}
