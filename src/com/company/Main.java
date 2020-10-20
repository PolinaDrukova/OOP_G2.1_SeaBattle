package com.company;


public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Console.draw(game);

        BusinessLogic.newGame(game);
        Console.draw(game);
    }

}
