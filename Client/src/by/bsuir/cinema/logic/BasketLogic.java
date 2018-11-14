package by.bsuir.cinema.logic;

import by.bsuir.cinema.database.dao.BasketDao;
import by.bsuir.cinema.database.dao.DaoManager;
import by.bsuir.cinema.exception.ProjectException;

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
}

