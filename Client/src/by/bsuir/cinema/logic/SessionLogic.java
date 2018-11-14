package by.bsuir.cinema.logic;

import by.bsuir.cinema.database.dao.DaoManager;
import by.bsuir.cinema.database.dao.SessionDao;
import by.bsuir.cinema.exception.ProjectException;

public class SessionLogic {
    public static String findAllSessions() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        SessionDao sessionDao = new SessionDao();
        try{
            daoManager.startDAO(sessionDao);
            return sessionDao.findAllSessions();
        } finally {
            daoManager.endDAO();
        }

    }
}
