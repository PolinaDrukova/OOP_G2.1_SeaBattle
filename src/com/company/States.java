package com.company;

public class States {

    public Ship ship;
    public int x;
    public int y;
    public boolean shoot;
    public boolean water;
    public boolean border;
    public boolean ban;

    public States(int x, int y) {
        this.x = x;
        this.y = y;
        this.shoot = false;
        this.water = false;
        this.border = false;
        this.ban = false;
    }
}
