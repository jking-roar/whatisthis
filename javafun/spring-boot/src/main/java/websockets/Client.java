package websockets;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Client {
    public static void main(String[] args)
            throws IOException, ExecutionException, InterruptedException, DumbException {
        URI uri = URI.create("ws://localhost:8080/events/");
        WebSocketClient client = new WebSocketClient();
        try {
            client.start();
        } catch (Exception e) {
            throw new DumbException("bang bang what the hang?", e);
        }

        Future<Session> connection = client.connect(new EventSocket(), uri);
        Session session = connection.get();
        session.getRemote().sendString("hello");
        session.close();


    }

    private static class DumbException extends Exception {
        DumbException(String msg, Exception cause) {
            super(msg, cause);
        }
    }
}
