package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;
import by.bsuir.cinema.logic.SessionLogic;

import javax.swing.*;
import java.awt.*;

public class FilmsFrame {
    private JTextArea filmsValue;
    public JPanel getPanel;
    private JLabel films;
    private JButton back;
    private JTextField filmId;
    private JButton deleteFilm;
    private JScrollPane jSc;
    public JFrame jFrame;

    public FilmsFrame(JFrame frame) throws ProjectException {
        jFrame = frame;
        filmsValue.append(FilmLogic.findAllFilms());

        deleteFilm.addActionListener(e -> {
           int integerFilmId = Integer.parseInt(filmId.getText());
            try {
                if (FilmLogic.deleteFilmById(integerFilmId)){
                    JOptionPane.showMessageDialog(null, "Фильм был успешно удален");
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            }
        });

        back.addActionListener(e -> {
            jFrame.dispose();
            openAdminUserMenu();
        });
    }

    private void openAdminUserMenu(){
        JFrame frame = new JFrame("Меню администратора");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new AdminMenuFrame(frame).adminMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
