package tictactoe.game.player;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Player extends UnicastRemoteObject implements IPlayer {

    private String nick;
    private char figure;

    private Scanner scanner = new Scanner(System.in);

    public Player(String nick) throws RemoteException {
        super();
        this.nick = nick;
    }

    public void setFigure(char figure) throws RemoteException {
        this.figure = figure;
    }

    public char getFigure() throws RemoteException{
        return figure;
    }

    public String makeMove() throws RemoteException{
        System.out.println("Your move, type in coordinates: ");
        return scanner.nextLine();
    }

    public void printMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public void draw() throws RemoteException {
        System.out.println("We have a draw!");
        this.unexport();
    }

    public void win() throws RemoteException {
        System.out.println("You won, bravo!");
        this.unexport();
    }

    public void loose() throws RemoteException {
        System.out.println("You lost, sorry!");
        this.unexport();
    }

    public void gameStarts() throws RemoteException {
        System.out.println("THE GAME STARTS \n You've got " + this.figure);
    }

    private void unexport() {
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            e.printStackTrace();
        }
    }


}
