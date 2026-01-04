package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Command-line interface for the RecomTree client.
 * Manages user input and server communication.
 * Uses a message-based protocol where messages are terminated with "END".
 */
public class ClientCLI {

    private Socket socket;

    /**
     * Constructs a ClientCLI with the given socket connection.
     *
     * @param socket the socket connection to the server
     */
    public ClientCLI(Socket socket) {
        this.socket = socket;
    }

    /**
     * Starts the interactive command-line interface.
     * Reads initial messages from the server, then enters a loop
     * prompting for user input and displaying server responses.
     * Terminates when the user enters "QUIT".
     */
    public void start() {

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {

            // Read and display initial messages from the server
            String line;
            while ((line = in.readLine()) != null && !line.equals("END")) {
                System.out.println(line);
            }

            // Main command loop
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                out.println(input);

                // Read and display server response
                while ((line = in.readLine()) != null && !line.equals("END")) {
                    System.out.println(line);
                }

                // Exit if user entered QUIT
                if (input.equalsIgnoreCase("QUIT")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }
}