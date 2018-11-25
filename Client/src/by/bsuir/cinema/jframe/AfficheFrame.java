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
    private JLabel ourSessions;
    private JButton back;
    //private JTextArea Tickets;
    private JButton addToBasket;
    private JTextArea textArea1;
    private JTextField sessionId;
    private JTextField sessions;
    static private ObjectOutputStream output;

    public AfficheFrame(JFrame frame) throws ProjectException {
        //JFrame
        this.frame = frame;
        textArea1.removeAll();
        textArea1.append(SessionLogic.findAllSessions());
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
            frame.dispose();
            openUserMenu(frame);
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
