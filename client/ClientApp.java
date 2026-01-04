package client;

import java.io.IOException;
import java.net.Socket;

public class ClientApp {

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
