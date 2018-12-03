package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.entity.user.User;
import by.bsuir.cinema.exception.ProjectException;
import static by.bsuir.cinema.controller.Starter.user;
import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AdminMenuFrame implements Runnable {
    public JFrame frame;
    public JPanel adminMenu;
    private JButton addFilm;
    private JButton seeAndDeleteAffiche;
    private JButton exit;
    private JButton addingSessionToAffiche;
    private JButton films;
    private DataOutputStream output;
    //private static ObjectInputStream input;

    public AdminMenuFrame(JFrame frame, DataOutputStream dataOutputStream) {
        this.frame=frame;
        this.output = dataOutputStream;

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

        exit.addActionListener(e -> {
            this.frame.dispose();
        });
    }

    public AdminMenuFrame(DataOutputStream dataOutputStream) {
        this.output = dataOutputStream;
    }

    private void openFilms() throws ProjectException {
        JFrame filmFrame = new JFrame("Просмотр фильмов");
        filmFrame.setBounds(650, 300, 15000, 300);
        filmFrame.setContentPane(new FilmsFrame(filmFrame, output).getPanel);
        filmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmFrame.pack();
        filmFrame.setVisible(true);
    }

    private void opedAddingSessionToAffiche() {
        JFrame sessionFrame = new JFrame("Добавление cеанса");
        sessionFrame.setBounds(650, 300, 15000, 300);
        sessionFrame.setContentPane(new AddSessionToAfficheFrame(sessionFrame, output).adminAddingSessionPanel);
        sessionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sessionFrame.pack();
        sessionFrame.setVisible(true);
    }

    private void openAddingFilm(){
        JFrame filmFrame = new JFrame("Добавление фильма");
        filmFrame.setBounds(650, 300, 15000, 300);
        filmFrame.setContentPane(new AdminAddFilmFrame(filmFrame, output).adminAddingFilmPanel);
        filmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmFrame.pack();
        filmFrame.setVisible(true);
    }

    private void openAffiche() throws ProjectException {
        JFrame afficheFrame = new JFrame("Просмотр афиши");
        afficheFrame.setBounds(650, 300, 15000, 300);
        afficheFrame.setContentPane(new AdminAfficheFrame(afficheFrame, output).getPanel);
        afficheFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        afficheFrame.pack();
        afficheFrame.setVisible(true);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            this.output.writeUTF(user.toString() + " подключился");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
