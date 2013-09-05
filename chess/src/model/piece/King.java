package model.piece;


import model.Move;
import model.board.Board;

/**
 * Class for the King Piece
 */
public class King extends Piece {



    /**
     * Validates the moves for the King
     *
     * @param move
     * @return boolean based on if move is valid for King
     */
    public boolean validateMove(Move move, Board board) {
        /* @todo check for check on a kings move which involves seeing if any piece of the other
        color would put the king in check on the move
        */

        if (Math.abs(move.getEndX() - move.getStartX()) > 1 || Math.abs(move.getEndY() - move.getStartY()) > 1 && !this.pieceInWay(move, board)) {
            return false;
        } else {
            return true;
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

    /**
     * Default constructor for King that sets it's color
     *
     * @param color
     */
    public King(boolean color) {
        super(color);
    }

}
