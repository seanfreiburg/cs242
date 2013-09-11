package model.player;

import model.Move;
import model.board.NormalChessBoard;
import model.piece.*;
import model.player.HumanPlayer;
import org.junit.Test;

import static org.junit.Assert.*;


public class PlayerTest {

    @Test
    public void testSendMove() {
        NormalChessBoard board = new NormalChessBoard();
        HumanPlayer blackPlayer = new HumanPlayer(Player.BLACK);
        HumanPlayer whitePlayer = new HumanPlayer(Player.WHITE);
        assertEquals("Success", blackPlayer.sendMove(0, 1, 0, 3, board));
        assertEquals("Error: No piece exists in that position", blackPlayer.sendMove(0, 1, 0, 3, board));

    }

    @Test
    public void testPieceIsMine() {
        NormalChessBoard board = new NormalChessBoard();
        HumanPlayer blackPlayer = new HumanPlayer(Player.BLACK);
        HumanPlayer whitePlayer = new HumanPlayer(Player.WHITE);

        assertEquals(true, blackPlayer.pieceIsMine(0, 0, board));
        assertEquals(false, blackPlayer.pieceIsMine(0, -1, board));
        assertEquals(false, blackPlayer.pieceIsMine(7, 7, board));

        assertEquals(true, whitePlayer.pieceIsMine(0, 7, board));
        assertEquals(false, whitePlayer.pieceIsMine(0, -1, board));
        assertEquals(false, whitePlayer.pieceIsMine(0, 0, board));

    }


}
