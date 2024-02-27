import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class UrlStatusCheckerTest {

    @Test
    void testReachableUrl() throws URISyntaxException {
        // Arrange
        String reachableUrl = "https://www.google.com"; // Replace with a valid URL
        UrlStatusChecker checker = new UrlStatusChecker();

        // Act
        boolean isReachable;
        try {
            isReachable = checker.isReachable(reachableUrl);
        } catch (IOException e) {
            fail("Exception occurred while checking reachable URL: " + e.getMessage());
            return;
        }

        // Assert
        assertTrue(isReachable, "Expected URL to be reachable");
    }

    @Test
    void testUnreachableUrl() throws URISyntaxException {
        // Arrange
        String unreachableUrl = "https://www.google.com/404"; // Replace with an invalid URL
        UrlStatusChecker checker = new UrlStatusChecker();

        // Act
        boolean isReachable;
        try {
            isReachable = checker.isReachable(unreachableUrl);
        } catch (IOException e) {
            fail("Exception occurred while checking unreachable URL: " + e.getMessage());
            return;
        }

        // Assert
        assertFalse(isReachable, "Expected URL to be unreachable");
    }

    @Test
    void testWrongUrlFormat() {
        // Arrange
        String wrongURLFormat = "www.google.com/404"; // Replace with an invalid URL
        UrlStatusChecker checker = new UrlStatusChecker();

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> checker.isReachable(wrongURLFormat));
        assertEquals("URI is not absolute", exception.getMessage());

    }
}
