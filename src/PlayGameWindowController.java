package com.example.game;

import javafx.animation.*;
import javafx.application.Platform;
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
import java.util.*;
import java.util.concurrent.TimeUnit;


public class PlayGameWindowController extends TimerTask implements Initializable {
    private int position;
    private Stage stage;
    private Scene scene;
    private User user;
    private WeaponChest chest1;
    private Weapon sword;
    private Weapon hammer;
    private int[] abyss;
    private boolean gotChest;
    private Orc redOrc;
    private Orc greenOrc;
    private Boss greenBoss;


    private void Animate(Node myNode,Duration duration,int type,double value,boolean reverse){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(myNode);
        translateTransition.setDuration(duration);
        if (type==1)
            translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setByY(value);
        translateTransition.setAutoReverse(reverse);
        translateTransition.play();
    }
    @FXML
    private Button btn;
    @FXML
    private ImageView island15;
    @FXML
    private Label coins;
    @FXML
    private Label coordinate;
    @FXML
    private Label lose;
    @FXML
    private Label win;
    @FXML
    private ImageView coinChest;
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
    private ImageView openCoinChest;
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
    private ImageView myImage1;
    @FXML
    private ImageView orc;
    @FXML
    private ImageView island5;
    @FXML
    private Group myGroup;
    @FXML
    private ImageView bossOrc;
    @FXML
    private ImageView openChest;
    @FXML
    private ImageView treasureChest1;
    @FXML
    private ImageView island;

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

