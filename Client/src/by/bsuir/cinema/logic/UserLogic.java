package by.bsuir.cinema.logic;

import by.bsuir.cinema.database.dao.DaoManager;
import by.bsuir.cinema.database.dao.UserDao;
import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.entity.user.User;
import by.bsuir.cinema.exception.ProjectException;

import java.math.BigDecimal;

public class UserLogic {
    public static User findUser(String login, String password) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDao userDao = new UserDao();
        try {
            daoManager.startDAO(userDao);
            return userDao.findUserByLoginAndPassword(login, password);
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean updateUserMoney(BigDecimal value, Client client) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        UserDao userDao = new UserDao();
        boolean flag;
        try{
            daoManager.startDAO(userDao);
            flag = userDao.updateUserMoney(value, client);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }
}
