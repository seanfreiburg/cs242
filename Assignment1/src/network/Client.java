package network;

import controller.GameController;
import controller.OnlineGameController;
import model.player.HumanPlayer;
import model.player.Player;
import view.BoardView;

import java.io.IOException;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:27 PM
 */
public class Client {
    public static void main(String[] args) throws IOException {

        Client t = new Client();


    }

    public Client(){
        OnlineGameController game = new OnlineGameController();
        BoardView view = new BoardView("Chess", game);
        game.setView(view);
        game.setCurrentPlayer(setPlayerFromNetwork());
    }

    public Player setPlayerFromNetwork(){
        return new HumanPlayer(true);

    }
}
