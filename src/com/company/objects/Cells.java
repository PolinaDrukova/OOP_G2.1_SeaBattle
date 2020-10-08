package com.company.objects;

import com.company.IMapObject;


public class Cells implements IMapObject {
    private Point position;
    private char alive_view;
    private char dead_view;
    private boolean isAlive;

    public Cells(Point position, char alive_view, char dead_view) {
        this.position = position;
        this.alive_view = alive_view;
        this.dead_view = dead_view;
        this.isAlive = true;
    }

    public boolean isAlive(){
        return isAlive;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public char getView() {
        return isAlive ? alive_view : dead_view;
    }
}
