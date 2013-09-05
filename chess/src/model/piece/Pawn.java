package model.piece;

import model.Move;
import model.board.Board;
import model.player.Player;

/**
 * Class for the Pawn Piece
 */
public class Pawn extends Piece {

    /**
     * Default constructor for Pawn that sets it's color
     *
     * @param color
     */
    public Pawn(boolean color) {
        super(color);
    }

    /**
     * Validates the moves for the Pawn
     *
     * @param move
     * @return boolean based on if move is valid for Pawn
     */
    public boolean validateMove(Move move, Board board) {

        int diffY = move.getEndY() - move.getStartY();
        int diffX = move.getStartX() - move.getEndX();
        int direction;
        int startingY;

        if (this.color == Player.BLACK) {
            direction = 1;
            startingY = 1;
        } else {
            direction = -1;
            startingY = 6;
        }
        if (Math.abs(diffX) == 1 && diffY == direction) {
            return board.getPiece(move.getEndX(), move.getEndY()) != null;
        }

        if (move.getStartY() == startingY) {
            if (diffX == 0 && diffY == 2 * direction) {
                return !pieceInWay(move, board);
            } else {
                return diffX == 0 && diffY == direction;
            }
        } else {
            return diffX == 0 && diffY == direction;
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
        // needed only for init jump
        return false;
    }


}
