package com.company;

import com.company.enum_state.DeckCount;
import com.company.enum_state.Orientation;
import com.company.objects.Point;

public  interface InputListener {
    boolean addNewShip(DeckCount decks, Orientation orient, Point startCoord);
    void attack(int x, int y);
    void quitGame();
}
