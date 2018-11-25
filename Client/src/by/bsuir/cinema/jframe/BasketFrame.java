package by.bsuir.cinema.jframe;

import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.BasketLogic;

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
                int integerSessionId = Integer.parseInt(sessionId.getText());
                if (BasketLogic.isEnoughMoney(((Client) user).getMoney(), integerSessionId)){
                    ((Client) user).setMoney(BasketLogic.buyTicket(user.getId(), integerSessionId,
                            ((Client) user).getMoney()));
                    JOptionPane.showMessageDialog(null,
                            "Билет был успешно куплен");
                    BasketLogic.deleteFromBasket(user.getId(), integerSessionId);
                    openUserMenu(this.frame);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "У вас недостаточно денег: пополните счет");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null,
                        "Упс, что-то пошло не так:(");
            }
        });

        cancelOrder.addActionListener(e -> {
            int integerSessionId = Integer.parseInt(sessionId.getText());
            try {
                if (BasketLogic.deleteFromBasket(user.getId(), integerSessionId)){
                    JOptionPane.showMessageDialog(null, "Билет был успешно отменен");
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
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
