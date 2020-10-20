package com.company;

import com.company.BattleField;
import com.company.Game;
import com.company.Player.BasePlayer;
import com.company.enum_state.CellState;
import com.company.enum_state.DeckCount;
import com.company.enum_state.Orientation;
import com.company.objects.Cell;
import com.company.objects.Point;
import com.company.objects.Ship;

import java.util.*;

public class BusinessLogic {

    public static void fill(BattleField field, List<Ship> ships) {
        Random rand = new Random();
        Map<DeckCount, Integer> shipCounter = shipCounter();

        for (int i = 1; i < DeckCount.values().length; ++i) {
            DeckCount dc = DeckCount.valueOf(i);

            for (int j = 0; j < shipCounter.get(dc); j++) {


                Orientation orient = rand.nextInt(2) > 0 ? Orientation.Horizontal
                        : Orientation.Vertical;

                for (int k = 0; k < 10; k++) {
                    int x = rand.nextInt(10);
                    int y = rand.nextInt(10);

                    Point coord = new Point(x, y);

                    if (isPosiblePlace(field, orient, dc, coord)) {
                        addShip(field, orient, dc, coord, ships, shipCounter);

                        break;
                    }
                }
            }
        }
    }

    private static Map<DeckCount, Integer> shipCounter() {
        Map<DeckCount, Integer> shipCounter = new HashMap<>();
        for (int i = 1; i < DeckCount.values().length; i++) {
            int shipCount = 5 - i;
            shipCounter.put(DeckCount.valueOf(i), shipCount);
        }
        return shipCounter;
    }

    private static void addShip(BattleField field, Orientation orient, DeckCount dc, Point startCoord, List<Ship> ships, Map<DeckCount, Integer> shipCounter) {

        int free_places = shipCounter.get(dc);

        if (free_places > 0) {
            Point[] coords = getCoordsForShip(field, orient, dc, startCoord);
            if (coords == null) {
                return;
            }
            List<Cell> cells = new ArrayList<>();

            for (Point point : coords) {
                cells.add(field.getCell(point));
            }
            Ship ship = new Ship(orient, dc, cells);
            ships.add(ship);
            free_places = free_places - 1;
            shipCounter.replace(dc, free_places);

        }
    }

    private static boolean isPosiblePlace(BattleField field, Orientation orient, DeckCount dc, Point startCoord) {
        Point step = orient.getDirection();
        boolean isPosiblePlace = true;
        Point position = new Point(startCoord.getX(), startCoord.getY());
        Point lastPosition = new Point(-1, -1);
        for (int i = 0; i < dc.getValue(); ++i) {
            isPosiblePlace = field.isValidCoord(position) &&
                    field.noNeighbours(position, lastPosition);

            if (!isPosiblePlace) {
                break;
            }
            lastPosition.setX(position.getX());
            lastPosition.setY(position.getY());
            position.setX(position.getX() + step.getX());
            position.setY(position.getY() + step.getY());
        }

        return isPosiblePlace;
    }

    private static Point[] getCoordsForShip(BattleField map, Orientation orientation, DeckCount deckCount, Point startCoord) {
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
        Point position = new Point(startCoord.getX(), startCoord.getY());
        Point lastPosition = new Point(-1, -1);
        for (int i = 0; i < deckCount.getValue(); i++) {
            isPosiblePlace = map.isValidCoord(position) && map.noNeighbours(position, lastPosition);
            if (!isPosiblePlace) {
                break;
            }
            lastPosition.setX(position.getX());
            lastPosition.setY(position.getY());
            coord[i] = new Point(position.getX(), position.getY());
            position.setX(position.getX() + step.getX());
            position.setY(position.getY() + step.getY());
        }
        if (isPosiblePlace) {
            return coord;
        }
        return null;
    }

    public static boolean newGame(Game game) {
        while (game.getPlayers(0).isAlive() && game.getPlayers(1).isAlive()) {
            if (game.isStep()) {
                if (!BusinessLogic.shot(game.getPlayers(1))) {
                    game.setStep(false);
                }
            } else {
                if (BusinessLogic.shot(game.getPlayers(0))) {
                    game.setStep(false);
                }
            }
        }
        return game.isStep();
    }

    public static boolean shot(BasePlayer player) {
        Cell cell = player.getField().getShotPoints().remove(random(0, player.getField().getShotPoints().size()));
        if (cell.state == CellState.alive) {
            cell.setState(CellState.injured);
            for (Ship ships : player.getShips()) {
                if (ships.getDecks().contains(cell)) {
                    if (!ships.isAlive()) {
                        killAround(player, ships);
                    }

                    return true;
                } else {
                    cell.setState(CellState.missed);

                }

            }
        }
        return false;
    }

    private static void killAround(BasePlayer player, Ship ship) {
        for (Cell cell : ship.getDecks()) {
            int x = cell.getPosition().getX();
            int y = cell.getPosition().getY();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    Point point = new Point(x + i, y + j);
                    if (player.getField().isValidCoord(point)) {
                        Cell c = player.getField().getCell(point);
                        if (player.getField().getShotPoints().contains(c)) {
                            player.getField().getShotPoints().remove(cell);
                            if (c.getState() == CellState.empty) {
                                c.setState(CellState.missed);
                            }
                        }
                    }
                }
            }
        }
    }

    private static int random(int x, int y) {
        return (int) (Math.random() * (y - x)) + x;
    }
}