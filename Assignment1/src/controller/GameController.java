package controller;


import model.PlayerColors;
import model.board.NormalChessBoard;
import model.player.HumanPlayer;
import model.player.Player;
import view.BoardView;

public class GameController {

    NormalChessBoard board;
    Player whitePlayer;
    Player blackPlayer;

    public BoardView getView() {
        return view;
    }

    public void setView(BoardView view) {
        this.view = view;
    }

    BoardView view;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    Player currentPlayer;




    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public NormalChessBoard getBoard() {
        return board;
    }

    public void setBoard(NormalChessBoard board) {
        this.board = board;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }


    public GameController() {
        board = new NormalChessBoard();
        whitePlayer = new HumanPlayer(PlayerColors.WHITE);
        blackPlayer = new HumanPlayer(PlayerColors.BLACK);
        currentPlayer = whitePlayer;
    }

    public Player getNextPlayer(Player player) {
        if (player.getColor() == PlayerColors.BLACK) {
            return this.getWhitePlayer();
        } else {
            return this.getBlackPlayer();
        }
    }

    public String sendMove(int startX, int startY, int endX, int endY, Player player) {
        String status = player.sendMove(startX, startY, endX, endY, board);
        if (status.equals("Success")){
            view.setPiece(startX,startY, null);
            view.setPiece(endX,endY, board.getPiece(endX,endY));
            this.currentPlayer = getNextPlayer(currentPlayer);
            view.setTurnText(currentPlayer.getColor());
        }

        postMoveChecks();

        return status;

    }

    public void postMoveChecks() {
        //@todo add check
        //@todo not working

    }

    /*
       @todo For undo I have to make a stack with information stored of the delta of the move
     */
}
