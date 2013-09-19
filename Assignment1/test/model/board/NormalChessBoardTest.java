package model.board;

import model.Move;
import model.PlayerColors;
import model.Position;
import model.board.NormalChessBoard;
import model.piece.*;
import model.player.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class NormalChessBoardTest {


    @Test
    public void testMoveIsValidWithValidMove() {
        NormalChessBoard board = new NormalChessBoard();
        Move move = new Move(0, 0, 1, 1);
        assertEquals(board.moveIsOnBoard(move), true);
    }

    @Test
    public void testMoveIsValidWithInValidMoveGreaterThanWidth() {
        NormalChessBoard board = new NormalChessBoard();
        Move move = new Move(0, 0, 8, 1);
        assertEquals(board.moveIsOnBoard(move), false);
    }

    @Test
    public void testMoveIsValidWithInValidMoveLessThanWidth() {
        NormalChessBoard board = new NormalChessBoard();
        Move move = new Move(0, 0, -1, 1);
        assertEquals(board.moveIsOnBoard(move), false);
    }

    @Test
    public void testMoveIsValidWithInValidMoveGreaterThanHeight() {
        NormalChessBoard board = new NormalChessBoard();
        Move move = new Move(0, 0, 1, 9);
        assertEquals(board.moveIsOnBoard(move), false);
    }

    @Test
    public void testMoveIsValidWithInValidMoveLessThanHeight() {
        NormalChessBoard board = new NormalChessBoard();
        Move move = new Move(0, 0, 1, -2);
        assertEquals(board.moveIsOnBoard(move), false);
    }

    @Test
    public void testConstructor() {
        NormalChessBoard board = new NormalChessBoard();
        assertEquals(board.getPiece(8, 8), null);
        assertEquals(board.getPiece(8, -1), null);
        assertEquals(board.getPiece(-1, 3), null);
        assertEquals(board.getPiece(0, 0).getClass(), new Rook(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(1, 0).getClass(), new Knight(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(2, 0).getClass(), new Bishop(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(4, 0).getClass(), new Queen(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(3, 0).getClass(), new King(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(5, 0).getClass(), new Bishop(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(6, 0).getClass(), new Knight(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(7, 0).getClass(), new Rook(PlayerColors.BLACK).getClass());
        assertEquals(board.getPiece(0, 1).getClass(), new Pawn(PlayerColors.BLACK).getClass());

        assertEquals(board.getPiece(0, 6).getClass(), new Pawn(PlayerColors.WHITE).getClass());
        assertEquals(board.getPiece(7, 7).getClass(), new Rook(PlayerColors.WHITE).getClass());

    }

    @Test
    public void testPieceBelongsToPlayer() {
        NormalChessBoard board = new NormalChessBoard();
        HumanPlayer player1 = new HumanPlayer(PlayerColors.BLACK);
        HumanPlayer player2 = new HumanPlayer(PlayerColors.WHITE);
        assertEquals(board.pieceBelongsToPlayerToMove(new Move(0, 0, 1, 1), player1), true);

        assertEquals(board.pieceBelongsToPlayerToMove(new Move(0, 0, 1, 1), player2), false);
        assertEquals(board.pieceBelongsToPlayerToMove(new Move(0, 1, 1, 1), player1), true);
        assertEquals(board.pieceBelongsToPlayerToMove(new Move(1, 0, 1, 1), player1), true);
        assertEquals(board.pieceBelongsToPlayerToMove(new Move(0, 7, 1, 1), player2), true);


    }

    @Test
    public void testBoardMoves() {
        NormalChessBoard board = new NormalChessBoard();
        HumanPlayer blackPlayer = new HumanPlayer(PlayerColors.BLACK);
        HumanPlayer whitePlayer = new HumanPlayer(PlayerColors.WHITE);

        //Rook and error testing
        assertEquals("Error: Move is not on board", board.validateAndExecuteMove(new Move(99, 99, -7, -8), blackPlayer));
        assertEquals("Error: No piece exists in that position", board.validateAndExecuteMove(new Move(4, 4, 4, 5), blackPlayer));
        assertEquals("Error: That is the other player's piece", board.validateAndExecuteMove(new Move(7, 7, 4, 5), blackPlayer));
        assertEquals("Error: You can't kill your own player", board.validateAndExecuteMove(new Move(0, 0, 0, 1), blackPlayer));
        board.removePiece(0, 1);
        assertEquals("Success", board.validateAndExecuteMove(new Move(0, 0, 0, 1), blackPlayer));
        assertEquals(board.getPiece(0, 1).getClass(), new Rook(PlayerColors.BLACK).getClass());
        assertEquals("Success", board.validateAndExecuteMove(new Move(0, 1, 0, 6), blackPlayer));


        assertEquals(board.getPiece(0, 7).getClass(), new Rook(PlayerColors.BLACK).getClass());

        //pawn testing
        assertEquals("Success", board.validateAndExecuteMove(new Move(1, 1, 1, 3), blackPlayer));
        assertEquals(board.getPiece(1, 3).getClass(), new Pawn(PlayerColors.BLACK).getClass());


        assertEquals("Success", board.validateAndExecuteMove(new Move(2, 1, 2, 2), blackPlayer));
        assertEquals(board.getPiece(2, 2).getClass(), new Pawn(PlayerColors.BLACK).getClass());

        assertEquals("Success", board.validateAndExecuteMove(new Move(2, 2, 2, 3), blackPlayer));
        assertEquals(board.getPiece(2, 3).getClass(), new Pawn(PlayerColors.BLACK).getClass());

        assertEquals("Success", board.validateAndExecuteMove(new Move(4, 6, 4, 4), whitePlayer));
        assertEquals(board.getPiece(4, 4).getClass(), new Pawn(PlayerColors.WHITE).getClass());

        //king testing
        board.removePiece(3, 1);
        assertEquals("Success", board.validateAndExecuteMove(new Move(3, 0, 3, 1), blackPlayer));
        assertEquals(board.getPiece(3, 1).getClass(), new King(PlayerColors.BLACK).getClass());

        assertEquals("Error: You can't kill your own player", board.validateAndExecuteMove(new Move(3, 1, 4, 1), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(3, 1, 4, 2), blackPlayer));


        //bishop testing
        assertEquals("Success", board.validateAndExecuteMove(new Move(2, 0, 3, 1), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(3, 1, 2, 0), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(2, 0, 0, 2), blackPlayer));
        assertEquals("Error: You can't kill your own player", board.validateAndExecuteMove(new Move(0, 2, 1, 3), blackPlayer));
        board.setPiece(2, 5, new Bishop(PlayerColors.BLACK));
        assertEquals("Success", board.validateAndExecuteMove(new Move(2, 5, 3, 6), blackPlayer));

        assertEquals("Error: Move is not on board", board.validateAndExecuteMove(new Move(-1, 6, -2, 2), blackPlayer));


        assertEquals("Success", board.validateAndExecuteMove(new Move(1, 0, 3, 1), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(3, 1, 4, 3), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(4, 3, 5, 5), blackPlayer));
        assertEquals("Error: Move is not on board", board.validateAndExecuteMove(new Move(5, 5, -1, 5), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(5, 5, 6, 7), blackPlayer));

        //pawn attack from front
        board.setPiece(1, 5, new Pawn(PlayerColors.BLACK));
        assertEquals("Error: Pawns can't attack directly in front", board.validateAndExecuteMove(new Move(1, 5, 1, 6), blackPlayer));
        //pawn attack from corner
        assertEquals("Success", board.validateAndExecuteMove(new Move(1, 5, 2, 6), blackPlayer));

        // queen testing
        assertEquals("Success", board.validateAndExecuteMove(new Move(4, 0, 0, 0), blackPlayer));
        assertEquals("Error: That is an invalid move", board.validateAndExecuteMove(new Move(0, 0, 0, 4), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(0, 0, 3, 3), blackPlayer));
        assertEquals("Success", board.validateAndExecuteMove(new Move(3, 3, 4, 4), blackPlayer));



    }

    @Test
    public void testFindKing() {

        NormalChessBoard board = new NormalChessBoard();
        HumanPlayer blackPlayer = new HumanPlayer(PlayerColors.BLACK);
        HumanPlayer whitePlayer = new HumanPlayer(PlayerColors.WHITE);

        assertEquals(true, new Position(3, 0).equals(board.findKing(PlayerColors.BLACK)));
        assertEquals(true, new Position(3, 7).equals(board.findKing(PlayerColors.WHITE)));
        assertEquals(false, new Position(3, 2).equals(board.findKing(PlayerColors.BLACK)));

    }


}

