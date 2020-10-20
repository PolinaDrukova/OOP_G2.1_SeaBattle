package com.company.Player;

import com.company.BattleField;
import com.company.objects.Ship;


import java.util.ArrayList;
import java.util.List;


public class BasePlayer {

    public BattleField field = new BattleField();
    public List<Ship> ships = new ArrayList<>();


    public void newGame() {
        this.field = new BattleField();
    }

    public boolean isAlive() {
        for (Ship ship : ships) {
            if (ship.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public BattleField getField() {
        return field;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
