import controller.GameController;
import model.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

    public static void main(String[] args) throws IOException {
        GameController game = new GameController();
        Player currentPlayer = game.getWhitePlayer();
        String color = "Blank";
        while (true) {
            String message = " ";
            while (!message.equals("Success")) {
                game.getBoard().printBoard();

                ///getMoveFromPlayer

                if (currentPlayer.getColor()) {
                    color = "Black";
                } else {
                    color = "White";
                }
                System.out.println(color + " player's turn");
                System.out.println("Enter Start x:");
                BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
                int startX = Integer.parseInt(consoleIn.readLine());

                System.out.println("Enter Start y:");
                consoleIn = new BufferedReader(new InputStreamReader(System.in));
                int startY = Integer.parseInt(consoleIn.readLine());

                System.out.println("Enter End x:");
                consoleIn = new BufferedReader(new InputStreamReader(System.in));
                int endX = Integer.parseInt(consoleIn.readLine());

                System.out.println("Enter End y:");
                consoleIn = new BufferedReader(new InputStreamReader(System.in));
                int endY = Integer.parseInt(consoleIn.readLine());

                //send move to board
                message = game.sendMove(startX, startY, endX, endY, currentPlayer);
                System.out.println(message);
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

    }


}
