package com.company.GUI;

import com.company.Game;
import com.company.Service_BusinessLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    public Game game;
    public MainFrame view;


    public Controller(MainFrame view, Game game) {
        this.view = view;
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Новая игра")) {
            Game game1 = new Game();
            Service_BusinessLogic logic = new Service_BusinessLogic();
            MainFrame view = new MainFrame(game1);
            view.setVisible(true);
            logic.newGame(game1);
        }
        if (cmd.equals("Выход")) {
            System.exit(0);
        }

    }
}

