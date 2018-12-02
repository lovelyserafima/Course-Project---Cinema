package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.SessionLogic;

import javax.swing.*;

public class AdminAfficheFrame {
    public JPanel getPanel;
    private JLabel ourSessions;
    private JButton back;
    private JButton deleteSession;
    private JTextField sessionId;
    private JScrollPane jSc;
    private JTextArea sessionsValue;
    public JFrame jFrame;

    public AdminAfficheFrame(JFrame jFrame) throws ProjectException {
        this.jFrame = jFrame;

        sessionsValue.append(SessionLogic.findAllSessions());

        deleteSession.addActionListener(e -> {
            int integerSessionId = Integer.parseInt(sessionId.getText());
            try {
                if (SessionLogic.deleteFromSession(integerSessionId)){
                    JOptionPane.showMessageDialog(null, "Сеанс был успешно удален");
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            }
        });

        back.addActionListener(e -> {
            this.jFrame.dispose();
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
