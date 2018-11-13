package by.bsuir.cinema.logic;

import by.bsuir.cinema.database.dao.DaoManager;
import by.bsuir.cinema.database.dao.UserDao;
import by.bsuir.cinema.entity.user.User;
import by.bsuir.cinema.exception.ProjectException;

public class UserLogic {
    public static User findUser(String login, String password)  {
        DaoManager daoManager = new DaoManager();
        UserDao userDao = new UserDao();
        try {
            daoManager.startDAO(userDao);
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (ProjectException e) {
            e.printStackTrace();
        } finally {
            daoManager.endDAO();
        }
        return null;
    }
}
