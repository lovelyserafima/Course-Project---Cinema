package by.bsuir.cinema.jframe.client;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.TicketsLogic;
import static by.bsuir.cinema.controller.Starter.user;
import javax.swing.*;

public class TicketsFrame {
    public JPanel getPanel;
    private JLabel yourTickets;
    private JButton back;
    private JTextArea tickets;
    private JFrame jFrame;

    public TicketsFrame(JFrame jFrame){
        this.jFrame = jFrame;
        tickets.removeAll();
        try {
            tickets.append(TicketsLogic.findAllTickets(user.getId()));
        } catch (ProjectException e) {
            e.printStackTrace();
        }

        back.addActionListener(e -> {
            this.jFrame.dispose();
            openUserMenu(jFrame);
        });
    }

    private void openUserMenu(JFrame frame) {
        JFrame newFrame = new JFrame("Меню пользователя");
        newFrame.setBounds(650, 300, 15000, 300);
        newFrame.setContentPane(new UserMenuFrame(newFrame).userMenu);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);
    }
}
