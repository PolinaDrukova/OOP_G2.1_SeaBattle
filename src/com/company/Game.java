package com.company;

import com.company.BusinessLogic;
import com.company.Player.BasePlayer;



public class Game {

    private int n = 2;
    private BasePlayer[] players = new BasePlayer[n];
    private int currentIndex = 0;//индекс игрока который ходит
    private boolean step;


    public Game() {
        for (int i = 0; i < n; i++) {
            players[i] = new BasePlayer();
            BusinessLogic.fill(players[i].field, players[i].ships);
        }


    }


    public void process() {
        while (players[0].isAlive() && players[1].isAlive()) {
            if (step) {
                if (!BusinessLogic.shot(players[1])) {
                    step = false;
                }
            } else {
                if (BusinessLogic.shot(players[0])) {
                    step = true;
                }
            }

        }
    }


    public Integer whoWin() {
        int flag = -1;
        for (int i = 0; i < n; i++) {
            if (players[i].isAlive()) {
                if (flag != -1) {
                    return null;
                }
                flag = i;
            }
        }
        return flag;
    }

    public BasePlayer getPlayers(int i) {
        return players[i];
    }
    public BasePlayer[] getPlayers() {
        return players;
    }

    public boolean isStep() {
        return step;
    }

    public void setStep(boolean step) {
        this.step = step;
    }
}
