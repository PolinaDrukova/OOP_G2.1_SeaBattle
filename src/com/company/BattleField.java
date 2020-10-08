package com.company;

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
    Map<Character, Integer> points = new HashMap<>();

    public BattleField() {
        head = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        cells = new char[width][height];
        objects = new IMapObject[width][height];

        points.put('A', 0);
        points.put('B', 1);
        points.put('C', 2);
        points.put('D', 3);
        points.put('E', 4);
        points.put('F', 5);
        points.put('G', 6);
        points.put('H', 7);
        points.put('I', 8);
        points.put('J', 9);
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


    public boolean isValidCoord(Point point) {
        return point.x >= 0 && point.x < width && point.y >= 0 && point.y < height;
    }

    public void clean() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = ' ';
            }
        }
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
