package com.example.game;

import java.io.Serializable;

public class Weapon implements Serializable {
    private int damagePerHit;
    private int level;
    private final int weaponType;
    public Weapon(int type){
        this.level=1;
        this.damagePerHit=20;
        weaponType = type;
    }
    private void setDamagePerHit(int damage){
        damagePerHit=damage;
    }
    public int getDamagePerHit(){
        return this.damagePerHit;
    }
    public void setLevel(int level){
        this.level=level;
        setDamagePerHit(getDamagePerHit()+20);
    }
    public int getLevel(){
        return this.level;
    }
    public int getWeaponType(){
        return weaponType;
    }
}
