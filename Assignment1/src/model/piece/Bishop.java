package model.piece;


import model.Move;
import model.board.Board;

/**
 * Class for the Bishop Piece
 */
public class Bishop extends Piece {

    /**
     * Default constructor for Bishop that sets it's color
     *
     * @param color PlayerColors of piece desired
     */
    public Bishop(boolean color) {
        super(color);
    }

    /**
     * Validates the moves for the Bishop
     *
     * @param move Move being sent in to validate
     * @return boolean based on if move is valid for Bishop
     */
    public boolean validateMove(Move move, Board board) {
        if (Math.abs(move.getEndX() - move.getStartX()) != Math.abs(move.getEndY() - move.getStartY())) {
            return false;
        } else if (pieceInWay(move, board)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Says whether a piece is in the way of the move
     * @param move move being validated
     * @param board current board the piece is checking against
     * @return return true if a piece is in the way, false otherwise
     */
    public boolean pieceInWay(Move move, Board board) {
        int modifierX = getModifier(move.getStartX(), move.getEndX());
        int modifierY = getModifier(move.getStartY(), move.getEndY());

        int adderX = modifierX;
        int adderY = modifierY;

        while (move.getStartX() + adderX != move.getEndX()) {
            if (board.pieceIsOnBoard(move.getStartX() + adderX, move.getStartY() + adderY)) {
                return true;
            }
            adderX += modifierX;
            adderY += modifierY;
        }

        return false;
    }

    /**
     * finds the direction and difference to check up to
     *
     * @param start The starting X or Y
     * @param end   The ending X or Y
     * @return difference to add during for loop
     */
    private int getModifier(int start, int end) {
        int difference = end - start;
        if (difference < 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
