package tictactoe.game;

import tictactoe.game.player.Bot;
import tictactoe.game.player.IPlayer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GameApp implements IGameApp {

    private List<Game> games = new ArrayList<Game>();

    public void getGameAndStart(boolean botGame, boolean newGame, IPlayer player) throws RemoteException {
        IPlayer secondPlayer = null;
        Game game = null;
        if (botGame) {
            player.setFigure('O');
            secondPlayer = new Bot("n/n");
            secondPlayer.setFigure('X');
            game = new Game(player, secondPlayer);
        }else {
            if (newGame) {
                player.setFigure('O');
                game = new Game(player, secondPlayer);
                games.add(game);
            } else {
                for (Game gameIter : games){
                    if (!gameIter.isReady()) {
                        player.setFigure('X');
                        game = gameIter;
                        game.addPlayer(player);
                        break;
                    }
                }
                if (game == null){
                    player.printMessage("Sorry, there isn't any game to join. Creating new one...");
                    player.setFigure('O');
                    game = new Game(player, secondPlayer);
                    games.add(game);
                }
            }
        }


        if (game.isReady()) {
            game.begin();
        } else {
            synchronized (game) {
                player.printMessage("Waiting for second player...");
                game.waitForPlayer();
            }
        }
    }




}
