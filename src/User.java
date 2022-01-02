package com.example.game;

import java.io.Serializable;

public class User implements Serializable {
    private int score;
    private String name;
    private int coinsEarned;
    private Hero myHero;

    public User(String name,int score ){
        this.name=name;
        this.score=score;
        this.coinsEarned=0;
        this.myHero=new Hero();
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score =score;
    }
    public int getCoinsEarned() {
        return this.coinsEarned;
    }
    public void setCoinsEarned(int coinsEarned) {
        this.coinsEarned = coinsEarned;
    }
    public Hero getMyHero() {
        return myHero;
    }
    public void setMyHero(Hero myHero) {
        this.myHero = myHero;
    }
}
