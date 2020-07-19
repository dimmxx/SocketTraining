package org.example.chat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class Client {

    public static void main(String[] args) {

        final String serverHost = "localhost";

        Socket clientSocket;
        BufferedWriter socketWriter;
        BufferedReader socketReader;
        BufferedReader consoleReader;

        boolean finish = false;
        String clientName;

        try {
            clientSocket = new Socket(serverHost, 9999);
            socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("I/O error for the connection to " + serverHost);
            return;
        }

        ClientReadThread clientReadThread = new ClientReadThread(socketReader);
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            clientName = acquaintClient(consoleReader, socketWriter);

            while (!finish) {

                //System.out.print("You: ");
                String line = consoleReader.readLine();
                if (Objects.equals(line, "quit")) {
                    finish = true;
                }
                socketWriter.write(line);
                socketWriter.newLine();
                socketWriter.flush();

            }
            socketWriter.close();
            socketReader.close();
            clientSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }

    private static String acquaintClient(BufferedReader consoleReader, BufferedWriter socketWrite) throws IOException {
        String clientName = consoleReader.readLine();
        socketWrite.write(clientName);
        socketWrite.newLine();
        socketWrite.flush();
        return clientName;
    }

}
