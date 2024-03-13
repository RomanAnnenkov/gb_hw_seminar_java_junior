package org.example.model;

import org.example.view.IView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectedClient implements Runnable {
    private String name;
    private static final List<ConnectedClient> connectedClients = new ArrayList<>();
    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private IView serverView;

    public ConnectedClient(Socket socket, IView serverView) {
        this.socket = socket;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.name = bufferedReader.readLine();
            this.serverView = serverView;
            connectedClients.add(this);
            serverView.showMessage(name + " connected.");
            sendMessageToAll("Server: " + name + " connected.");
        } catch (IOException e) {
            close();
        }
    }

    private void sendMessageToAll(String message) {
        for (ConnectedClient client : connectedClients) {
            try {
                client.bufferedWriter.write(message);
                client.bufferedWriter.newLine();
                client.bufferedWriter.flush();
            } catch (IOException e) {
                client.close();
            }

        }
    }

    private void close() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            connectedClients.remove(this);
            serverView.showMessage(this.name + " disconnected.");
            sendMessageToAll("Server: " + this.name + " disconnected.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                String message = bufferedReader.readLine();
                if (message == null) {
                    close();
                    break;
                }
                sendMessageToAll(message);
            } catch (IOException e) {
                close();
            }
        }
    }
}
