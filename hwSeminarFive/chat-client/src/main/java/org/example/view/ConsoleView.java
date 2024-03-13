package org.example.view;

import org.example.model.Client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleView implements IView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {

        showMessage("Console chat.");
        System.out.print("Enter your name: ");
        String input = scanner.nextLine();

        try {
            Socket socket = new Socket("192.168.32.198", 2055);
            Client client = new Client(socket, input, this);
            client.receiveMessage();
            client.sendMessage();

        } catch (IOException e) {
            showMessage("ERROR: " + e.getMessage());
        }

    }

    public String getMessage() {
        return scanner.nextLine();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
