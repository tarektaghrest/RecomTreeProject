package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientCLI {

    private Socket socket;

    public ClientCLI(Socket socket) {
        this.socket = socket;
    }

    public void start() {

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {

            // Messages initiaux du serveur (lire jusqu'à "END")
            String line;
            while ((line = in.readLine()) != null && !line.equals("END")) {
                System.out.println(line);
            }

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                out.println(input);

                while ((line = in.readLine()) != null && !line.equals("END")) {
                    System.out.println(line);
                }

                if (input.equalsIgnoreCase("QUIT")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Connection closed.");
        }
    }
}