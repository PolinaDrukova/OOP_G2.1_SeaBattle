package com.company.GUI;

import com.company.Game;
import com.company.Service_BusinessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
    public Game game;
    public GameView view;
    public Service_BusinessLogic logic;

    public GameController(GameView view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("New game")) {
            this.logic.newGame(game);
        }

        if (cmd.equals("Exit")) {
            System.exit(0);
        }

    }
}

