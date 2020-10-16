package com.company;

import com.company.InputListener;
import com.company.enum_state.DeckCount;
import com.company.enum_state.GameState;
import com.company.enum_state.Orientation;
import com.company.objects.Point;

import java.util.Scanner;

public class Input implements InputListener {
    boolean done;
    private Scanner scanner;
    private InputListener listener;

    public Input(InputListener listener) {
        this.done = false;
        this.scanner = new Scanner(System.in);
        this.listener = listener;
    }

    public void process(GameState state) {
        String in = scanner.nextLine();
        System.out.print("command: " + in);
        boolean quitGame = "q".equals(in) || "quit".equals(in);

        if (listener != null) {
            if (quitGame) {
                listener.quitGame();
                return;
            }
            if (state == GameState.Fill) {
                fillProcess(in);
            } else if (state == GameState.Battle) {
                doProcess(in);
            }
        }
    }

    private Point parseCoords(String in) {
        String[] coords = in.split(",");
        int x = -1;
        int y = -1;

        if (coords.length >= 2) {
            char symbol = Character.toLowerCase(coords[0].charAt(0));
            x = symbol - 'a';
            y = Integer.parseInt(coords[1]) ;
        }
        return new Point(x, y);
    }

    private void fillProcess(String in) {
        String[] chunks = in.split(";");

        if (chunks.length >= 3) {
            Point c = parseCoords(chunks[0]);
            int decks = Integer.parseInt(chunks[2]);
            Orientation orient = Orientation.None;

            if ("H".equals(chunks[1]) || "h".equals(chunks[1])) {
                orient = Orientation.Horizontal;
            } else if (("V".equals(chunks[1]) || "v".equals(chunks[1]))) {
                orient = Orientation.Vertical;
            }
            if (orient != Orientation.None && c.x >= 0 && c.y >= 0) {
                listener.addNewShip(DeckCount.valueOf(decks), orient, new Point(c.x, c.y));
            }
        }
    }

    private void doProcess(String in) {
        Point c = parseCoords(in);

        if (c.x >= 0 && c.y >= 0) {
            listener.attack(c.x, c.y);
        }
    }


    @Override
    public boolean addNewShip(DeckCount decks, Orientation orient, Point startCoord) {
        return true;
    }

    @Override
    public void attack(int x, int y) {

    }

    @Override
    public void quitGame() {

    }
}

