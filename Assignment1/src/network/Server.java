package network;

import com.sun.net.httpserver.HttpServer;
import controller.GameController;

import java.io.IOException;
import java.net.InetSocketAddress;
import handler.*;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:27 PM
 */
public class Server {

    public static void main(String[] args) throws IOException {

        GameController game = new GameController();
        InetSocketAddress addr  = new InetSocketAddress("localhost", 8000);
        HttpServer server = HttpServer.create(addr, 10);
        server.createContext("/get_color", new GetColorHandler(game));
        server.createContext("/send_move", new SendMoveHandler(game));
        server.createContext("/forfeit", new ForfeitHandler(game));
        server.createContext("/restart", new RestartHandler(game));
        server.start();

    }
}
