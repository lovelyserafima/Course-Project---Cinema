package by.bsuir.cinema.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller implements Runnable{
    private static ServerSocket server;
    private static Socket connection;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    public static void main(String[] args) {
        new Thread(new Controller()).start();
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(3600, 10);
            while (true) {
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                output.flush();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
