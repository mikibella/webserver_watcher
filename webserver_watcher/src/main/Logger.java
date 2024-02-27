import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Logger class for writing log entries and generating log strings.
 */
public class Logger {

    private static final String DATE_FORMAT = "dd-MMM-yyyy HH:mm:ss 'Uhr :'";

    /**
     * Writes a string to a log file.
     *
     * @param fileName      The name of the log file.
     * @param contentString The string to be written to the log file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeStringToFile(String fileName, String contentString) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(contentString);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    /**
     * Generates a log string based on the provided URL and reachability status.
     *
     * @param url     The URL being monitored.
     * @param reached Indicates whether the URL is reachable.
     * @return A formatted log string containing timestamp, URL, and reachability
     *         status.
     */
    public String generateString(String url, boolean reached) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Berlin"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String status = reached ? "erreichbar!" : "nicht erreichtbar!";
        return now.format(formatter) + " " + url + " -> " + status;
    }
}
