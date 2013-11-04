package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.GameController;
import model.player.HumanPlayer;
import model.player.Player;

import java.io.IOException;
import java.io.OutputStream;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:19 PM
 */
public class GetColorHandler implements HttpHandler {
    GameController game;
    HumanPlayer blackPlayer;
    HumanPlayer whitePlayer;

    public GetColorHandler(GameController game) {
        this.game = game;
    }

    public void handle(HttpExchange t) throws IOException {
        final OutputStream os;
        boolean player_color = true;
        boolean turn = true;
        String response;


        if (whitePlayer == null) {
            whitePlayer = new HumanPlayer(Player.WHITE);
            response = "w";

        } else if (blackPlayer == null){
            blackPlayer = new HumanPlayer(Player.BLACK);
            response = "b";
        }
        else {
            response = "e";
        }

        t.getRequestHeaders();
        t.sendResponseHeaders(200, response.length());
        t.getRequestBody();
        t.getResponseBody();

        os = t.getResponseBody();

        os.write(response.getBytes());

        os.close();
        t.close();
        System.out.println(response);
    }

}
