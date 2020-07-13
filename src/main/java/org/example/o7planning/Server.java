package org.example.o7planning;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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

            int i = 0;

            while (true) {
                System.out.println(i);
                i++;
                line = is.readLine();
                System.out.println("server's inputstream: " + line);
                os.write(">>> " + line);
                os.newLine();
                os.flush();

                if (line.equals("QUIT")) {
                    os.write(">>> OK");
                    os.newLine();
                    os.flush();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server: server is stopped");
    }
}
