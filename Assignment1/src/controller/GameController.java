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
    Player currentPlayer;
    BoardView view;


    public BoardView getView() {
        return view;
    }

    public void setView(BoardView view) {
        this.view = view;
    }



    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


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

    /**
     * Sends the move to the board and responds to view with moves to make
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @param player
     * @return
     */
    public String sendMove(int startX, int startY, int endX, int endY, Player player) {
        String status = player.sendMove(startX, startY, endX, endY, board);
        if (status.equals("Success")){
            view.setPiece(startX,startY, null);
            view.setPiece(endX,endY, board.getPiece(endX,endY));
            currentPlayer = getNextPlayer(currentPlayer);

            view.setTurnText(currentPlayer.getColor());
        }

        postMoveChecks();


        return status;

    }

    public void postMoveChecks() {
        //@todo add check, checkmate
        //@todo not working

    }


    /**
     * I restart the game and add 1 to the other players score and update the view
     */
    public void forfeit(){
        this.currentPlayer = getNextPlayer(currentPlayer);
        currentPlayer.setScore( 1+ currentPlayer.getScore());
        view.setScoreLabel(blackPlayer.getScore(), whitePlayer.getScore());

        restart();

    }

    /**
     * I restart the game and update the view
     */
    public void restart(){
        board = new NormalChessBoard();
        for (int y =0; y <board.getHeight(); y++){
            for (int x= 0; x < board.getWidth(); x++){
                view.setPiece(x,y,board.getPiece(x,y));
            }
        }
        this.currentPlayer = whitePlayer;
    }

    /*
       @todo For undo I have to make a stack with information stored of the delta of the move
     */
}