    private void rotateTransition(Double time,Node node,double angle ){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time),node);
        rotateTransition.setByAngle(angle);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        position=0;
        lose.setVisible(false);
        win.setText("");
        abyss = new int[]{-315,-290,-165,-140,-15,110,135,160,360,610,635,785,810,935,960,1085,1110,1260,1285,1410,1435,1760,1785,1910,1935,2060,2085,2110,2135};
        user=new User("",0);
        redOrc = new Orc(40,140,50,70,700);
        greenOrc = new Orc(100,200,1520,50,800);
        greenBoss = new Boss(200,100,2250,30,500);
        mySword.setVisible(false);myHammer.setVisible(false);
        takeInput();
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        takeChest(treasureChest1);takeChest(treasureChest2);takeChest(treasureChest3);takeChest(coinChest);
                        try {
                            explodeTNT(TNT1);explodeTNT(TNT3);explodeTNT(TNT2);
                            jumpIslands();//fightOrc();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 0, 150);
        Animate(heroGroup,Duration.millis(600),1,-60,true);
        Animate(myImage1,Duration.millis(700),1,-70,true);
        Animate(island5,Duration.millis(2000),1,-15,true);
        Animate(island,Duration.millis(2000),1,-15,true);
        Animate(orc,Duration.millis(800),1,-50,true);
        Animate(island15,Duration.millis(2000),1,-10,true);
        Animate(bossOrc,Duration.millis(500),1,-30,true);
    }
    public void fadeAnimation(Node node,Duration duration,double from,double to){
        FadeTransition fade=new FadeTransition(duration, node);
        fade.setFromValue(from);
        fade.setToValue(to);
        fade.play();
    }
    public void takeInput(){
        fadeAnimation(myGroup,Duration.millis(3000),1.0,0.36);
        Animate(textGroup,Duration.millis(50),0,175,true);
    }
    public void getUserName(){
        user.setName(textField.getText());
        Animate(textGroup,Duration.millis(650),0,-175,true);
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
        Button reloadGame = new Button("Reload Game");
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
        button1.setTextAlignment(TextAlignment.RIGHT);
        reloadGame.setLayoutX(55); reloadGame.setLayoutY(50);
        reloadGame.setOnAction(event -> {
            try {
                deSerialise(event);
                System.out.println("GAME LOADED");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        CheckBox checkBox = new CheckBox("Music");
        checkBox.setLayoutX(55); checkBox.setLayoutY(90);

        anchorPane.getChildren().addAll(button1, checkBox,reloadGame);

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
        ObjectOutputStream outUser=null;
        try{
            outUser=new ObjectOutputStream(new FileOutputStream("C:\\Users\\shubh\\IdeaProjects\\Game\\src\\main\\java\\com\\example\\game\\savedUsers.txt"));
            outUser.writeObject(this.user);
        }
        catch(Exception e){
            System.out.println("Error were Encountered while Saving User");
        }
        finally {
            if (outUser!=null){
                outUser.close();
            }
        }
        serializeOrcs();
    }
    public void serializeOrcs() throws IOException{
        ObjectOutputStream outOrc = null;
        try{
            outOrc=new ObjectOutputStream(new FileOutputStream("C:\\Users\\shubh\\IdeaProjects\\Game\\src\\main\\java\\com\\example\\game\\savedOrcs.txt"));
            outOrc.writeObject(this.redOrc);
            outOrc.writeObject(this.greenOrc);
            outOrc.writeObject(this.greenBoss);
        }
        catch (Exception e) {
            System.out.println("Error were Encountered while Saving Orc");
        }
        finally {
            if (outOrc!=null){
                outOrc.close();
            }
        }
    }
    public void deSerialise(ActionEvent event) throws Exception{
        ObjectInputStream in = null;
        ObjectInputStream inOrc = null;
        User myUser = this.user;
        /*Orc rOrc = this.redOrc;
        Orc gOrc = this.greenOrc;
        Orc gBoss = this.greenBoss;*/
        try{
            in = new ObjectInputStream(new FileInputStream("C:\\Users\\shubh\\IdeaProjects\\Game\\src\\main\\java\\com\\example\\game\\savedUsers.txt"));
            this.user = (User)in.readObject();
            inOrc = new ObjectInputStream(new FileInputStream("C:\\Users\\shubh\\IdeaProjects\\Game\\src\\main\\java\\com\\example\\game\\savedOrcs.txt"));
            this.redOrc = (Orc)inOrc.readObject();
            this.greenOrc = (Orc)inOrc.readObject();
            this.greenBoss = (Boss)inOrc.readObject();
        }
        catch(Exception e){
            System.out.println("Errors were Encountered while Loading Game");
            endGame(event);
        }
        finally {
            if (in!=null){
                in.close();
            }
            Hero hero = user.getMyHero();
            this.user.setMyHero(myUser.getMyHero());

            setUpLoadedGame(hero);
        }
        System.out.println("user Name= "+ user.getName());
    }
    private synchronized void setUpLoadedGame(Hero hero){
        fadeAnimation(myGroup,Duration.millis(3000),0.36,1.0);
        Animate(textGroup,Duration.millis(50),0,-175,true);
        user.getMyHero().setXCoordinates(user.getMyHero().getXCoordinates()+25*user.getScore());
        user.getMyHero().setMyWeapon(hero.getMyWeapon());
        if (user.getCoinsEarned() > 0)
            coins.setText(String.valueOf(user.getCoinsEarned()));
        if (hero.getWeapons()[0] != null){
            hammerIcon.setOpacity(1.0);
            user.getMyHero().setWeapons(0,hero.getWeapons()[0]);
            hammerLevel.setText(String.valueOf(user.getMyHero().getWeapons()[0].getLevel()));
        }
        if (hero.getWeapons()[1]!=null){
            swordIcon.setOpacity(1.0);
            user.getMyHero().setWeapons(1,hero.getWeapons()[1]);
            swordLevel.setText(String.valueOf(user.getMyHero().getWeapons()[1].getLevel()));
        }
        if (hero.getMyWeapon() != null){
            if (hero.getMyWeapon().getWeaponType() == 0) {
                myHammer.setVisible(true);
            }
            else if (hero.getMyWeapon().getWeaponType() == 1) {
                mySword.setVisible(true);
            }
        }
        myImage1.setTranslateX(redOrc.getXCoordinates()-redOrc.getBaseX());
        orc.setTranslateX(greenOrc.getXCoordinates()-greenOrc.getBaseX());
        bossOrc.setTranslateX(greenBoss.getXCoordinates()-greenBoss.getBaseX());
        myGroup.setTranslateX(-user.getScore() * 25);
        coordinate.setText(String.valueOf(user.getScore()));
        position = user.getScore() * 25;
    }
    public void moveHero() throws InterruptedException {
        position+=25;
        user.setScore(user.getScore()+1);
        coordinate.setText(String.valueOf(user.getScore()));
        coins.setText(String.valueOf(user.getCoinsEarned()));
        myGroup.setTranslateX(-position);
        gotChest = true;
        user.getMyHero().setXCoordinates(user.getMyHero().getXCoordinates()+25);
        if (user.getMyHero().getWeapons()[0]!=null)
            hammerLevel.setText(String.valueOf(user.getMyHero().getWeapons()[0].getLevel()));
        else
            hammerLevel.setText("0");
        if (user.getMyHero().getWeapons()[1]!=null)
            swordLevel.setText(String.valueOf(user.getMyHero().getWeapons()[1].getLevel()));
        else
            swordLevel.setText("0");
        fightOrc();
        System.out.println("Orc"+redOrc.getXCoordinates()+" "+redOrc.getYCoordinates());
        System.out.println("Hero:"+user.getMyHero().getXCoordinates()+" "+user.getMyHero().getYCoordinates());
        System.out.println("TChest2: "+ treasureChest2.getLayoutX()+ " "+ treasureChest2.getLayoutY());
    }
    public boolean isCollide(int coordinate1,int coordinate2,int range){
        return coordinate1 <= coordinate2 + range && coordinate1 >= coordinate2 - range;
    }
    public synchronized void takeChest(ImageView chest) {
        if (isCollide(user.getMyHero().getXCoordinates(), (int) chest.getLayoutX(), 12) && gotChest && isCollide(user.getMyHero().getYCoordinates(), (int) chest.getLayoutY(), 20)) {                          // event Handler for taking chest
            gotChest = false;
            if (chest == treasureChest1)
                openChest.setLayoutX(-100);
            if (chest == treasureChest2)
                openChest.setLayoutX(695);
            if (chest == treasureChest3)
                openChest.setLayoutX(1665);
            if (chest == coinChest) {
                openCoinChest.setLayoutX(1020);
                openCoinChest.setLayoutY(40);
                user.setCoinsEarned(user.getCoinsEarned() + 30);
                return;
            }
            chest1 = new WeaponChest();
            if (chest1.getWeaponType() == 0) {
                myHammer.setVisible(true);
                mySword.setVisible(false);
                if (hammer == null) {
                    hammer = chest1.getWeapon();
                    fadeAnimation(hammerIcon, Duration.millis(20), 0.36, 1.0);
                    chest1.equipHero(user.getMyHero());
                } else {
                    user.getMyHero().setMyWeapon(hammer);
                }
                user.getMyHero().addWeapons(hammer,chest1);
            }
            if (chest1.getWeaponType() == 1) {
                mySword.setVisible(true);
                myHammer.setVisible(false);
                if (sword == null) {
                    sword = chest1.getWeapon();
                    fadeAnimation(swordIcon, Duration.millis(20), 0.36, 1.0);
                    chest1.equipHero(user.getMyHero());
                } else {
                    user.getMyHero().setMyWeapon(sword);
                }
                user.getMyHero().addWeapons(sword,chest1);
                swordLevel.setText(String.valueOf(user.getMyHero().getWeapons()[1].getLevel()));
            }
        }
    }
    public synchronized void explodeTNT(ImageView tnt) throws InterruptedException {
        if (isCollide(user.getMyHero().getXCoordinates(),(int) tnt.getLayoutX(),15) && isCollide(user.getMyHero().getYCoordinates(), (int) tnt.getLayoutY(),20)){
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
            resurrectHero();
        }
    }
    public synchronized void jumpIslands() throws InterruptedException {
        if (checkAbyss(user.getMyHero().getXCoordinates()) && user.getMyHero().isRet() && user.getMyHero().getYCoordinates()<=50 && user.getMyHero().isAlive()){
            Platform.runLater(() ->{
                Animate(heroGroup,Duration.millis(350),0,650,false);
                fadeAnimation(heroGroup,Duration.millis(275),1.0,0);
            });
            user.getMyHero().setAlive(false);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            resurrectHero();
        }
    }
    public boolean checkAbyss(int coordinate){
        int i=0;
        int j=abyss.length;
        while(i<j){
            int mid=(i+j)/2;
            if (coordinate > abyss[mid]){
                i=mid+1;
            }
            else if (coordinate < abyss[mid])
                j=mid;
            else
                return true;
        }
        return false;
    }
    public void fightOrc() throws InterruptedException {
        redOrc.fightHero(user.getMyHero(),13);
        greenOrc.fightHero(user.getMyHero(),13);
        greenBoss.fightHero(user.getMyHero(),15);
        if ( !user.getMyHero().isAlive()) {
            resurrectHero();
            return;
        }
        if (user.getMyHero().fightingWith == redOrc){
            rotateTransition(500.0,myHammer,180);
            rotateTransition(500.0,mySword,180);
            if (user.getMyHero().getMyWeapon()!=null)
                myImage1.setTranslateX(25*((redOrc.getMaxHealth()-redOrc.getHitPoints())/user.getMyHero().getMyWeapon().getDamagePerHit()));
            else
                myImage1.setTranslateX(25*((redOrc.getMaxHealth()-redOrc.getHitPoints())/20));
            if (!redOrc.isAlive){
                Animate(myImage1,Duration.millis(300),0,650,false);
                myImage1.setLayoutY(1500);
                redOrc.setXCoordinate(-540);
            }
        }
        if (user.getMyHero().fightingWith == greenOrc){
            rotateTransition(500.0,myHammer,90);
            rotateTransition(500.0,mySword,150);
            if (user.getMyHero().getMyWeapon()!=null)
                orc.setTranslateX(25*((greenOrc.getMaxHealth()-greenOrc.getHitPoints())/user.getMyHero().getMyWeapon().getDamagePerHit()));
            else
                orc.setTranslateX(25*((greenOrc.getMaxHealth()-greenOrc.getHitPoints())/20));
            if (!greenOrc.isAlive){
                Animate(orc,Duration.millis(300),0,650,false);
                orc.setLayoutY(1500);
                greenOrc.setXCoordinate(-540);
            }
        }
        if (user.getMyHero().fightingWith == greenBoss){
            rotateTransition(500.0,myHammer,90);
            rotateTransition(500.0,mySword,150);
            if (user.getMyHero().getMyWeapon()!=null)
                bossOrc.setTranslateX(25*((greenBoss.getMaxHealth()-greenBoss.getHitPoints())/user.getMyHero().getMyWeapon().getDamagePerHit()));
            else
                bossOrc.setTranslateX(25*((greenBoss.getMaxHealth()-greenBoss.getHitPoints())/20));
            if (!greenBoss.isBossAlive()){
                Animate(bossOrc,Duration.millis(300),0,650,false);
                bossOrc.setLayoutY(1500);
                greenBoss.setXCoordinate(-540);
                win.setText("YOU WIN");
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            checkWinner(actionEvent);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
    public void checkWinner(ActionEvent actionEvent) throws InterruptedException {
        Thread.sleep(1500);
        endGame(actionEvent);
    }
    public void resurrectHero() throws InterruptedException {
        if ((user.getCoinsEarned()<0 || ! user.getMyHero().isResurrectAvailable()) /*&& ! user.getMyHero().isAlive()*/) {
            lose.setVisible(true);
            btn.setOnAction(new EventHandler<>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    endGame(actionEvent);
                }
            });
            return;
        }
        if (user.getMyHero().isResurrectAvailable()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save Hero");
            alert.setContentText("Do you want to save the Hero ?");
            user.getMyHero().setResurrectAvailable(false);
            try {
                if (alert.showAndWait().get() == ButtonType.OK) {
                    user.setCoinsEarned(user.getCoinsEarned() - 5);
                    user.getMyHero().setAlive(true);
                    int i = 0;
                    while (i < 5) {
                        if (!checkAbyss(user.getMyHero().getXCoordinates() + 25 * i)) {
                            user.getMyHero().setXCoordinates(user.getMyHero().getXCoordinates() + 25 * i);
                            fadeAnimation(heroGroup, Duration.millis(200), 0, 1.0);
                            position += 25 * i;
                            user.setScore(user.getScore()+i);
                            myGroup.setTranslateX(-position);
                            break;
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void endGame(ActionEvent actionEvent){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("HighScore.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        assert root != null;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void run() {
    }
}
