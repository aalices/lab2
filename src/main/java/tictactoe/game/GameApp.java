package tictactoe.game;

import tictactoe.game.player.AbstractPlayer;
import tictactoe.game.player.Bot;
import tictactoe.game.player.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameApp implements IGameApp {

    private List<Game> games = new ArrayList<Game>();
    Scanner scanner = new Scanner(System.in);


    public void start() throws RemoteException {
        AbstractPlayer player = createPlayer();
        boolean botGame, newGame;

        System.out.println("Who do you want to play with? - type 'human' or 'computer'.");
        botGame = scanner.nextLine().equals("bot");

        System.out.println("Do you want to create a new game or join an existing one? Type 'new' or 'join'.");
        newGame = scanner.nextLine().equals("new");

        AbstractPlayer secondPlayer = null;
        if (botGame) {
            secondPlayer = new Bot("n/n");
        }
        if (newGame) {
            Game game = new Game(player, secondPlayer);
            games.add(game);
        } else {
            for (Game game : games){
                if (!game.isReady()) {
                    game.addPlayer(secondPlayer);
                    break;
                }
            }
        }
    }

    private AbstractPlayer createPlayer() throws RemoteException {
        System.out.println("Type your username: \n");

        return new Player(scanner.nextLine());
    }



}
