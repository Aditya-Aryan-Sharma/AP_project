package com.example.game;

public class User {
    private int score;
    private String name;
    private int coinsEarned;
    private Hero myHero;
    private PlayGameWindowController myGame;
    public User(String name,int score ){
        this.name=name;
        this.score=score;
        this.coinsEarned=0;
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

}
