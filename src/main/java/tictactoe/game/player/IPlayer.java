package tictactoe.game.player;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface IPlayer extends Remote {

    void setFigure(char figure) throws RemoteException;

    String makeMove() throws RemoteException;
    void printMessage(String message) throws RemoteException;

    char getFigure() throws RemoteException;

    void draw() throws RemoteException;
    void win() throws RemoteException;
    void loose() throws RemoteException;

    void gameStarts() throws RemoteException;
}
