package by.bsuir.cinema.jframe;

import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.BasketLogic;
import by.bsuir.cinema.logic.SessionLogic;
import javax.swing.*;
import static by.bsuir.cinema.controller.Controller.user;

public class BasketFrame {
    private JTextArea orders;
    public JPanel getPanel;
    public JFrame frame;
    private JLabel yourOrders;
    private JButton back;
    private JButton buyTicket;
    private JTextField sessionId;
    private JButton cancelOrder;

    public BasketFrame(JFrame frame) throws ProjectException {
        //JFrame
        this.frame = frame;
        orders.removeAll();
        orders.append(BasketLogic.findAllSessionOfUser(user.getId()));

        buyTicket.addActionListener(e -> {
            try {
                if (BasketLogic.isEnoughMoney(((Client) user).getMoney(), Integer.parseInt(sessionId.getText()))){

                    JOptionPane.showMessageDialog(null,
                            "Билет был успешно добавлен в корзину");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "У вас недостаточно денег: пополните счет");
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
