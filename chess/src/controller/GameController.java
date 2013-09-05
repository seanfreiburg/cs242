package controller;


import model.board.NormalChessBoard;
import model.player.HumanPlayer;
import model.player.Player;

public class GameController {

    NormalChessBoard board;
    Player whitePlayer;
    Player blackPlayer;

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public NormalChessBoard getBoard() {
        return board;
    }

    public void setBoard(NormalChessBoard board) {
        this.board = board;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }


    public GameController() {
        board = new NormalChessBoard();
        whitePlayer = new HumanPlayer(Player.WHITE);
        blackPlayer = new HumanPlayer(Player.BLACK);
    }

    public Player getNextPlayer(Player player) {
        if (player.getColor() == Player.BLACK) {
            return this.getWhitePlayer();
        } else {
            return this.getBlackPlayer();
        }
    }

    public String sendMove(int startX, int startY, int endX, int endY, Player player) {
        String status = player.sendMove(startX, endX, startX, endY, board);
        postMoveChecks();
        return status;
    }

    public void postMoveChecks() {
        //@todo add check
        //@todo not working
        /*if (board.isInCheck(whitePlayer.getColor())) {
            System.out.println("White King is in check");
        }
        if (board.isInCheck(blackPlayer.getColor())) {
            System.out.println("Black King is in check");
        }*/
    }
}
