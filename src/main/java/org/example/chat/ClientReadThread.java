package org.example.chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReadThread extends Thread{

    BufferedReader socketReader;
    private String response;


    public ClientReadThread(BufferedReader socketReader) {
        this.socketReader = socketReader;
        start();
    }

    @Override
    public void run() {
        try {
            while (true){
                if ((response = socketReader.readLine()) != null) {
                    System.out.println(response);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
