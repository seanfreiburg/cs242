package model.piece;

import model.PlayerColors;
import model.Move;
import model.board.NormalChessBoard;
import model.player.HumanPlayer;
import model.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: seanfreiburg
 * Date: 9/11/13
 * Time: 2:10 PM
 */
public class VikingTest {

    @Test
    public void testPawnValidateMove() {

        NormalChessBoard board = new NormalChessBoard();
        board.setPiece(0, 3, new Viking(PlayerColors.BLACK));
        Player whitePlayer = new HumanPlayer(PlayerColors.WHITE);
        Player blackPlayer = new HumanPlayer(PlayerColors.BLACK);
        assertEquals(true, board.getPiece(0, 3).validateMove(new Move(0, 3, 0, 4), board));

    }
}
