package org.example.model;

import org.example.view.IView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    private final IView serverView;

    public Server(ServerSocket serverSocket, IView serverView) {
        this.serverSocket = serverSocket;
        this.serverView = serverView;
        serverView.showMessage("Server is up.");
        serverView.showMessage("Listen on port: "+ serverSocket.getLocalPort());
    }

    public void run() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ConnectedClient connectedClient = new ConnectedClient(socket, serverView);
                Thread thread = new Thread(connectedClient);
                thread.start();
            }
        }
        catch (IOException e){
            close();
        }
    }


    private void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
