package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;
import by.bsuir.cinema.logic.SessionLogic;

import javax.swing.*;

public class AddSessionToAfficheFrame {
    public JPanel adminAddingSessionPanel;
    private JLabel filmNameLabel;
    private JTextField filmNameValue;
    private JLabel dateAndTimeLabel;
    private JTextField dateAndTimeValue;
    private JLabel costLabel;
    private JTextField costValue;
    private JButton addSessionButton;
    private JButton backButton;
    public JFrame frame;

    public AddSessionToAfficheFrame(JFrame sessionFrame) {
        this.frame = sessionFrame;

        addSessionButton.addActionListener(e -> {
            String filmName = filmNameValue.getText();
            String dateAndTime = dateAndTimeValue.getText();
            String cost = costValue.getText();
            if (filmName != "" && dateAndTime != "" && cost != ""){
                try {
                    if (SessionLogic.addNewSession(filmName, dateAndTime, cost)){
                        JOptionPane.showMessageDialog(null, "Сеанс был успешно добавлен");
                    } else {
                        JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                    }
                } catch (ProjectException e1) {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Вы не все поля заполнили");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            opedAdminUserMenu();
        });
    }

    private void opedAdminUserMenu() {
        JFrame frame = new JFrame("Меню администратора");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new AdminMenuFrame(frame).adminMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
