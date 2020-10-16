package com.company;

import com.company.enum_state.DeckCount;
import com.company.objects.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class BattleField {
    public static final Logger log = Logger.getLogger(BattleField.class.getName());

    public final int width = 10;
    public final int height = 10;
    private char[] head;
    private char[][] cells;
    private IMapObject[][] objects;
    private DeckCount deckCount;
    Point[] neighbours;


    public BattleField() {
        head = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        cells = new char[width][height];
        objects = new IMapObject[width][height];

    }


    public void addObject(IMapObject object) {
        Point position = object.getPosition();
        if (isValidCoord(position)) {
            objects[position.x][position.y] = object;
        } else {
            log.warning("can't  add map object");
        }
    }


    public boolean isCollide(Point position) { // дописать границы
        return objects[position.x][position.y] != null;
    }

    public boolean hasNeighbour(Point position) {
        boolean result = false;
        for (Point p : neighbours) {
            Point neighbour = new Point(position.x + p.x, position.y + p.y);
            if (isValidCoord(neighbour) && objects[neighbour.x][neighbour.y] != null) {
                result = true;
                break;
            }
        }
        return result;
    }


    public boolean isValidCoord(Point point) {
        return point.x >= 0 && point.x < width && point.y >= 0 && point.y < height;
    }

    public void clean() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = ' ';
            }
        }
        neighbours = new Point[8];
        neighbours[0] = new Point(-1, -1);
        neighbours[1] = new Point(-1, 0);
        neighbours[2] = new Point(-1, 1);
        neighbours[3] = new Point(0, 1);
        neighbours[4] = new Point(1, 1);
        neighbours[5] = new Point(1, 0);
        neighbours[6] = new Point(1, -1);
        neighbours[7] = new Point(0, -1);
    }

    private void update() {
        clean();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                IMapObject object = objects[x][y];
                if (object != null) {
                    Point position = object.getPosition();

                    cells[position.x][position.y] = object.getView();
                }
            }
        }
    }

    public void draw() {
        update();
        int coordX = 0;
        int coordY = 0;
        System.out.print(' ');
        for (int x = 0; x < width; x++) {
            System.out.print(' ');
            System.out.print(head[coordX]);
            coordX += 1;
        }
        System.out.println();
        for (int y = 0; y < height; y++) {
            System.out.print(coordY++);
            for (int x = 0; x < width; x++) {
                if (!(x == 0 && coordY == height + 1)) {
                    System.out.print(' ');
                }
                System.out.print(cells[x][y]);

            }
            System.out.println('|');
        }
    }

}
