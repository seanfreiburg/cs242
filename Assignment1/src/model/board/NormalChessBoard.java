package model.board;

import model.Move;
import model.PlayerColors;
import model.Position;
import model.piece.*;
import model.player.Player;


public class NormalChessBoard extends RectangularBoard {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;

    /**
     * Default constructor for the NormalChessBoard
     */
    public NormalChessBoard() {
        this.width = WIDTH;
        this.height = HEIGHT;
        pieces = fill_board();
    }

    /**
     * Fills a pieces array with default setup for a normal chess board
     *
     * @return A pieces array filled with pieces
     */
    public Piece[][] fill_board() {

        pieces = new Piece[this.width][this.height];
        pieces[0][0] = new Rook(PlayerColors.BLACK);
        pieces[1][0] = new Knight(PlayerColors.BLACK);
        pieces[2][0] = new Bishop(PlayerColors.BLACK);
        pieces[3][0] = new King(PlayerColors.BLACK);
        pieces[4][0] = new Queen(PlayerColors.BLACK);
        pieces[5][0] = new Bishop(PlayerColors.BLACK);
        pieces[6][0] = new Knight(PlayerColors.BLACK);
        pieces[7][0] = new Rook(PlayerColors.BLACK);

        for (int i = 0; i < 8; i++) {
            pieces[i][1] = new Pawn(PlayerColors.BLACK);
            pieces[i][6] = new Pawn(PlayerColors.WHITE);
        }

        pieces[0][7] = new Rook(PlayerColors.WHITE);
        pieces[1][7] = new Knight(PlayerColors.WHITE);
        pieces[2][7] = new Bishop(PlayerColors.WHITE);
        pieces[3][7] = new King(PlayerColors.WHITE);
        pieces[4][7] = new Queen(PlayerColors.WHITE);
        pieces[5][7] = new Bishop(PlayerColors.WHITE);
        pieces[6][7] = new Knight(PlayerColors.WHITE);
        pieces[7][7] = new Rook(PlayerColors.WHITE);

        return pieces;
    }

    /**
     * Get the piece at the x y coordinate
     *
     * @param x
     * @param y
     * @return the piece at the x y coordinate. If it doesn't exist, it returns null
     */
    public Piece getPiece(int x, int y) {
        if (!coordinateOnBoard(x, y)) {
            return null;
        } else {
            return pieces[x][y];
        }
    }

    public boolean setPiece(int x, int y, Piece piece) {
        if (!coordinateOnBoard(x, y)) {
            return false;
        } else {
            pieces[x][y] = piece;
            return true;
        }
    }

    /**
     * Sees if given x,y is on board
     *
     * @param x
     * @param y
     * @return true if x,y is on board, false otherwise
     */
    public boolean coordinateOnBoard(int x, int y) {
        return x <= WIDTH - 1 && y <= HEIGHT - 1 && x >= 0 && y >= 0;
    }

    /**
     * Executes a move given
     *
     * @param move
     * @param currentPlayer
     * @return string value
     */
    public String validateAndExecuteMove(Move move, Player currentPlayer) {

        // @todo I might want to take the validations and put it a validate move method

        if (!this.moveIsOnBoard(move)) {
            return "Error: Move is not on board";
        }
        if (!this.pieceIsOnBoard(move.getStartX(), move.getStartY())) {
            return "Error: No piece exists in that position";
        }
        if (!this.pieceBelongsToPlayerToMove(move, currentPlayer)) {
            return "Error: That is the other player's piece";
        }



        Piece piece = this.getPiece(move.getStartX(), move.getStartY());
        if (!piece.validateMove(move, this)) {
            return "Error: That is an invalid move";
        }

        // if king is in check, move must put king out of check
        // we have a valid move, but we need to make sure it doesnt put king in check



        // check for another piece at position
        if (this.pieceIsOnBoard(move.getEndX(), move.getEndY())) {
            // if king raise error
            if (this.getPiece(move.getEndX(), move.getEndY()).getColor() == currentPlayer.getColor()) {
                return "Error: You can't kill your own player";
            }
            if (this.getPiece(move.getEndX(), move.getEndY()).getClass().getSimpleName().equals("King")) {
                return "Error: You cannot take the king out in one fell swoop";
            } else {
                Piece pieceToCheck = this.getPiece(move.getStartX(), move.getStartY());
                if (pieceToCheck.getClass().getSimpleName().equals("Pawn")) {
                    if (move.getEndX() - move.getStartX() == 0) {
                        return "Error: Pawns can't attack directly in front";
                    }
                }
                executeMove(move);
                return "Success";
            }
        } else {
            Position positionOfMovedPiece = executeMove(move);

            return "Success";

        }


    }


