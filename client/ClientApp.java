package client;

import java.io.IOException;
import java.net.Socket;

/**
 * Client application for the RecomTree recommendation system.
 * Establishes a connection to the server and provides a command-line interface
 * for interacting with the recommendation system.
 */
public class ClientApp {

    /**
     * Main entry point for the client application.
     * Connects to the RecomTree server and launches the CLI interface.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 4500;

        try {
            Socket socket = new Socket(host, port);
            new ClientCLI(socket).start();
        } catch (IOException e) {
            System.out.println("Unable to connect to server.");
        }
    }
}
