import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    @Test
    void testGenerateStringReached() {
        // Arrange
        String url = "https://example.com";
        boolean reached = true;

        // Create an instance of your class
        Logger generator = new Logger();

        // Act
        String result = generator.generateString(url, reached);

        // Assert
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss 'Uhr :'");
        String expected = now.format(formatter) + " " + url + " -> erreichbar!";
        assertEquals(expected, result);
    }

    @Test
    void testGenerateStringNotReached() {
        // Arrange
        String url = "https://example.com";
        boolean reached = false;

        // Create an instance of your class
        Logger generator = new Logger();

        // Act
        String result = generator.generateString(url, reached);

        // Assert
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss 'Uhr :'");
        String expected = now.format(formatter) + " " + url + " -> nicht erreichtbar!";
        assertEquals(expected, result);
    }
}
