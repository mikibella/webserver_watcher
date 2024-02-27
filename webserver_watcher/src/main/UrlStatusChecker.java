import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * UrlStatusChecker class for url checking functions.
 */
public class UrlStatusChecker {

    /**
     * Checks if the specified URL is reachable.
     *
     * @param urlString The URL to check for reachability.
     * @return {@code true} if the URL is reachable (returns a status code less than
     *         400), {@code false} otherwise.
     * @throws IOException        If an I/O error occurs while connecting to the
     *                            URL.
     * @throws URISyntaxException If the provided URL string is not a valid URI.
     */
    public boolean isReachable(String urlString) throws IOException, URISyntaxException {

        URL url = new URI(urlString).toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int statusCode = connection.getResponseCode();

        connection.disconnect();

        return statusCode < 400;
    }
}
