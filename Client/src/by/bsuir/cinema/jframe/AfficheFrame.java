package by.bsuir.cinema.jframe;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.BasketLogic;
import by.bsuir.cinema.logic.SessionLogic;
import static by.bsuir.cinema.controller.Controller.user;
import javax.swing.*;
import java.io.ObjectOutputStream;

public class AfficheFrame {
    public JPanel getPanel;
    public JFrame frame;
    private JLabel sessions;
    private JButton back;
    private JTextArea Tickets;
    private JButton addToBasket;
    private JTextField sessionId;
    static private ObjectOutputStream output;

    public AfficheFrame(JFrame jFrame) throws ProjectException {
        this.frame = jFrame;
        Tickets.removeAll();
        Tickets.append(SessionLogic.findAllSessions());

        addToBasket.addActionListener(e -> {
            try {
                boolean flag = BasketLogic.addToBasket(user.getId(), Integer.parseInt(sessionId.getText()));
                if (flag){
                    JOptionPane.showMessageDialog(null,
                            "Билет был успешно добавлен в корзину");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Упс, что-то пошло не так");
                }
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        back.addActionListener(e -> {
            backToUserMenu(frame, output);
        });
    }

    private void backToUserMenu(JFrame frame, ObjectOutputStream objectOutputStream) {
        frame.setVisible(false);
        JFrame jFrame = new JFrame("Меню пользователя");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new UserMenuFrame(jFrame).userMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
