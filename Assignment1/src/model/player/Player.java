package model.player;

import model.Move;
import model.board.Board;
import model.board.NormalChessBoard;
import model.piece.Piece;

/**
 * Abstract class that defines base Player attributes
 */
public abstract class Player {

    boolean color;
    boolean inCheck;
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    /**
     * Constructor for player that sets the players color
     *
     * @param color
     */
    public Player(boolean color) {
        this.color = color;
        inCheck = false;
    }

    /**
     * Gets Player's color
     *
     * @return string as color of player
     */
    public boolean getColor() {
        return color;
    }

    /**
     * Send the move to the board for validation and execution
     * @param startX
     * @param endX
     * @param startY
     * @param endY
     * @param board
     * @return message from board
     */
    public String sendMove(int startX,  int startY,int endX, int endY, Board board) {
        return board.validateAndExecuteMove(new Move(startX, startY, endX, endY), this);
    }

    /**
     * Sees if move belongs to player
     * @param x
     * @param y
     * @param board
     * @return true if piece does belongs to player
     */
    public boolean pieceIsMine(int x, int y, Board board) {
        if (board.coordinateOnBoard(x, y)) {
            Piece piece = board.getPiece(x, y);
            if (piece != null) {
                return piece.getColor() == this.getColor();
            }
        }
        return false;

    }



    /**
     * Checks if the King is in check
     * @param board
     * @return if player is in check
     */
    public boolean isInCheck(Board board){
        return board.inCheck(this);
    }

    /**
     * Checks if the King is in checkmate
     *
     * @return True if King is in checkmate, false otherwise
     */
    public boolean inCheckMate(Board board){
        return board.inCheckmate(this);
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    public boolean getInCheck() {
        return inCheck;
    }

    int score;
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



}
