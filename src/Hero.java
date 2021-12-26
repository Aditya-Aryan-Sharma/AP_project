package com.example.game;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Hero extends TimerTask implements Serializable{
    private Weapon myWeapon;
    private int[][] coordinates;
    private boolean ret;
    private final int baseLevel;
    private boolean isAlive;
    public Hero(){
        ret = false;
        coordinates = new int[1][2];
        isAlive = true;
        coordinates[0][0]=-540;
        coordinates[0][1]=45;              //Hardcoded values are from Scene Builder
        Timer timer = new Timer(true);
        TimerTask timerTask= this;
        baseLevel = coordinates[0][1];
        timer.schedule(timerTask,0,150);
    }

    @Override
    public synchronized void run() {
        int y=getYCoordinates();
        if (y == baseLevel+60)
            ret = true;
        if (y == baseLevel)
            ret = false;
        if(y <= baseLevel+60 && y >= baseLevel){
            if (ret){
                this.setYCoordinates(getYCoordinates()-15);
            }
            else{
                this.setYCoordinates(getYCoordinates()+15);
            }
        }
    }
    public void killOrc(Orc enemy){
        enemy.setXCoordinate(enemy.getXCoordinates()+25);
        enemy.setHitPoints(enemy.getHitPoints()-myWeapon.getDamagePerHit());
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
    public boolean isRet() {
        return ret;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean val){
        isAlive = val;
    }
}
