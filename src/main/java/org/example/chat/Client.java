package org.example.chat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class Client {

    public static void main(String[] args) {

        final String serverHost = "localhost";

        Socket clientSocket;
        BufferedWriter writer;
        BufferedReader socketReader;
        BufferedReader consoleReader;

        boolean finish = false;
        String clientName;

        try {
            clientSocket = new Socket(serverHost, 9999);
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("I/O error for the connection to " + serverHost);
            return;
        }

        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println(reader.readLine());
            clientName = consoleReader.readLine();
            writer.write(clientName);
            writer.newLine();
            writer.flush();

            while (!finish) {



                System.out.print("You: ");
                String line = consoleReader.readLine();
                if (Objects.equals(line, "quit")) {
                    finish = true;
                }
                writer.write(line);
                writer.newLine();
                writer.flush();

            }
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
