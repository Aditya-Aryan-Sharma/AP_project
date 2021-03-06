package com.example.game;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Orc extends TimerTask implements Serializable,floatInAir {
    private int hitPoints;
    private final int[][] coordinates;
    private final int[][] baseLevel;
    private boolean ret;
    private final int distance;
    protected boolean isAlive;
    private final int maxHealth;
    private final int period;

    public Orc(int points, int period,int xCoordinate,int distance,int duration){
        this.hitPoints = points;
        maxHealth = points;
        ret = false;
        isAlive = true;
        coordinates = new int[1][2];
        baseLevel =new int[1][2];
        coordinates[0][0] = xCoordinate;
        coordinates[0][1] = 45;
        baseLevel[0][1] = coordinates[0][1];
        baseLevel[0][0] = xCoordinate;
        this.distance = (distance/duration)*period;
        this.period = period;
        changeYCoordinate();
    }
    @Override
    public void run() {
        int y=getYCoordinates();
        if (y == baseLevel[0][1]+70)
            ret = true;
        if (y == baseLevel[0][1])
            ret = false;
        if(y <= baseLevel[0][1]+70 && y >= baseLevel[0][1]){
            if (ret){
                this.setYCoordinates(getYCoordinates() - distance);
            }
            else{
                this.setYCoordinates(getYCoordinates() + distance);
            }
        }
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void fightHero(Hero hero, int orcRange){
        if (isCollide(hero.getXCoordinates(),getXCoordinates(),orcRange)){
            if (getYCoordinates()- hero.getYCoordinates()>=0 && getYCoordinates()- hero.getYCoordinates()<10){
                hero.setAlive(false);
            }
            else if (hero.getYCoordinates()>getYCoordinates()){
                hero.killOrc(this);
            }
        }
    }
    public boolean isCollide(int coordinate1,int coordinate2,int range){
        return coordinate1 <= coordinate2 + range && coordinate1 >= coordinate2 - range;
    }
    private void setYCoordinates(int y) {
        this.coordinates[0][1] = y;
    }
    public int getYCoordinates() {
        return coordinates[0][1];
    }
    public int getXCoordinates(){
        return coordinates[0][0];
    }
    public void setXCoordinate(int x){ coordinates[0][0] = x;}
    public int getHitPoints() {return hitPoints;}
    public void setHitPoints(int hitPoints) {this.hitPoints = hitPoints;}

    @Override
    public void changeYCoordinate() {
        Timer timer = new Timer(true);
        TimerTask timerTask = this;
        timer.schedule(timerTask,0,period);
    }
    public int getBaseX(){
        return baseLevel[0][0];
    }
}
class Boss extends Orc{
    private boolean bossAlive;
    public Boss(int points,int period, int xCoordinate,int distance, int duration){
        super(points, period, xCoordinate,distance,duration);
        this.bossAlive = true;
    }
    public void setBossAlive(){
        this.bossAlive = false;
    }
    public boolean isBossAlive() {
        return bossAlive;
    }
}
