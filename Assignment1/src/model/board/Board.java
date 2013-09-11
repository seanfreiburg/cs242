package model.board;

import model.Move;
import model.Position;
import model.piece.Piece;
import model.player.*;


public abstract class Board {



    protected int width;
    protected int height;
    protected Piece[][] pieces;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract boolean moveIsOnBoard(Move move);

    protected abstract Piece[][] fill_board();

    public abstract Piece getPiece(int x, int y);

    public abstract boolean setPiece(int x, int y, Piece piece);

    public abstract String validateAndExecuteMove(Move move, Player player);

    /**
     * Checks if piece belongs to player that is specified to move
     *
     * @param move
     * @param player
     * @return boolean, true if the piece at the start of move belongs to player, false otherwise
     */
    protected boolean pieceBelongsToPlayerToMove(Move move, Player player) {
        Piece piece = getPiece(move.getStartX(), move.getStartY());
        return piece.getColor() == player.getColor();
    }

    /**
     * Checks if landing position of the move belongs to the player
     *
     * @param move
     * @param player
     * @return boolean, true if the piece at the end of move belongs to player, false otherwise
     */
    protected boolean pieceBelongsToPlayerToReplace(Move move, Player player) {
        Piece piece = getPiece(move.getEndX(), move.getEndY());
        return piece.getColor() == player.getColor();
    }

    /**
     * Checks if the piece specified at start of move is on the board
     *
     * @param x
     * @param y
     * @return boolean, true if piece is on board, false otherwise
     */
    public boolean pieceIsOnBoard(int x, int y) {
        return this.getPiece(x, y) != null;
    }

    /**
     * Executes the move by moving the piece object
     * @param move
     * @return Coordinates of location piece moved to
     */
    protected Position executeMove(Move move) {
        // I need to check if this move put the king in check

        Piece pieceToBeMoved = this.getPiece(move.getStartX(), move.getStartY());
        pieces[move.getEndX()][move.getEndY()] = pieceToBeMoved;
        this.setPiece(move.getStartX(), move.getStartY(), null);
        return new Position(move.getEndX(),move.getEndY());
    }

    public void removePiece(int x, int y) {
        if (this.pieceIsOnBoard(x, y)) {
            pieces[x][y] = null;
        }
    }

    /**
     * Sees if given x,y is on board
     *
     * @param x
     * @param y
     * @return true if x,y is on board, false otherwise
     */
    public abstract boolean coordinateOnBoard(int x, int y);

    /**
     * Checks if the King is in check
     *
     * @return True if King is in check, false otherwise
     * @param player
     */
    public abstract boolean inCheck(Player player);

    /**
     * Checks if the King is in checkmate
     *
     * @return True if King is in checkmate, false otherwise
     * @param player
     */
    public abstract boolean inCheckmate(Player player);


}
