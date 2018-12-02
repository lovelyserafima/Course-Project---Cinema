package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import javax.swing.*;

public class AdminMenuFrame {
    public JFrame frame;
    public JPanel adminMenu;
    private JButton addFilm;
    private JButton seeAndDeleteAffiche;
    private JButton exit;
    private JButton addingSessionToAffiche;
    private JButton films;

    public AdminMenuFrame(JFrame frame) {
        this.frame=frame;

        addFilm.addActionListener(e -> {
                this.frame.dispose();
                openAddingFilm();
        });

        addingSessionToAffiche.addActionListener(e -> {
            this.frame.dispose();
            opedAddingSessionToAffiche();
        });

        films.addActionListener(e -> {
            this.frame.dispose();
            try {
                openFilms();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        seeAndDeleteAffiche.addActionListener(e -> {
            this.frame.dispose();
            try {
                openAffiche();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void openFilms() throws ProjectException {
        JFrame filmFrame = new JFrame("Просмотр фильмов");
        filmFrame.setBounds(650, 300, 15000, 300);
        filmFrame.setContentPane(new FilmsFrame(filmFrame).getPanel);
        filmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmFrame.pack();
        filmFrame.setVisible(true);
    }

    private void opedAddingSessionToAffiche() {
        JFrame sessionFrame = new JFrame("Добавление cеанса");
        sessionFrame.setBounds(650, 300, 15000, 300);
        sessionFrame.setContentPane(new AddSessionToAfficheFrame(sessionFrame).adminAddingSessionPanel);
        sessionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sessionFrame.pack();
        sessionFrame.setVisible(true);
    }

    private void openAddingFilm(){
        JFrame filmFrame = new JFrame("Добавление фильма");
        filmFrame.setBounds(650, 300, 15000, 300);
        filmFrame.setContentPane(new AdminAddFilmFrame(filmFrame).adminAddingFilmPanel);
        filmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmFrame.pack();
        filmFrame.setVisible(true);
    }

    private void openAffiche() throws ProjectException {
        JFrame afficheFrame = new JFrame("Просмотр афиши");
        afficheFrame.setBounds(650, 300, 15000, 300);
        afficheFrame.setContentPane(new AdminAfficheFrame(afficheFrame).getPanel);
        afficheFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        afficheFrame.pack();
        afficheFrame.setVisible(true);
    }
}
