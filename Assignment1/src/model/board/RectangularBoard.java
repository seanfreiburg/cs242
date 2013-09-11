package model.board;

import model.Move;

public abstract class RectangularBoard extends Board {

    /**
     * @param move
     * @return boolean, True if move is on the board, false otherwise
     */
    public boolean moveIsOnBoard(Move move) {
        return !(move.getEndX() > width - 1 || move.getEndY() > width - 1 || move.getEndX() < 0 || move.getEndY() < 0);
    }
}
