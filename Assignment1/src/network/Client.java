package network;

import controller.GameController;
import controller.OnlineGameController;
import model.player.HumanPlayer;
import model.player.Player;
import view.BoardView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:27 PM
 */
public class Client {
    final static String base_url = "http://localhost:8000/";

    public static void main(String[] args) throws IOException {

        Client t = new Client();


    }

    public Client(){
        OnlineGameController game = new OnlineGameController();
        BoardView view = new BoardView("Chess", game);
        game.setView(view);
        game.setCurrentPlayer(setPlayerFromNetwork());
    }

    public Player setPlayerFromNetwork(){
        String status = "Error";
        try {
            URL localhost = new URL(base_url + "get_color");
            URLConnection lc = localhost.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            lc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                status = inputLine;
            }
            in.close();
        } catch (Exception e) {
            //this is for garbage errors intellij was giving me
        }
        boolean color = false;
        if (status.equals("w")){
            color = true;
        }
        else if (status.equals("b")){
            color = false;
        }
        else{
            System.exit(1);
        }

        return new HumanPlayer(color);

    }
}
