package com.example.game;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Hero extends TimerTask implements Serializable,floatInAir{
    private Weapon[] weapons;
    private Weapon myWeapon;
    private final int[][] coordinates;
    private boolean ret;
    private int baseLevel;
    private boolean isAlive;
    protected Orc fightingWith;
    private boolean resurrectAvailable;
    public Hero(){
        ret = false;
        coordinates = new int[1][2];
        resurrectAvailable = true;
        weapons = new Weapon[2];
        fightingWith = null;
        isAlive = true;
        coordinates[0][0]=-540;
        coordinates[0][1]=45;
        changeYCoordinate();
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
        if (myWeapon != null)
            enemy.setHitPoints(enemy.getHitPoints()-myWeapon.getDamagePerHit());
        else
            enemy.setHitPoints(enemy.getHitPoints()-20);
        fightingWith = enemy;
        if (enemy.getHitPoints()<=0)
            enemy.setAlive(false);
        if (enemy instanceof Boss && enemy.getHitPoints()<=0){
            ((Boss) enemy).setBossAlive();
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
    public boolean isRet() {
        return ret;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean val){
        isAlive = val;
    }

    @Override
    public void changeYCoordinate() {
        Timer timer = new Timer(true);
        TimerTask timerTask= this;
        baseLevel = coordinates[0][1];
        timer.schedule(timerTask,0,150);
    }
    public void addWeapons(Weapon weapon,WeaponChest chest){
        if (chest.getWeaponType() == 0){
            if (weapons[0] == null)
                weapons[0] = weapon;
            else
                weapons[0].setLevel(weapons[0].getLevel()+1);
        }
        if (chest.getWeaponType() == 1){
            if (weapons[1] == null)
                weapons[1] = weapon;
            else
                weapons[1].setLevel(weapons[1].getLevel()+1);
        }
    }
    public Weapon[] getWeapons() {
        return weapons;
    }
    public void setWeapons(int index, Weapon weapon ){
        if (index==0)
            weapons[0] = weapon;
        else
            weapons[1] = weapon;
    }
    public void setResurrectAvailable(boolean val){
        resurrectAvailable = val;
    }
    public boolean isResurrectAvailable() {
        return resurrectAvailable;
    }
}
