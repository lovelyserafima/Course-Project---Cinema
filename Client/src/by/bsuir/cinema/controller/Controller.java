package by.bsuir.cinema.controller;

import by.bsuir.cinema.logic.SignInLogic;
import by.bsuir.cinema.util.Encryption;
import by.bsuir.cinema.util.constant.ConstantMessages;
import javax.swing.*;

public class Controller {
    private static final int X = 650;
    private static final int Y = 300;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 300;

    private JPanel authorizationPanel;
    private JButton signIn;
    private JLabel authorization;
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
            switch (SignInLogic.findUser(loginText.getText(), Encryption.encryptPassword(passwordField.getText()))) {
                case "admin":
                    authorizationFrame.dispose();
                    //openAdminMenu();
                    break;
                case "user":
                    authorizationFrame.dispose();
                    //openUserMenu();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Отсутствует пользователь с таким логином или паролем");
                    break;
            }


        });
    }
}