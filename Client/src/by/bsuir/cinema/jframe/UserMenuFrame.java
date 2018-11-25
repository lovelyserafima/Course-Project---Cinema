package by.bsuir.cinema.jframe;

import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.UserLogic;

import javax.swing.*;
import static by.bsuir.cinema.controller.Controller.user;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;

public class UserMenuFrame {
    public JPanel userMenu;
    private JButton affiche;
    private JButton basket;
    private JButton exit;
    private JButton tickets;
    private JTextField value;
    private JButton topUp;
    private JTextField balance;
    public JFrame frame;
    static private Socket connection;
    static private ObjectOutputStream output;
    static private ObjectInputStream input;

    public UserMenuFrame(JFrame frame) {
        this.frame=frame;
        balance.setText("Ваш баланс = " + ((Client) user).getMoney());
        affiche.addActionListener(e -> {
            try {
                openAffice();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        basket.addActionListener(e -> {
            try {
                showBasket();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        tickets.addActionListener(e -> {
            showTickets();
        });

        topUp.addActionListener(e -> {
            boolean flag = false;
            try {
                flag = UserLogic.updateUserMoney(BigDecimal.valueOf(Double.parseDouble(value.getText())),
                        (Client) user);
                if (flag){
                    JOptionPane.showMessageDialog(null, "Баланс был успешно пополнен");
                    openUserMenu(this.frame);
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            }

        });


        exit.addActionListener(e -> {
            try {
                connection.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            frame.dispose();
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

    private void openAffice() throws ProjectException {
        JFrame ticketFrame = new JFrame("Покупка билета");
        ticketFrame.setBounds(650, 300, 15000, 300);
        ticketFrame.setContentPane(new AfficheFrame(ticketFrame).getPanel);
        ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticketFrame.pack();
        ticketFrame.setVisible(true);
    }

    private void showBasket() throws ProjectException {
        JFrame basketFrame = new JFrame("Корзина");
        basketFrame.setBounds(650, 300, 1000, 300);
        basketFrame.setContentPane(new BasketFrame(basketFrame).getPanel);
        basketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        basketFrame.pack();
        basketFrame.setVisible(true);
        frame.setVisible(false);
    }

    private void showTickets(){
        JFrame ticketFrame = new JFrame("Билеты");
        ticketFrame.setBounds(650, 300, 1000, 300);
        ticketFrame.setContentPane(new TicketsFrame(ticketFrame).getPanel);
        ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticketFrame.pack();
        ticketFrame.setVisible(true);
        frame.setVisible(false);
    }

    private void openUserMenu(JFrame frame) {
        JFrame newFrame = new JFrame("Меню пользователя");
        newFrame.setBounds(650, 300, 15000, 300);
        newFrame.setContentPane(new UserMenuFrame(newFrame).userMenu);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);
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
