package main;

import gui.LoginForm;

import java.awt.*;

// Client interface app
public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new LoginForm());
    }

}
