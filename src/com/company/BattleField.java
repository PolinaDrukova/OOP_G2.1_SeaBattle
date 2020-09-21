package com.company;

import java.util.ArrayList;

public class BattleField {
    public GameElements[][] elements;
    public ArrayList<Ship> ships;

    public BattleField(GameElements[][] elements, ArrayList<Ship> ships) {
        this.elements = elements;
        this.ships = ships;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.elements[j][i] = new GameElements(j, i);
            }
        }
        this.putShips();
    }

    public void putShips() {
        GameElements element;
        for (int i = 0; i < 10; ++i) { //заполнение поля водой
            for (int j = 0; j < 10; ++j) {
                element = this.elements[j][i];
                element.state = State.water;
                element.shoot = false;
            }
        }

        this.ships = new ArrayList<>();

        for (int i = 1; i > 0; i++) { //добавление на поле кораблей
            for (int j = 5 - i; j > 0; j--) {
                Ship ship = new Ship(this, i);
                this.ships.add(ship);
            }
        }

        for (int i = 0; i < 10; i++) { //добавление запретной зон
            for (int j = 0; j < 10; j++) {
                element = this.elements[i][j];
                if (element.state == State.border) {
                    element.state = State.water;
                }
            }

        }

    }

    public boolean shot(int x, int y) {
        boolean shot = false;
        State state = this.getShipState(x, y);
        elements[x][y].shoot = true;

        if (state == State.enWell) {
            shot = true;
            Ship ship = elements[x][y].ship;
            if (ship.health != 0) { //если здоровье не нулевое, вычитается один пункт и присваивается тип ранен
                ship.health--;
                if (ship.health == 0) { //если здоровье нулевое присваивается тип убит
                    ship.state = ShipState.killed;
                } else {
                    ship.state = ShipState.injured;
                    elements[x][y].state = State.injured;
                }
            }
        } else {
            if ((state == State.border) ||    //если попало на границу или в воду, тип "мимо"
                    (state == State.water)) {
                this.setShip(x, y, State.missed);
            }
        }
        return shot;
    }


    public boolean setShip(int x, int y, State state) {
        if (this.check_Bound(x, y)) {
            this.elements[x][y].state = state;
        }

        return true;
    }

    public boolean check_Bound(int x, int y) {
        return x >= 0 && x <= 9 && y >= 0 && y <= 9;
    }


    public State getShipState(int x, int y) {//получаем тип элемента
        if (check_Bound(x, y)) {
            return elements[x][y].state;
        } else {
            return State.empty;
        }
    }
}

