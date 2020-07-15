package org.example.o7planning;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        final String serverHost = "localhost";

        Socket clientSocket = null;
        BufferedWriter os = null;
        BufferedReader is = null;

        try {
            clientSocket = new Socket(serverHost, 9999);
            os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return;
        }
        Scanner scanner = new Scanner(System.in);
        boolean finish = false;

        try {
            while (!finish) {
                String line = scanner.nextLine();
                String response = null;
                if (Objects.equals(line, "quit")) {
                    finish = true;
                }
                os.write(line);
                os.newLine();
                os.flush();

                while (is.ready() && (response = is.readLine()) != null) {
                    System.out.println("server response: " + response);
                }
            }
            os.close();
            is.close();
            clientSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
