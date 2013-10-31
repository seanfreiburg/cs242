import controller.GameController;
import controller.OnlineGameController;
import view.BoardView;

import java.io.IOException;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:27 PM
 */
public class Client {
    public static void main(String[] args) throws IOException {

        GameController game = new OnlineGameController();
        BoardView view = new BoardView("Chess", game);
        game.setView(view);

    }
}
