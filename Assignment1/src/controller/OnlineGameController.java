package controller;

import model.board.NormalChessBoard;
import model.player.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:42 PM
 */
public class OnlineGameController extends GameController {

    final static String base_url = "http://localhost:8000/";

    /**
     * Sends the move to the board and responds to view with moves to make
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param player
     * @return
     */
    public String sendMove(int startX, int startY, int endX, int endY, Player player) {

        //I need to send data to the server here

        String status = "Error";
        try {
            URL localhost = new URL(base_url + "send_move");
            URLConnection lc = localhost.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            lc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                status = inputLine;
            }
            in.close();
        } catch (Exception e) {
            //this is for garbage errors intellij was giving me
        }


        if (status.equals("Success")) {
            view.setPiece(startX, startY, null);
            view.setPiece(endX, endY, board.getPiece(endX, endY));
            currentPlayer = getNextPlayer(currentPlayer);

            view.setTurnText(currentPlayer.getColor());
        }

        postMoveChecks();
        return status;

    }

    /**
     * I restart the game and add 1 to the other players score and update the view
     */
    public void forfeit() {

        String status = "Error";
        try {
            URL localhost = new URL(base_url + "forfeit");
            URLConnection lc = localhost.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            lc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                status = inputLine;
            }
            in.close();
        } catch (Exception e) {
            //this is for garbage errors intellij was giving me
        }


        if (status.equals("Success")) {
            this.currentPlayer = getNextPlayer(currentPlayer);
            currentPlayer.setScore(1 + currentPlayer.getScore());
            view.setScoreLabel(blackPlayer.getScore(), whitePlayer.getScore());
            super.restart();
        }

    }

    /**
     * I restart the game and update the view
     */
    public void restart() {
        String status = "Error";
        try {
            URL localhost = new URL(base_url + "restart");
            URLConnection lc = localhost.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            lc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                status = inputLine;
            }
            in.close();
        } catch (Exception e) {
            //this is for garbage errors intellij was giving me
        }


        if (status.equals("Success")) {
            board = new NormalChessBoard();
            for (int y = 0; y < board.getHeight(); y++) {
                for (int x = 0; x < board.getWidth(); x++) {
                    view.setPiece(x, y, board.getPiece(x, y));
                }
            }
            this.currentPlayer = whitePlayer;
        }
    }


}
