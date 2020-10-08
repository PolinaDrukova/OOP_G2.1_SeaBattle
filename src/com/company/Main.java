package com.company;

import com.company.enum_state.DeckCount;
import com.company.enum_state.GameState;
import com.company.enum_state.Orientation;
import com.company.Input;
import com.company.objects.Point;
import com.company.objects.Ship;

public class Main {

    public static void main(String[] args) {
        BattleField playerMap = new  BattleField();
        BattleField enemyMap = new  BattleField();

        Input in = new Input(null);
        GameState gs =  GameState.Fill;
        Ship ship_d3 = Ship.make(playerMap, Orientation.Vertical, DeckCount.Three, new Point(2, 7));
        Ship ship_d2 = Ship.make(playerMap, Orientation.Horizontal, DeckCount.Four, new Point(2, 5));
        do {
            System.out.println("---- My Map ----");
            playerMap.draw();
            System.out.println("---- Enemy Map ----");
            enemyMap.draw();
            in.process(gs);

        } while (!in.isDone());
    }
}
