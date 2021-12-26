package com.example.game;

import java.util.Random;

public class TreasureChest {
    private int chestType;

    public TreasureChest(int chest){
        chestType=chest;
    }
}
class WeaponChest extends TreasureChest{
    private final Weapon weapon;
    private final int weaponType;

    public WeaponChest(){
        super(0);
        this.weapon = new Weapon();
        Random random = new Random();
        weaponType= random.nextInt(2);
    }
    public void equipHero(Hero hero){
        hero.setMyWeapon(weapon);
    }
    public int getWeaponType() {
        return weaponType;
    }
    public Weapon getWeapon() {
        return weapon;
    }
}
