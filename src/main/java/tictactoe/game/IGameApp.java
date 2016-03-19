package tictactoe.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameApp extends Remote {


    void start() throws RemoteException;
}
