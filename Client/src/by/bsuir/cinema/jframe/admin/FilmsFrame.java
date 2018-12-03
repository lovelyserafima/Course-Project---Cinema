package by.bsuir.cinema.jframe.admin;

import static by.bsuir.cinema.controller.Starter.user;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilmsFrame {
    private JTextArea filmsValue;
    public JPanel getPanel;
    private JLabel films;
    private JButton back;
    private JTextField filmId;
    private JButton deleteFilm;
    private JScrollPane jSc;
    public JFrame jFrame;
    private DataOutputStream output;

    public FilmsFrame(JFrame frame, DataOutputStream output) throws ProjectException {
        this.output = output;

        jFrame = frame;
        filmsValue.append(FilmLogic.findAllFilms());

        deleteFilm.addActionListener(e -> {
           int integerFilmId = Integer.parseInt(filmId.getText());
            try {
                if (FilmLogic.deleteFilmById(integerFilmId)){
                    JOptionPane.showMessageDialog(null, "Фильм был успешно удален");
                    this.output.writeUTF(user.toString() + "deleted film with id = " + integerFilmId);
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
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
        frame.setContentPane(new AdminMenuFrame(frame, output).adminMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
