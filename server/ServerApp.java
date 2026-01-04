package server;

import tree.GenreTree;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server application for the RecomTree recommendation system.
 * Listens for client connections and spawns a handler thread for each client.
 * Initializes the genre tree with sample genres and manages the shared tree
 * state.
 */
public class ServerApp {

    private static final int PORT = 4500;

    /**
     * Main entry point for the server application.
     * Initializes the genre tree with sample genres,
     * opens a server socket, and accepts client connections.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        GenreTree genreTree = new GenreTree("Movies");

        // Initialize the genre hierarchy for the demo
        genreTree.addSubGenre("Movies", "Action");
        genreTree.addSubGenre("Movies", "Drama");
        genreTree.addSubGenre("Action", "Thriller");
        genreTree.addSubGenre("Drama", "Romance");

        System.out.println("RecomTree server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Accept client connections indefinitely
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected: " + client.getInetAddress());
                new Thread(new ClientHandler(client, genreTree)).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}
