package org.example;

import org.example.view.ConsoleView;
import org.example.view.IView;

public class Main {
    public static void main(String[] args) {
        IView view = new ConsoleView();
        view.run();
    }
}
