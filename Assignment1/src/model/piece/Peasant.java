package model.piece;

import model.Move;
import model.board.Board;
import model.player.Player;

/**
 * User: seanfreiburg
 * Date: 9/11/13
 * Time: 1:41 PM
 */
public class Peasant extends Piece {

    /**
     * Default constructor for Peasant that sets it's color
     *
     * @param color
     */
    public Peasant(boolean color) {
        super(color);
    }

    /**
     * Validates the moves for the Peasant
     *
     * @param move
     * @return boolean based on if move is valid for Peasant
     */
    public boolean validateMove(Move move, Board board) {

        int diffY = move.getEndY() - move.getStartY();
        int diffX = move.getStartX() - move.getEndX();

        if (Math.abs(diffX) == 1 && Math.abs(diffY) == 1) {
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Says whether a piece is in the way of the move
     *
     * @param move
     * @param board
     * @return return true if a piece is in the way, false otherwise
     */
    protected boolean pieceInWay(Move move, Board board) {
        return false;
    }


}
