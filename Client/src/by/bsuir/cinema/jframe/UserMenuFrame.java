package by.bsuir.cinema.jframe;

import by.bsuir.cinema.exception.ProjectException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserMenuFrame {
    public JPanel userMenu;
    private JButton affiche;
    private JButton basket;
    private JButton exit;
    public JFrame frame;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    public UserMenuFrame(JFrame frame) {
        this.frame=frame;
        affiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openAffice(frame,output);
                } catch (ProjectException e1) {
                    e1.printStackTrace();
                }
            }
        });

        basket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBasket(frame, output);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
    }

    public void sendData(Object info) {
        try {
            output.flush();
            output.writeObject(info);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAffice(JFrame frame, ObjectOutputStream output) throws ProjectException {
        JFrame ticketFrame = new JFrame("Покупка билета");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new AfficheFrame(ticketFrame).getPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void showBasket(JFrame frame, ObjectOutputStream output) {
        /*JFrame removeOrAddMenu = new JFrame("Получение графика");
        removeOrAddMenu.setBounds(650, 300, 1000, 300);
        removeOrAddMenu.setContentPane(new GetGraphics(frame, removeOrAddMenu, output, this).getGraphicPanel);
        removeOrAddMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeOrAddMenu.pack();
        removeOrAddMenu.setVisible(true);
        frame.setVisible(false);*/
    }

    /*@Override
    public void run() {
        Printer printer = new Printer();
        try {
            while (true) {
                connection = new Socket(InetAddress.getByName("127.0.0.1"), 5678);
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                printer.print((String) input.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
}
