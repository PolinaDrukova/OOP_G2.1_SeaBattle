package com.company.Player;

import com.company.BattleField;
import com.company.objects.Ship;


import java.util.ArrayList;
import java.util.List;


public class BasePlayer {

    public BattleField field = new BattleField();
    public List<Ship> ships = new ArrayList<>();


    public boolean isAlive() {
        for (Ship ship : ships) {
            if (ship.isAliveShip()) {
                return true;
            }
        }
        return false;
    }

    public BattleField getBattleField() {
        return field;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
