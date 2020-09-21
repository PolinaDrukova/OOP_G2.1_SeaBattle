package com.company;

public class GameElements {
    public Ship ship;
    public int x;
    public int y;
    public State state;
    public boolean shoot;

    public GameElements(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = State.water;
        this.shoot = false;
    }
}
