package com.company;

import com.company.BattleField;
import com.company.Game;
import com.company.Player.BasePlayer;
import com.company.objects.Point;

public class Service_Console {
    public static void draw(Game game) {
        for (BasePlayer player : game.getPlayers()) {
            draw(player.getBattleField());
        }
    }

    public static void draw(BattleField field) {
        System.out.println(toString(field));
    }


    private static String toString(BattleField field) {
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        for (char i = 'A'; i < 'K'; i++) {
            sb.append(" ").append(i);
        }
        sb.append("|\n");
        for (int i = 0; i < field.getWidth(); i++) {
            sb.append(i);
            for (int j = 0; j < field.getHeight(); j++) {
                Point point = new Point(j, i);
                sb.append(" ");
                switch (field.getCell(point).getState()) {
                    case alive:
                        sb.append("█");
                        break;
                    case injured:
                        sb.append("X");
                        break;
                    case missed:
                        sb.append("●");
                        break;
                    case empty:
                        sb.append(" ");
                        break;
                }
            }
            sb.append("|\n");

        }
        return sb.toString();
    }
}