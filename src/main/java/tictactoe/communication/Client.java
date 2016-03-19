package tictactoe.communication;

import tictactoe.game.player.AbstractPlayer;
import tictactoe.game.IGameApp;
import tictactoe.game.player.Player;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        if (args.length < 2){
            System.out.println("Please provide IP and port as arguments.");
            return;
        }

        String ip = args[0];
        String port = args[1];
        IGameApp gameApp = null;

        try {
            gameApp = (IGameApp) Naming.lookup("rmi://" + ip + ":" + port +  "/game");
        } catch (Exception e) {
            System.out.println("Error while connecting to server.");
        }

        gameApp.start();

    }
}
