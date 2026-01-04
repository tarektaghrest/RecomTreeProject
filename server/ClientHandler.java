package server;

import command.Command;
import command.CommandFactory;
import tree.GenreTree;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private GenreTree genreTree;

    public ClientHandler(Socket socket, GenreTree genreTree) {
        this.socket = socket;
        this.genreTree = genreTree;
    }

    @Override
    public void run() {

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {
            out.println("Welcome to RecomTree!");
            out.println("Available commands: ADD_MOVIE, LIST_SUBTREE, RATE, RECOMMEND");
            out.println("END");

            String line;
            while ((line = in.readLine()) != null) {

                if (line.equalsIgnoreCase("QUIT")) {
                    out.println("Goodbye!");
                    out.println("END");
                    System.out.println("Client disconnected: " + socket.getInetAddress() + ":" + socket.getPort());
                    break;
                }

                Command command = CommandFactory.create(line, genreTree);
                String response;
                try {
                    response = command.execute();
                } catch (Exception e) {
                    response = "ERROR: Internal server error.";
                    System.err.println("Error executing command: " + e.getMessage());
                }

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