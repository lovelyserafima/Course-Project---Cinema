package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;

import javax.swing.*;

public class AdminAddFilmFrame {
    public JPanel adminAddingFilmPanel;
    private JTextField mainRolesValue;
    private JTextField filmNameValue;
    private JTextField genreNameValue;
    private JButton addFilmButton;
    private JButton backButton;
    private JLabel filmNameLabel;
    private JLabel genreLabel;
    private JLabel producersLabel;
    private JTextField producersValue;
    private JTextField genreValue;
    private JLabel mainRolesLabel;
    public JFrame frame;

    public AdminAddFilmFrame(JFrame filmFrame) {
        frame = filmFrame;

        addFilmButton.addActionListener(e -> {
            String filmName = filmNameValue.getText();
            String genreName = genreNameValue.getText();
            String producers = producersValue.getText();
            String mainRoles = mainRolesValue.getText();
            if (filmName != "" && genreName != "" && producers != "" && mainRoles != ""){
                try {
                    if (FilmLogic.addNewFilm(filmName, genreName, producers, mainRoles)){
                        JOptionPane.showMessageDialog(null, "Фильм был успешно добавлен");
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
