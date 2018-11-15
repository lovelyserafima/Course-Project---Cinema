package by.bsuir.cinema.logic;

import by.bsuir.cinema.database.dao.BasketDao;
import by.bsuir.cinema.database.dao.DaoManager;
import by.bsuir.cinema.database.dao.TicketDao;
import by.bsuir.cinema.database.dao.UserDao;
import by.bsuir.cinema.exception.ProjectException;

import java.math.BigDecimal;

public class BasketLogic {
    public static boolean addToBasket(int userId, int sessionId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDao basketDao = new BasketDao();
        try{
            daoManager.startDAO(basketDao);
            boolean flag = basketDao.insertIntoBasket(userId, sessionId);
            daoManager.commit();
            return flag;
        } catch (ProjectException e){
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean isEnoughMoney(BigDecimal balance, int sessionId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDao basketDao = new BasketDao();
        try{
            daoManager.startDAO(basketDao);
            return basketDao.isEnoughMoney(balance, sessionId);
        } finally {
            daoManager.endDAO();
        }
    }

    public static String findAllSessionOfUser(int id) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        BasketDao basketDao = new BasketDao();
        try{
            daoManager.startDAO(basketDao);
            return basketDao.findOrdersByUserId(id);
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean buyTicket(int userId, int sessionId, BigDecimal userBalance){
        DaoManager daoManager = new DaoManager();
        TicketDao ticketDao = new TicketDao();
        UserDao userDao = new UserDao();
        try{
            daoManager.startDAO(ticketDao, userDao);
            ticketDao.insertIntoTicket(userId, sessionId);
            userDao.updateUserMoney(userId, sessionId, userBalance);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }
}

