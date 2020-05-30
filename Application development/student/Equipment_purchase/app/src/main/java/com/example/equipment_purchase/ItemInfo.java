package com.example.equipment_purchase;

import java.io.Serializable;

public class ItemInfo implements Serializable {
    private int life;
    private int firepower;
    private int armor;

    public ItemInfo(int life, int firepower, int armor) {
        this.life = life;
        this.firepower = firepower;
        this.armor = armor;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getFirepower() {
        return firepower;
    }

    public void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
