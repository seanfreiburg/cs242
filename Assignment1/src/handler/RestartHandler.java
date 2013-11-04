package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.GameController;

import java.io.IOException;
import java.io.OutputStream;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 9:04 PM
 */
public class RestartHandler implements HttpHandler {


    GameController game;

    public RestartHandler(GameController game) {
        this.game = game;
    }

    public void handle(HttpExchange t) throws IOException {
        final OutputStream os;
        String response = new String("Success");
        t.getRequestHeaders();
        t.sendResponseHeaders(200, response.length());
        t.getRequestBody();
        t.getResponseBody();
        game.restart();

        os = t.getResponseBody();

        os.write(response.getBytes());

        os.close();
        t.close();
        System.out.println("forfeit server");
    }
}
