package tictactoe.communication;

import tictactoe.game.GameApp;
import tictactoe.game.IGameApp;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    static IGameApp gameAppInt;

    public static void main(String[] args) {

        if (args.length < 2){
            System.out.println("Please provide IP and port as arguments.");
            return;
        }

        String ip = args[0];
        String port = args[1];

        try {
            gameAppInt = new GameApp();
            IGameApp game = (IGameApp) UnicastRemoteObject.exportObject(gameAppInt, 0);
            Naming.rebind( "rmi://" + ip + ":" + port +  "/game", game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static IGameApp findGame() {
        return null;
    }
}
