package org.example.model;

import org.example.view.IView;

import java.io.*;
import java.net.Socket;

public class Client {
    private String name;
    private Socket socket;
    private IView view;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket, String name, IView view) {
        try {
            this.socket = socket;
            this.name = name;
            this.view = view;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            close();
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            while (socket.isConnected()) {
                String message = view.getMessage();
                bufferedWriter.write(name + ": " + message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            close();
        }
    }

    public void receiveMessage() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    try {
                        String inputMessage = bufferedReader.readLine();
                        if (inputMessage == null) {
                            close();
                            break;
                        }
                        view.showMessage(inputMessage);
                    } catch (IOException e) {
                        close();
                    }
                }
            }
        });
        thread.start();
    }

    public void close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
