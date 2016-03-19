package tictactoe.game;

import tictactoe.game.player.AbstractPlayer;

public class Game {

    private AbstractPlayer playerOne;
    private AbstractPlayer playerTwo;
    private AbstractPlayer currentPlayer;
    private Board board;

    public Game(AbstractPlayer playerTwo, AbstractPlayer playerOne) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        this.board = new Board();
    }

    public boolean isReady() {
        return playerOne != null && playerTwo != null;
    }

    public void addPlayer(AbstractPlayer player) {
        this.playerTwo = player;
    }
}
