package model.piece;

import model.Move;
import model.board.Board;

/**
 * Created with IntelliJ IDEA.
 * User: seanfreiburg
 * Date: 9/11/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Viking extends Piece {
    /**
     * Default constructor for Viking that sets it's color
     *
     * @param color
     */
    public Viking(boolean color) {
        super(color);
    }

    /**
     * Validates the moves for the Viking
     *
     * @param move
     * @return boolean based on if move is valid for Viking
     */
    public boolean validateMove(Move move, Board board) {

        return true;

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
