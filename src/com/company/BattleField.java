package com.company;


import com.company.enum_state.CellState;
import com.company.objects.Cell;
import com.company.objects.Point;

import java.util.ArrayList;
import java.util.List;


public class BattleField {

    public final int width = 10;
    public final int height = 10;

    //список кораблей игрока
    protected List<Cell> shotPoints = new ArrayList<>();//список  клеток дступных для обстрела

    private Cell[][] cells = new Cell[height][width];

    public BattleField() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point point = new Point(x, y);
                if (isValidCoord(point)) {
                    this.cells[y][x] = new Cell(point);
                    shotPoints.add(this.cells[y][x]);
                }
            }
        }
    }


    public boolean noNeighbours(Point position, Point lastPosition) { //границы вокруг корабля, на которые нельзя ставить другой корабль

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0)) {
                    Point point = new Point(position.getX() + i, position.getY()+ j);
                    if (isValidCoord(point) && getCell(point).getState() == CellState.alive) {
                        if (!(point.getX() == lastPosition.getX() && point.getY() == lastPosition.getY())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean isValidCoord(Point point) {
        return point.getX() >= 0 && point.getX() < width && point.getY() >= 0 && point.getY() < height;
    }

    public Cell getCell(Point point) {
        return cells[point.getX()][point.getY()];
    }

    public List<Cell> getShotPoints() {
        return shotPoints;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}