    /**
     * Checks if the King is in check
     *
     * @param player
     */
    public boolean inCheck(Player player) {
        boolean color = player.getColor();
        Position kingPosition = this.findKing(color);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Piece currentPiece = this.getPiece(x, y);
                if (currentPiece != null && currentPiece.getColor() != color ) {
                    Move move = new Move(x,y,kingPosition.getX(),kingPosition.getY());
                    if (currentPiece.validateMove(move,this)){
                        return true;
                    }
                }
            }
        }
        return false;


    }

    /**
     * Checks if the King is in checkmate
     *
     * @return True if King is in checkmate, false otherwise
     * @param player
     */
    public boolean inCheckmate(Player player) {
        // @todo implement
        return false;
    }

    /**
     * Find the king of given color
     *
     * @param color
     * @return position of that piece
     */
    public Position findKing(boolean color) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Piece currentPiece = this.getPiece(x, y);
                if (currentPiece != null && currentPiece.getColor() == color && currentPiece.getClass().getSimpleName().equals("King")) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    /**
     * Sees if a promotion should be made, if so it calls promote piece
     */
    public void checkPromotion() {
        scanLineForPromotion(0);
        scanLineForPromotion(7);
    }

    /**
     * Scans specified row for promotions
     *
     * @param row
     */
    private void scanLineForPromotion(int row) {
        for (int i = 0; i < WIDTH; i++) {
            if (this.getPiece(i, row).getClass().getSimpleName().equals("Pawn")) {
                promotePiece(i, row, this.getPiece(i, row));
            }
        }
    }



    /**
     * Promotes piece at location given
     *
     * @param x
     * @param y
     * @param oldPiece
     */
    public void promotePiece(int x, int y, Piece oldPiece) {
        this.setPiece(x, y, new Queen(oldPiece.getColor()));
    }

    /**
     * For debugging
     */
    public void printBoard() {
        System.out.print("   0__1__2__3__4__5__6__7\n");
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print(i);
            for (int j = 0; j < WIDTH; j++) {
                System.out.print("|" + getPrintRepresentation(pieces[j][i]));
            }
            System.out.print("|\n  ______________________\n");
        }
    }

    /**
     * For debugging
     */
    private String getPrintRepresentation(Piece piece) {
        String color;

        if (piece != null && piece.getColor() == PlayerColors.BLACK) {
            color = "b";
        } else {
            color = "w";
        }

        if (piece == null) {
            return "  ";
        } else if (piece.getClass().getSimpleName().equals("King")) {
            return color + "k";
        } else if (piece.getClass().getSimpleName().equals("Queen")) {
            return color + "q";
        } else if (piece.getClass().getSimpleName().equals("Bishop")) {
            return color + "b";
        } else if (piece.getClass().getSimpleName().equals("Knight")) {
            return color + "n";
        } else if (piece.getClass().getSimpleName().equals("Pawn")) {
            return color + "p";
        } else if (piece.getClass().getSimpleName().equals("Rook")) {
            return color + "r";
        } else {
            return "  ";
        }
    }

}
