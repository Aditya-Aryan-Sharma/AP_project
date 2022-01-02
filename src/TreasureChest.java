package com.example.game;

import java.util.Random;

public class TreasureChest {
    private int chestType;
    private final int coins;

    public TreasureChest(int chest,int coins){
        chestType=chest;
        this.coins = coins;
    }
    public int getCoins(){
        return this.coins;
    }
}
class WeaponChest extends TreasureChest{
    private final Weapon weapon;
    private final int weaponType;

    public WeaponChest(){
        super(0,5);
        Random random = new Random();
        weaponType= random.nextInt(2);
        this.weapon = new Weapon(weaponType);
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
class CoinChest extends TreasureChest{

    public CoinChest(){
        super(1,30);
    }
}
