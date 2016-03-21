package tictactoe.communication;

import tictactoe.game.Game;
import tictactoe.game.IGameApp;
import tictactoe.game.player.Bot;
import tictactoe.game.player.IPlayer;
import tictactoe.game.player.Player;

import java.rmi.Naming;
import java.rmi.RemoteException;
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your username: \n");

        IPlayer player = null;
        try {
            player = new Player(scanner.nextLine());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        boolean botGame, newGame;

        System.out.println("Who do you want to play with? - type 'human' or 'computer'.");
        botGame = scanner.nextLine().equals("computer");

        if (!botGame) {
            System.out.println("Do you want to create a new game or join an existing one? Type 'new' or 'join'.");
            newGame = scanner.nextLine().equals("new");
        } else {
            newGame = true;
        }

        try {
            gameApp.getGameAndStart(botGame, newGame, player);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
