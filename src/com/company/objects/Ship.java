package com.company.objects;

import com.company.objects.Cells;
import com.company.enum_state.DeckCount;
import com.company.enum_state.Orientation;
import com.company.BattleField;

import java.util.Map;

public class Ship {

    private Orientation orientation;
    private DeckCount deckCount;
    private Point[] coords;
    Cells[] decks;
    private boolean isAlive;
    private boolean isChecked;

    public Ship() {
        this.orientation = Orientation.None;
        this.deckCount = DeckCount.Invalid;
        this.coords = null;
    }

    public Ship(int n) {
        n = deckCount.getValue();
    }

    public Ship(Ship ship) {
        this.isAlive = true;
        this.decks = ship.decks;
        this.isChecked = false;
    }


    public Ship(Field map, Orientation orientation, DeckCount deckCount, Point[] coords) {
        this.orientation = orientation;
        this.deckCount = deckCount;
        this.coords = coords;
        decks = new Cells[deckCount.getValue()];

        for (int i = 0; i < decks.length; i++) {
            Cells deck = new Cells(coords[i], 'O', 'X');
            decks[i] = deck;
            map.addObject(deck);
        }
    }


    public boolean isValid() {
        return coords != null && orientation != Orientation.None && deckCount != DeckCount.Invalid;

    }

    public boolean isAlive() {
        boolean result = false;
        for (Cells deck : decks) {
            if (deck.isAlive()) {
                result = true;
            }
        }
        return result;
    }

    public DeckCount getDeckCount(){
        return deckCount;
    }

    public static Point[] getCoordsForShip(Field map, Orientation orientation, DeckCount deckCount, Point startCoord) {
        Point step;
        if (orientation == Orientation.Horizontal) {
            step = new Point(1, 0);
        } else if (orientation == Orientation.Vertical) {
            step = new Point(0, 1);
        } else {
            return null;
        }
        boolean isPosiblePlace = true;
        Point[] coord = new Point[deckCount.getValue()];
        Point position = new Point(startCoord.x, startCoord.y);
        for (int i = 0; i < deckCount.getValue(); i++) {
            isPosiblePlace = map.isValidCoord(position) && !map.isCollide(position) && !map.hasNeighbour(position);
            if (!isPosiblePlace) {
                break;
            }
            coord[i] = new Point(position.x, position.y);
            position.x = position.x + step.x;
            position.y = position.y + step.y;

        }
        if (isPosiblePlace) {
            return coord;
        }

        return null;
    }

}
