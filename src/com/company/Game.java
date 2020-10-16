package com.company;

import com.company.Player.BasePlayer;
import com.company.Player.Player;
import com.company.enum_state.GameState;

public class Game {
    private GameState state;
    private BasePlayer[] players;
    private int currentIndex;
    private boolean isGameOver;

    private void next_step() {

        ++currentIndex;

        if( currentIndex >= players.length ) {
            currentIndex = 0;
        }
    }

    public Game() {

        state = GameState.Fill;

        players = new BasePlayer[2];
        players[0] = new Player( this );
        players[1] = new BasePlayer( this ); // BotPlayer

        currentIndex = 0;
        isGameOver = false;
    }

    public GameState getState() {

        return state;
    }

    public void process() {

        if( state != GameState.Battle &&
                players[0].is_map_filled() &&
                players[1].is_map_filled() )
        {
            state = GameState.Battle;
        }

        players[currentIndex].process();

        next_step();
    }

    public void setGameOver() {

        isGameOver = true;
    }

    public boolean isGameOver() {

        return isGameOver;
    }
}
