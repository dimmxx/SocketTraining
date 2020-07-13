package org.example.o7planning;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {

    public static void main(String[] args) {

        ServerSocket listener = null;
        Socket serverSocket = null;

        String line;
        BufferedReader is;
        BufferedWriter os;

        try {
            listener = new ServerSocket(9999);
            System.out.println("Server: listener created");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            System.out.println("Server: server is listening to accept user ...");
            serverSocket = listener.accept();
            System.out.println("Server: a client is accepted");

            is = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));


            boolean finish = false;
            while (!finish) {
                while (is.ready() && ((line = is.readLine()) != null)) {
                    if(Objects.equals(line.toLowerCase().trim(), "quit")){
                        finish = true;
                    }
                    System.out.println("server has received: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server: server is stopped");
    }
}
