package main;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 8089;
    private static final String ADDRESS = "localhost";

    private Socket socket = null;
    private DataOutputStream outputStream = null;
    private DataInputStream inputStream = null;

    public Client() {
        try {
            socket = new Socket(ADDRESS, PORT);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void closeStreams() throws IOException {
        inputStream.close();
        outputStream.close();
    }

    public void closeClient() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
        boolean exit = false;
        String received;
        char[] passwordCharArr;

        try {
            while (!exit) {
                System.out.println(client.getInputStream().readUTF());
                String reply = scanner.nextLine();
                client.getOutputStream().writeUTF(reply);

                int option = Integer.parseInt(reply);

                switch (option) {
                    case 1:
                        System.out.println(client.inputStream.readUTF());       //First name banner, input
                        reply = scanner.nextLine();                             //and output it
                        client.outputStream.writeUTF(reply);                    //

                        System.out.println(client.inputStream.readUTF());       //Last name banner, input
                        reply = scanner.nextLine();                             //and output it
                        client.outputStream.writeUTF(reply);                    //

                        System.out.println(client.inputStream.readUTF());       //Phone number banner,
                        reply = scanner.nextLine();                             //input and output it
                        client.outputStream.writeUTF(reply);                    //

                        System.out.println(client.inputStream.readUTF());       //Password banner, input
                        passwordCharArr = console.readPassword();               //and output it
                        reply = new String(passwordCharArr);                    //
                        client.outputStream.writeUTF(reply);                    //

                        System.out.println(client.inputStream.readUTF());       //User type banner, input
                        reply = scanner.nextLine();                             //and output it
                        client.outputStream.writeUTF(reply);                    //

                        System.out.println(client.inputStream.readUTF());       //Registration status banner
                        break;
                    case 2:
                        System.out.println(client.inputStream.readUTF());       //
                        reply = scanner.nextLine();
                        client.outputStream.writeUTF(reply);

                        System.out.println(client.inputStream.readUTF());       //
                        passwordCharArr = console.readPassword();               //and output it
                        reply = new String(passwordCharArr);                    //
                        client.outputStream.writeUTF(reply);

                        System.out.println(client.inputStream.readUTF());

                        launchMenu(client);

                        break;
                    case 3:
                        received = client.inputStream.readUTF();

                        if(received.equals("")) {
                            System.out.println("No student enrolled");
                        } else {
                            System.out.println(received);
                        }
                        break;
                    case 4:
                        received = client.inputStream.readUTF();

                        if(received.equals("")) {
                            System.out.println("No teacher hired");
                        } else {
                            System.out.println(received);
                        }
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        System.out.println("Closing connection");
                        client.closeClient();
                        System.out.println("Connection closed");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }

            scanner.close();
            client.closeStreams();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void launchMenu(Client client) throws IOException {

        boolean exit = false;

        System.out.println(client.inputStream.readUTF());

        while(!exit) {
            System.out.println(client.inputStream.readUTF());
            Scanner scanner = new Scanner(System.in);

            String input = scanner.nextLine();

            client.outputStream.writeUTF(input);

            System.out.println(client.inputStream.readUTF());

            if(input.equals("3")) {
                exit = true;
            }
        }
    }
}
