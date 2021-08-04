package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8089;
    private static Server instance = null;

    private ServerSocket serverSocket = null;
    private Socket socket = null;

    private Server() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance() {
        if(instance == null) {
            instance = new Server();
        }

        return instance;
    }

    public void setSocket() {
        try {
            this.socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void closeServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = Server.getInstance();

        System.out.println("Server has been started");

        while(true) {
            server.setSocket();

            System.out.println("A client just connected");

            try {
                DataInputStream inputStream = new DataInputStream(server.getSocket().getInputStream());
                DataOutputStream outputStream = new DataOutputStream(server.getSocket().getOutputStream());

                System.out.println("Assigning new thread for client");

                Thread thread = new ClientHandler(server.getSocket(), inputStream, outputStream);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
