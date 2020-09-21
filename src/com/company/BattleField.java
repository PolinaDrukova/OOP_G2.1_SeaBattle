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

    public boolean isBound(int x, int y) {
        return x >= 0 && x <= 9 && y >= 0 && y <= 9;
    }

    public State GetElement(int x, int y) {//получаем тип элемента
        if (isBound(x,y)) {
            return elements[x][y].state;
        } else {
            return State.empty;
        }
    }
}

