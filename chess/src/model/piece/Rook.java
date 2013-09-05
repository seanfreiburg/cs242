package model.piece;


import model.Move;
import model.board.Board;

/**
 * Class for the Rook Piece
 */
public class Rook extends Piece {

    /**
     * Default constructor for Rook that sets it's color
     *
     * @param color
     */
    public Rook(boolean color) {
        super(color);
    }

    /**
     * Validates the moves for the Rook
     *
     * @param move
     * @return boolean based on if move is valid for Rook
     */
    public boolean validateMove(Move move, Board board) {
        int startEndXDiff = move.getStartX() - move.getEndX();
        int startEndYDiff = move.getStartY() - move.getEndY();
        if ((startEndXDiff == 0 || startEndYDiff == 0) && (!pieceInWay(move, board))) {
            return true;
        } else {
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
