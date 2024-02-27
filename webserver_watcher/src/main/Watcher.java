import java.io.IOException;
import java.util.Scanner;

/**
 * Watcher class for monitoring the reachability of a specified URL.
 */
public class Watcher {
    /**
     * Main method to start the URL monitoring process. Writes to output_log.txt in
     * current directory.
     */
    public static void main(String[] args) throws Exception {
        UrlStatusChecker checker = new UrlStatusChecker();
        Logger fileWriter = new Logger();
        String fileName = "output_log.txt";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bitte geben Sie die zu überwachende URL ein:");
        String inputUrl = scanner.nextLine();

        if (inputUrl.isEmpty()) {
            System.err.println("Bitte eine URL eingeben!");
            return;
        }

        System.out.println(
                inputUrl
                        + " wird überwacht. Drücken Sie die ENTER-Taste, um das Programm zu beenden...");

        // Start thread for listening to user input
        Thread inputListenerThread = new Thread(() -> {
            scanner.nextLine();
            scanner.close();
            System.exit(0);
        });
        inputListenerThread.start();

        while (true) {
            String content = "";

            try {
                content = fileWriter.generateString(inputUrl, checker.isReachable(inputUrl));
            } catch (IOException e) {
                System.err.println("I/O Fehler bei der Server Connection: " + e.getMessage());
                System.exit(0);
            } catch (IllegalArgumentException e) {
                System.err.println("Falsches URL Format: " + e.getMessage());
                System.exit(0);
            }

            try {
                fileWriter.writeStringToFile(fileName, content);
            } catch (IOException e) {
                System.err.println("Fehler beim schreiben von der Log-Datei: " + e.getMessage());
            }

            Thread.sleep(30000);
        }
    }
}
