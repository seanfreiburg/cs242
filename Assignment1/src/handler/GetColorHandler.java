package handler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * User: seanfreiburg
 * Date: 10/30/13
 * Time: 8:19 PM
 */
public class GetColorHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            final OutputStream os;
            boolean player_color = true;
            boolean turn = true;
            String response;
            if (player_color == turn){
                response = new String("y");
            }
            else{
                response = new String("n");
            }

            t.getRequestHeaders();
            t.sendResponseHeaders(200, response.length());
            t.getRequestBody();
            t.getResponseBody();

            os = t.getResponseBody();

            os.write(response.getBytes());

            os.close();
            t.close();
        }

}
