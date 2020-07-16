package org.example.o7planning;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ServerThread extends Thread {

    private final int clientNumber;
    private String clientName;
    private Socket socket;

    private String line;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ServerThread(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void run() {





        try {
            while ((line = reader.readLine()) != null) {

                System.out.println("server has received from Client " + clientNumber + " " + line);
                for (ServerThread thread : Server.threads) {

                    thread.getWriter().write("Client " + clientNumber + " said: " + line);
                    thread.getWriter().newLine();
                    thread.getWriter().flush();
                }


                if (Objects.equals(line.toLowerCase().trim(), "quit")) {
                    break;
                }

                //writer.write(">>>> " + line);
                //writer.newLine();
                // writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Server: server is stopped");
    }
}

