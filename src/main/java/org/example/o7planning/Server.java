package org.example.o7planning;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    protected static volatile List<ServerThread> threads = new ArrayList<>();
    protected static volatile int clientNumber = 0;

    public static void main(String[] args) throws IOException {

        ServerSocket listener = null;

        try {
            listener = new ServerSocket(9999);
            System.out.println("Server: listener created");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            while (true) {
                Socket socket = listener.accept();
                ServerThread thread = new ServerThread(socket, clientNumber);
                threads.add(thread);
                System.out.println("Client " + clientNumber + " connected");
                clientNumber++;
            }

        } finally {
            listener.close();
        }
    }
}
