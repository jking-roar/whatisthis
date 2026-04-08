import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {
    private static final int PORT = 8000;

    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.stop(0);
                System.out.println("Server stopped");
            } catch (Exception ignored) {
                // JVM is exiting; ignore shutdown races.
            }
        }, "server-shutdown-hook"));
    }

    public static HttpServer startServer() throws IOException {
        return startServer(PORT);
    }

    public static HttpServer startServer(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", port), 0);

        // Create context for root path
        server.createContext("/", exchange -> {
            byte[] response = "Hello from server!".getBytes();
            exchange.getResponseHeaders().set("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        });

        server.setExecutor(null); // Default executor
        server.start();

        System.out.println("Server started on http://localhost:" + server.getAddress().getPort());
        return server;
    }
}
