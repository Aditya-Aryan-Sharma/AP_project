package com.example.game;

import java.io.Serializable;

public class Weapon implements Serializable {
    private int damagePerHit;
    private int level;
    public Weapon(){
        this.level=1;
        this.damagePerHit=20;
    }
    public void setDamagePerHit(int damage){
        damagePerHit=damage;
    }
    public int getDamagePerHit(){
        return this.damagePerHit;
    }
    public void setLevel(int level){
        this.level=level;
    }
    public int getLevel(){
        return this.level;
    }
}
