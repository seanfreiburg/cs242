package model.piece;


import model.Move;
import model.board.Board;

/**
 * Class for the Queen Piece
 */
public class Queen extends Piece {

    /**
     * Default constructor for Queen that sets it's color
     *
     * @param color
     */
    public Queen(boolean color) {
        super(color);
    }

    /**
     * Validates the moves for the Queen
     *
     * @param move
     * @return boolean based on if move is valid for Queen
     */
    @Override
    public boolean validateMove(Move move, Board board) {
        int diffX = move.getEndX() - move.getStartX();
        int diffY = move.getEndY() - move.getStartY();
        if (diffX == 0 || diffY == 0) {
            if (!pieceInWayStraight(move, board)) {
                return true;
            } else {
                return false;
            }
        } else {
            if (Math.abs(diffX) != Math.abs(diffY)) {
                return false;
            } else if (pieceInWayDiagonal(move, board)) {
                return false;
            } else {
                return true;
            }
        }
    }


    /**
     * Not actually used, just had to be implemented
     *
     * @param move
     * @param board
     * @return return true if a piece is in the way, false otherwise
     */
    protected boolean pieceInWay(Move move, Board board) {
        return false;
    }

    /**
     * Says whether a piece is in the way of the move
     *
     * @param move
     * @param board
     * @return return true if a piece is in the way, false otherwise
     */
    protected boolean pieceInWayDiagonal(Move move, Board board) {
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
     * @param start
     * @param end
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

    /**
     * Says whether a piece is in the way of the move
     * This uses Rook code, if bug, use both
     *
     * @param move
     * @param board
     * @return return true if a piece is in the way, false otherwise
     */
    protected boolean pieceInWayStraight(Move move, Board board) {
        if (move.getStartX() - move.getEndX() == 0) {
            // it is moving vertically
            int diff;
            if (move.getStartY() - move.getEndY() < 0) {
                return movingDownCheck(move, board);

            } else {
                return movingUpCheck(move, board);

            }
        } else if (move.getStartY() - move.getEndY() == 0) {
            // it is moving horizontally
            int diff;
            if (move.getStartX() - move.getEndX() < 0) {
                return movingRightCheck(move, board);
            } else {
                return movingLeftCheck(move, board);
            }

        } else {
            return true;
        }
    }

    /**
     * Checks rook moving left
     *
     * @param move
     * @param board
     * @return true if move fails, false otherwise
     */
    private boolean movingLeftCheck(Move move, Board board) {
        int diff;
        diff = -1;
        while (move.getStartX() + diff > move.getEndX()) {
            if (board.pieceIsOnBoard(move.getStartX() + diff, move.getStartY())) {
                return true;
            }
            diff += -1;
        }

        return false;
    }

    /**
     * Checks rook moving right
     *
     * @param move
     * @param board
     * @return true if move fails, false otherwise
     */
    private boolean movingRightCheck(Move move, Board board) {
        int diff;
        diff = 1;
        while (move.getStartX() + diff < move.getEndX()) {
            if (board.pieceIsOnBoard(move.getStartX() + diff, move.getStartY())) {
                return true;
            }
            diff += 1;

        }


        return false;

    }

    /**
     * Checks rook moving up
     *
     * @param move
     * @param board
     * @return true if move fails, false otherwise
     */
    private boolean movingUpCheck(Move move, Board board) {
        int diff;
        diff = -1;
        while (move.getStartY() + diff > move.getEndY()) {
            if (board.pieceIsOnBoard(move.getStartX(), move.getStartY() + diff)) {
                return true;
            }
            diff += -1;

        }

        return false;

    }

    /**
     * Checks rook moving down
     *
     * @param move
     * @param board
     * @return true if move fails, false otherwise
     */
    private boolean movingDownCheck(Move move, Board board) {
        int diff;
        diff = 1;
        while (move.getStartY() + diff < move.getEndY()) {
            if (board.pieceIsOnBoard(move.getStartX(), move.getStartY() + diff)) {
                return true;
            }
            diff += 1;

        }

        return false;

    }


}
