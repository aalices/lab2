package tictactoe.game.player;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Bot extends UnicastRemoteObject implements IPlayer {

    private String nick;
    private char figure;

    public Bot(String nick) throws RemoteException {
        this.nick = nick;
    }

    public void setFigure(char figure) throws RemoteException {
        this.figure = figure;
    }

    public String makeMove() throws RemoteException {
        int x, y;
        Random r = new Random();
        x = r.nextInt(3)+1;
        y = r.nextInt(3)+1;
        return x + " " + y;
    }

    public void printMessage(String message) throws RemoteException {}

    public char getFigure() {
        return this.figure;
    }

    public void draw() throws RemoteException {}

    public void win() throws RemoteException {}

    public void loose() throws RemoteException {}

    public void gameStarts() throws RemoteException {}
}
