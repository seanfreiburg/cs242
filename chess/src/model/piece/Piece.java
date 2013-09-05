package model.piece;

import model.Move;
import model.board.Board;
import model.player.Player;

/**
 * Generic abstract class of chess pieces
 */
public abstract class Piece {

    protected boolean color;

    /**
     * Gets the color of the piece
     *
     * @return color of the piece
     */
    public boolean getColor() {
        return this.color;
    }

    /**
     * Sets the color of the piece
     *
     * @param color
     */
    public Piece(boolean color) {
        this.color = color;
    }

    /**
     * Default constructor that sets color to null
     */
    public Piece() {
        this.color = false;
    }

    /**
     * Validates moves for the pieces
     *
     * @param move
     * @return boolean based on if the move is valid
     */
    public abstract boolean validateMove(Move move, Board board);

    /**
     * Says whether a piece is in the way of the move
     *
     * @param move
     * @param board
     * @return return true if a piece is in the way, false otherwise
     */
    protected abstract boolean pieceInWay(Move move, Board board);


}
