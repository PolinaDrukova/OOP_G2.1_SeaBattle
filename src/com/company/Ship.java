package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Ship {
    public int x;
    public int y;
    public int dx;
    public int dy;
    public int size;
    public int health;

    public ShipState state;
    public BattleField field;

    public ArrayList<GameElements> elements;

    public Ship(BattleField field, int size) {
        this.size = size;
        this.health = size;
        this.field = field;
        this.state = ShipState.healthy;
        this.elements = new ArrayList<>();
    }

    private void getPlace() {
        Random rand = new Random();
        this.x = rand.nextInt(10);
        this.y = rand.nextInt(10);
        this.dx = 0;
        this.dy = 0;
        if (rand.nextInt(2) == 1) {
            this.dx = 1;
        } else {
            this.dy = 1;
        }

    }

}
