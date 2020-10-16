package com.company.Player;

import com.company.Game;
import com.company.Input;
import com.company.InputListener;
import com.company.enum_state.DeckCount;
import com.company.enum_state.Orientation;

import com.company.objects.Point;


public class Player extends BasePlayer implements InputListener {

    private Input input;

    public Player(Game game) {

        super(game);

        input = new Input(this);
    }

    @Override
    public boolean addNewShip(DeckCount decks, Orientation orient, Point startCoord) {
        System.out.println("make_ship x, y (" + startCoord.x + ", " + startCoord.y + ") " + decks);

        addShip(orient, decks, startCoord);

        return getFreePlaces(decks) > 0;
    }

    public void attack(int x, int y) {

    }

    @Override
    public void quitGame() {
        _game.setGameOver();
    }

    @Override
    public void process() {

        super.process();

        input.process(_game.getState());
    }
}