package controller;

import model.board.NormalChessBoard;
import model.player.Player;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:42 PM
 */
public class OnlineGameController extends GameController {
    /**
     * Sends the move to the board and responds to view with moves to make
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param player
     * @return
     */
    public String sendMove(int startX, int startY, int endX, int endY, Player player) {
       /* String status = player.sendMove(startX, startY, endX, endY, board);
        if (status.equals("Success")){
            view.setPiece(startX,startY, null);
            view.setPiece(endX,endY, board.getPiece(endX,endY));
            currentPlayer = getNextPlayer(currentPlayer);

            view.setTurnText(currentPlayer.getColor());
        }

        postMoveChecks();
        return status;*/

        if (myTurn(player)){

        }

        return "remove this";

    }

    private boolean myTurn(Player player) {
        //send a request to the server to find if it is your turn
        return false;
    }


    public void postMoveChecks() {
        //@todo add check, checkmate
        //@todo not working

    }


    /**
     * I restart the game and add 1 to the other players score and update the view
     */
    public void forfeit(){
        this.currentPlayer = getNextPlayer(currentPlayer);
        currentPlayer.setScore( 1+ currentPlayer.getScore());
        view.setScoreLabel(blackPlayer.getScore(), whitePlayer.getScore());
        restart();

    }

    /**
     * I restart the game and update the view
     */
    public void restart(){
        board = new NormalChessBoard();
        for (int y =0; y <board.getHeight(); y++){
            for (int x= 0; x < board.getWidth(); x++){
                view.setPiece(x,y,board.getPiece(x,y));
            }
        }
        this.currentPlayer = whitePlayer;
    }



}
