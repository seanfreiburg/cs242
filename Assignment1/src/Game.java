import controller.GameController;
import model.board.Board;
import model.board.NormalChessBoard;
import model.player.Player;
import view.BoardView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    public static void main(String[] args) throws IOException {

        GameController game = new GameController();
        BoardView view = new BoardView("Chess", game);
        game.setView(view);
        NormalChessBoard board = new NormalChessBoard();
        Player currentPlayer = game.getWhitePlayer();

    }


}
