package by.bsuir.cinema.logic;

import by.bsuir.cinema.database.dao.DaoManager;
import by.bsuir.cinema.database.dao.TicketDao;
import by.bsuir.cinema.exception.ProjectException;

public class TicketsLogic {
    public static String findAllTickets(int userId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        TicketDao ticketDao = new TicketDao();
        try{
            daoManager.startDAO(ticketDao);
            return ticketDao.findAllTickets(userId);
        } finally {
            daoManager.endDAO();
        }
    }
}
