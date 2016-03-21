package tictactoe.game;

import tictactoe.game.player.IPlayer;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Game implements Serializable{

    private IPlayer playerOne;
    private IPlayer playerTwo;
    private IPlayer currentPlayer;
    private Board board;
    private boolean isReady;

    public Game(IPlayer playerOne, IPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.currentPlayer = playerOne;
        this.board = new Board();
        this.isReady = (playerTwo != null);
    }

    public void begin() throws RemoteException {
        board.initBoard();
        this.playerOne.gameStarts();
        this.playerTwo.gameStarts();
        String move;
        boolean correctMove;

        while (!isFinished()) {
                do {
                    move = currentPlayer.makeMove();
                    correctMove = board.applyMove(move, currentPlayer.getFigure());
                    if (!correctMove){
                        this.currentPlayer.printMessage("You cannot make this move, try again.");
                    }
                } while (!correctMove);

                this.playerOne.printMessage(board.printBoard());
                this.playerTwo.printMessage(board.printBoard());

                if (!isFinished()) {
                    this.currentPlayer.printMessage("Waiting for the opponent...");
                    changePlayer();
                }
        }

        if (board.getBoardState().equals("draw")) {
            this.playerOne.draw();
            this.playerTwo.draw();
        } else {
            if (this.currentPlayer == this.playerOne) {
                this.playerOne.win();
                this.playerTwo.loose();
            } else {
                this.playerOne.loose();
                this.playerTwo.loose();
            }
        }

    }

    public synchronized void waitForPlayer(){

        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void changePlayer() {
        if (this.currentPlayer == this.playerOne) {
            this.currentPlayer = this.playerTwo;
        } else {
            this.currentPlayer = this.playerOne;
        }
    }

    private boolean isFinished() {
        return board.winnerExists() || board.hasDraw();
    }

    public boolean isReady() {
        return this.isReady;
    }

    public synchronized void addPlayer(IPlayer player) {
        this.playerTwo = player;
        this.isReady = true;
        notify();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (playerOne != null ? !playerOne.equals(game.playerOne) : game.playerOne != null) return false;
        if (playerTwo != null ? !playerTwo.equals(game.playerTwo) : game.playerTwo != null) return false;
        return board != null ? board.equals(game.board) : game.board == null;

    }

    @Override
    public int hashCode() {
        int result = playerOne != null ? playerOne.hashCode() : 0;
        result = 31 * result + (playerTwo != null ? playerTwo.hashCode() : 0);
        result = 31 * result + (board != null ? board.hashCode() : 0);
        return result;
    }
}
