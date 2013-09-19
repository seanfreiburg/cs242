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




        /*
        String color = "Blank";
        while (true) {
            String message = " ";
            while (!message.equals("Success")) {

            }
            //reset message
            message = " ";
            Player otherPlayer = game.getNextPlayer(currentPlayer);

            if (otherPlayer.isInCheck(game.getBoard())){
                otherPlayer.setInCheck(true);
            }
            if (otherPlayer.inCheckMate(game.getBoard())){
                System.out.println(color +" player wins!");
                return;
            }
            currentPlayer = game.getNextPlayer(currentPlayer);


        }
        */

    }


}
