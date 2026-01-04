package server;

import command.Command;
import command.CommandFactory;
import tree.GenreTree;

import java.io.*;
import java.net.Socket;

/**
 * Handles client connections to the RecomTree server.
 * Runs in a separate thread for each connected client.
 * Reads commands from the client, executes them, and sends responses back.
 */
public class ClientHandler implements Runnable {

    private Socket socket;
    private GenreTree genreTree;

    /**
     * Constructs a ClientHandler for a new client connection.
     *
     * @param socket    the client socket
     * @param genreTree the shared genre tree to operate on
     */
    public ClientHandler(Socket socket, GenreTree genreTree) {
        this.socket = socket;
        this.genreTree = genreTree;
    }

    /**
     * Runs the client handler in a separate thread.
     * Handles the communication protocol with the client,
     * including welcoming messages, command parsing, and response delivery.
     */
    @Override
    public void run() {

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {
            // Send welcome message to client
            out.println("Welcome to RecomTree!");
            out.println("Available commands: ADD_MOVIE, LIST_SUBTREE, RATE, RECOMMEND");
            out.println("END");

            String line;
            while ((line = in.readLine()) != null) {

                // Handle client disconnect
                if (line.equalsIgnoreCase("QUIT")) {
                    out.println("Goodbye!");
                    out.println("END");
                    System.out.println("Client disconnected: " + socket.getInetAddress() + ":" + socket.getPort());
                    break;
                }

                // Parse and execute the command
                Command command = CommandFactory.create(line, genreTree);
                String response;
                try {
                    response = command.execute();
                } catch (Exception e) {
                    response = "ERROR: Internal server error.";
                    System.err.println("Error executing command: " + e.getMessage());
                }

                // Send response to client
                for (String l : response.split("\\R")) {
                    out.println(l);
                }
                out.println("END");
            }

        } catch (IOException e) {
            System.out.println("Client disconnected: " + socket.getInetAddress() + ":" + socket.getPort());
        }
    }
}