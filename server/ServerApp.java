package server;

import tree.GenreTree;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    private static final int PORT = 4500;

    public static void main(String[] args) {
        GenreTree genreTree = new GenreTree("Movies");

        // Genres de base (CORE, en dur pour la démo)
        genreTree.addSubGenre("Movies", "Action");
        genreTree.addSubGenre("Movies", "Drama");
        genreTree.addSubGenre("Action", "Thriller");
        genreTree.addSubGenre("Drama", "Romance");

        System.out.println("RecomTree server started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

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
