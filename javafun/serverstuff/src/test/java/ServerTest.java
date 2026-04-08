
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.time.Instant;

public class ServerTest {

    /**
     * Question from Stack Overflow:
     * <a href="https://stackoverflow.com/questions/56454813/timeouts-in-java-net-http-httpclient-not-working-as-expected">
     * Java Http client with 100ms timeout throws Timeout exception when connecting to an API
     * </a>.
     *
     * <p>Scenario: an API appears to respond in under 10ms via
     * {@code curl --max-time 0.01 http://localhost:8000/}, but Java {@code HttpClient}
     * intermittently throws {@code HttpTimeoutException} at a 100ms timeout.</p>
     *
     * <p>Original sample from the question:</p>
     * <pre>{@code
     * @Test
     * public void testHTTPClient() throws URISyntaxException, IOException, InterruptedException {
     *     HttpClient client = HttpClient.newBuilder().build().newHttpClient();
     *     java.net.URI uri = new URI("http://localhost:8000/");
     *     HttpRequest req = HttpRequest.newBuilder(uri).GET().timeout(Duration.ofMillis(100)).build();
     *     long start = Instant.now().toEpochMilli();
     *     try {
     *         java.net.http.HttpResponse<String> response =
     *                 client.send(req, java.net.http.HttpResponse.BodyHandlers.ofString());
     *         System.out.println(response.body());
     *     } catch (HttpTimeoutException e) {
     *         System.out.println("Time taken " + (Instant.now().toEpochMilli() - start));
     *         e.printStackTrace();
     *     }
     * }</pre>
     *
     * <p>Observed timeout output:</p>
     * <pre>{@code
     * Time taken 159
     * java.net.http.HttpTimeoutException: request timed out
     *     at java.net.http/jdk.internal.net.http.HttpClientImpl.send(HttpClientImpl.java:559)
     *     at java.net.http/jdk.internal.net.http.HttpClientFacade.send(HttpClientFacade.java:119)
     * }</pre>
     *
     * <p>Behavior noted in the question: with repeated runs at 100ms, some requests succeed,
     * and with 150ms, timeouts no longer appear.</p>
     */
    @Test
    void httpClientRespondsSoon() throws IOException, InterruptedException, URISyntaxException {
        for (int wait = 100; wait >= 0; wait-=5) {
            try (HttpClient client = HttpClient.newBuilder().build().newHttpClient()) {
                URI uri = new URI("http://localhost:8000/");
                HttpRequest req = HttpRequest.newBuilder(uri).GET().timeout(Duration.ofMillis(Math.max(1, wait))).build();
                long start = Instant.now().toEpochMilli();
                try {
                    java.net.http.HttpResponse<String> response = client.send(req, java.net.http.HttpResponse.BodyHandlers.ofString());
                    long elapsed = Instant.now().toEpochMilli() - start;
                    System.out.printf("wait: %d, %s (%s)%n", wait, response.body(), elapsed);
                } catch (HttpTimeoutException e) {
                    System.out.println("Time taken " + (Instant.now().toEpochMilli() - start));
                    e.printStackTrace();
                }
            }
        }
    }

}