package tictactoe.game;

import tictactoe.game.player.IPlayer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IGameApp extends Remote {

    void getGameAndStart(boolean botGame, boolean newGame, IPlayer player) throws RemoteException;
}
