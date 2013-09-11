package model.piece;


import model.board.NormalChessBoard;
import model.player.HumanPlayer;
import model.player.Player;
import org.junit.Test;

import static org.junit.Assert.*;

import model.Move;

public class PieceTest {

    @Test
    public void testPawnValidateMove() {

        NormalChessBoard board = new NormalChessBoard();
        board.setPiece(0, 3, new Pawn(Player.BLACK));
        Player whitePlayer = new HumanPlayer(Player.WHITE);
        Player blackPlayer = new HumanPlayer(Player.BLACK);
        assertEquals(true, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 4), board));
        assertEquals(false, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 5), board));
        board.setPiece(0, 3, new Pawn(Player.WHITE));
        assertEquals(true, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 2), board));
        assertEquals(false, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 5), board));

        board.setPiece(1, 3, new Pawn(Player.BLACK));
        assertEquals(false, board.getPiece(1, 3).validateMove(new Move(1, 3, 0, 2), board));
        assertEquals(false, board.getPiece(0, 3).validateMove(new Move(0, 3, 1, 4), board));
        assertEquals(false, board.getPiece(0, 3).validateMove(new Move(-1, 3, -1, -1), board));

        assertEquals(true, board.getPiece(0, 1).validateMove(new Move(0, 1, 0, 3), board));
        board.setPiece(0, 2, new Pawn(Player.WHITE));
        assertEquals(false, board.getPiece(0, 2).validateMove(new Move(0, 2, 0, 4), board));
        board.setPiece(0, 7, new Pawn(Player.WHITE));
        assertEquals(true, board.getPiece(0, 6).validateMove(new Move(0, 6, 0, 4), board));
        assertEquals(false, board.getPiece(0, 7).validateMove(new Move(0, 7, -1, 5), board));

    }

    @Test
    public void testKnightValidateMove() {
        NormalChessBoard board = new NormalChessBoard();
        board.setPiece(0, 3, new Knight(Player.BLACK));
        Player whitePlayer = new HumanPlayer(Player.WHITE);
        Player blackPlayer = new HumanPlayer(Player.BLACK);
        assertEquals(true, board.getPiece(1, 0).validateMove(new Move(1, 0, 2, 2), board));
        assertEquals(true, board.getPiece(1, 0).validateMove(new Move(1, 0, 0, 2), board));
        assertEquals(false, board.getPiece(1, 0).validateMove(new Move(1, 0, 0, 3), board));

    }

    @Test
    public void testQueenValidateMove() {
        // @todo need to write tests haha
    }

    @Test
    public void testKingValidateMove() {

        // @todo need to write tests haha
    }

    @Test
    public void testRookValidateMove() {
        NormalChessBoard board = new NormalChessBoard();
        board.setPiece(0, 3, new Rook(Player.BLACK));
        Player whitePlayer = new HumanPlayer(Player.WHITE);
        Player blackPlayer = new HumanPlayer(Player.BLACK);
        assertEquals(true, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 5), board));
        assertEquals(false, board.getPiece(0, 3).validateMove(new Move(0, 3, 1, 5), board));
        assertEquals(true, board.getPiece(0, 3).validateMove(new Move(0, 3, 2, 3), board));
        assertEquals(false, board.getPiece(0, 0).validateMove(new Move(0, 0, 4, 0), board));
        assertEquals(false, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 7), board));
        assertEquals(true, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 6), board));

    }

    @Test
    public void testBishopValidateMove() {
        NormalChessBoard board = new NormalChessBoard();
        Player whitePlayer = new HumanPlayer(Player.WHITE);
        Player blackPlayer = new HumanPlayer(Player.BLACK);
        board.removePiece(3, 1);

        assertEquals(true, board.getPiece(2, 0).validateMove(new Move(2, 0, 3, 1), board));

        assertEquals(true, board.getPiece(2, 0).validateMove(new Move(2, 0, 6, 4), board));
        board.setPiece(3, 2, new Bishop(Player.BLACK));
        assertEquals(false, board.getPiece(3, 2).validateMove(new Move(3, 2, 1, 0), board));
        assertEquals(true, board.getPiece(3, 2).validateMove(new Move(3, 2, 4, 3), board));
        assertEquals(false, board.getPiece(3, 2).validateMove(new Move(3, 2, 4, 2), board));
    }


}
