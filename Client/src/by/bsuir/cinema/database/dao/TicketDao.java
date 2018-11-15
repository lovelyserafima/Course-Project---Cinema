package by.bsuir.cinema.database.dao;

import by.bsuir.cinema.exception.ProjectException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDao extends AbstractDao {
    private static final String INSERT_INTO_TICKET = "insert into Ticket(user_id, session_id) values(?, ?)";

    public boolean insertIntoTicket(int userId, int sessionId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try{
            preparedStatement = connection.prepareStatement(INSERT_INTO_TICKET);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, sessionId);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }
}
