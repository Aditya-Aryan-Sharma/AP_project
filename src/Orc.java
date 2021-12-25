package com.example.game;

import java.util.Timer;
import java.util.TimerTask;

public class Orc extends TimerTask {
    private int hitPoints;
    private int[][] coordinates;
    private final int baseLevel;
    private boolean ret;
    private int distance;
    public Orc(int points, int period,int xCoordinate,int distance,int duration){
        this.hitPoints = points;
        ret = false;
        coordinates = new int[1][2];
        coordinates[0][0] = xCoordinate;
        coordinates[0][1] = 45;
        baseLevel = coordinates[0][1];
        this.distance = (distance/duration)*period;
        Timer timer = new Timer(true);
        TimerTask timerTask = this;
        timer.schedule(timerTask,0,period);
    }
    @Override
    public void run() {
        int y=getYCoordinates();
        if (y == baseLevel+70)
            ret = true;
        if (y == baseLevel)
            ret = false;
        if(y <= baseLevel+70 && y >= baseLevel){
            if (ret){
                this.setYCoordinates(getYCoordinates() - distance);
            }
            else{
                this.setYCoordinates(getYCoordinates() + distance);
            }
        }
    }
    public void killHero(Hero hero, int range){
    }
    public boolean isCollide(int coordinate1,int coordinate2,int range){
        return coordinate1 <= coordinate2 + range && coordinate1 >= coordinate2 - range;
    }
    private void setYCoordinates(int y) {
        this.coordinates[0][1] = y;
    }

    private int getYCoordinates() {
        return coordinates[0][1];
    }
    private int getXCoordinates(){
        return coordinates[0][0];
    }
}
class Boss extends Orc{
    private boolean bossAlive;
    public Boss(int points,int period, int xCoordinate,int distance, int duration){
        super(points, period, xCoordinate,distance,duration);
        this.bossAlive = true;
    }

}