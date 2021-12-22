package com.example.game;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Hero extends TimerTask implements Serializable{
    private Weapon myWeapon;
    private int[][] coordinates;
    boolean ret;
    public Hero(){
        ret = false;
        coordinates = new int[1][2];
        coordinates[0][0]=-540;
        coordinates[0][1]=45;              //Hardcoded values are from Scene Builder
        Timer timer = new Timer(true);
        TimerTask timerTask= this;
        timer.schedule(timerTask,0,150);
    }

    @Override
    public synchronized void run() {
        int y=getYCoordinates();
        if (y==105)
            ret = true;
        if (y==45)
            ret = false;
        if(y <= 105 && y >= 45){
            if (ret){
                this.setYCoordinates(getYCoordinates()-15);
            }
            else{
                this.setYCoordinates(getYCoordinates()+15);
            }
        }
    }

    public Weapon getMyWeapon() {
        return myWeapon;
    }
    public void setMyWeapon(Weapon myWeapon) {
        this.myWeapon = myWeapon;
    }
    public void setXCoordinates(int coordinates) {
        this.coordinates[0][0] = coordinates;
    }
    public int getXCoordinates() {
        return coordinates[0][0];
    }
    public void setYCoordinates(int coordinates) {
        this.coordinates[0][1] = coordinates;
    }
    public int getYCoordinates() {
        return coordinates[0][1];
    }
}
