package by.bsuir.cinema.controller;

import by.bsuir.cinema.entity.user.User;
import by.bsuir.cinema.logic.UserLogic;
import by.bsuir.cinema.jframe.UserMenuFrame;
import by.bsuir.cinema.util.Encryption;
import by.bsuir.cinema.util.constant.ConstantMessages;
import javax.swing.*;

public class Controller {
    private static final int X = 650;
    private static final int Y = 300;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 300;
    public static User user;

    private JPanel authorizationPanel;
    private JButton signIn;
    private JLabel signInLogic;
    private JLabel login;
    private JLabel password;
    private JTextField loginText;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        JFrame authorizationFrame = new JFrame(ConstantMessages.AUTH);
        authorizationFrame.setBounds(X, Y, WIDTH, HEIGHT);
        authorizationFrame.setContentPane(new Controller(authorizationFrame).authorizationPanel);
        authorizationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        authorizationFrame.pack();
        authorizationFrame.setVisible(true);
    }

    public Controller(JFrame authorizationFrame) {
        signIn.addActionListener(e -> {
            user = UserLogic.findUser(loginText.getText(), Encryption.encryptPassword(passwordField.getText()));
            if (user != null) {
                switch (user.getType()) {
                    case ADMIN:
                        authorizationFrame.dispose();
                        //openAdminMenu();
                        break;
                    case CLIENT:
                        authorizationFrame.dispose();
                        openUserMenu();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Ошибка");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Такого пользователя нет");
            }
        });
    }

    /*private void openAdminMenu() {
        JFrame frame = new JFrame("Меню администратора");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new AdminMenuFrame(frame).adminMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        new Thread(new AdminMenu(frame)).start();
    }*/

    private void openUserMenu() {
        JFrame frame = new JFrame("Меню пользователя");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new UserMenuFrame(frame).userMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
