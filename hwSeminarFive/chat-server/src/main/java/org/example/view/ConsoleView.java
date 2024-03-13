package org.example.view;

import org.example.model.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ConsoleView implements IView {
    public ConsoleView() {
        try {
            ServerSocket serverSocket = new ServerSocket(2055);
            Server server = new Server(serverSocket, this);
            server.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

}
