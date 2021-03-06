package com.example.turfwarsclient.model;

public class Bullet {
    private  int damage;
    private String type = "Bullet";
    private int posX;
    private int posY;
    private int mov;



    public Bullet(int posX, int posY, int mov, int damage) {
        super();
        this.posX = posX;
        this.posY = posY;
        this.mov = mov;
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getMov() {
        return mov;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
