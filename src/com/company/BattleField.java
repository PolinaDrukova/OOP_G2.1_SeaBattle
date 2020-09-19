package com.company;
import java.util.ArrayList;

public class BattleField {
    public States[][] elements;
    public ArrayList<Ship> ships;

    public BattleField(States[][] elements, ArrayList<Ship> ships) {
        this.elements = elements;
        this.ships = ships;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.elements[j][i] = new States(j, i);
            }
        }
        this.putShips();
    }

    public void putShips() {
        States element;
        for (int i = 0; i < 10; ++i) { //заполнение поля водой
            for (int j = 0; j < 10; ++j) {
                element = this.elements[j][i];
                element.water = true;
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
                States state = elements[j][i];
                if (state.border) {
                    state.ban = true;
                }
            }

        }

    }

}
