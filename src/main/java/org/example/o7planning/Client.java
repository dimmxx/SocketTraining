package org.example.o7planning;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

public static void main(String[] args) {

       // Server Host
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

       try {

           //Scanner scanner = new Scanner(System.in);
           //String line = scanner.nextLine();

           // Write data to the output stream of the Client Socket.
           os.write("line");
           // End of line
           os.newLine();
           // Flush data.
          os.flush();


           os.write("QUIT");
           os.newLine();
           os.flush();

           // Read data sent from the server.
           // By reading the input stream of the Client Socket.
           String responseLine;
           while ((responseLine = is.readLine()) != null) {
               System.out.println("Server: " + responseLine);
               if (responseLine.indexOf("OK") != -1) {
                   break;
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
