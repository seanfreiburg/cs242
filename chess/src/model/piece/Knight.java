package model.piece;


import model.Move;
import model.board.Board;

/**
 * Class for the Knight Piece
 */
public class Knight extends Piece {
    /**
     * Validates the moves for the Knight
     *
     * @param move
     * @return boolean based on if move is valid for Knight
     */
    public boolean validateMove(Move move, Board board) {
        int diffX = Math.abs(move.getStartX() - move.getEndX());
        int diffY = Math.abs(move.getStartY() - move.getEndY());
        if ((diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Says whether a piece is in the way of the moved
     *
     * @param move
     * @param board
     * @return return true if a piece is in the way, false otherwise
     */
    protected boolean pieceInWay(Move move, Board board) {
        // not needed, LOL
        return false;
    }

    /**
     * Default constructor for Knight that sets it's color
     *
     * @param color
     */
    public Knight(boolean color) {
        super(color);
    }
}
