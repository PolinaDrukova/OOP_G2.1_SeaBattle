package com.company;


import com.company.GUI.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Service_Console.draw(game);

        Service_BusinessLogic logic = new Service_BusinessLogic();
        logic.newGame(game);
        Service_Console.draw(game);


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (
                Throwable var3) {
            var3.printStackTrace();
        }

        MainFrame view = new MainFrame(game);

        view.setVisible(true);

        if (logic.whoWin(game) == -1) {
            String message = " Ничья ";
            JOptionPane.showMessageDialog(null, message);
        } else {
            String message = " Выйграл " + logic.whoWin(game) + " игрок";
            JOptionPane.showMessageDialog(null, message);
        }

    }
}
