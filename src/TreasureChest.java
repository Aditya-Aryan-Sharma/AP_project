package com.example.game;

import java.util.Random;

public class TreasureChest {
    private int chestType;

    public TreasureChest(int chest){
        chestType=chest;
    }
}
class WeaponChest extends TreasureChest{
    private Weapon weapon;
    private int weaponType;

    public WeaponChest(){
        super(0);
        this.weapon = new Weapon();
        Random random = new Random();
        weaponType= random.nextInt(2);
    }
    public int getWeaponType() {
        return weaponType;
    }
    public Weapon getWeapon() {
        return weapon;
    }
}