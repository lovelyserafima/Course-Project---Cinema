package by.bsuir.cinema.database.dao;

import by.bsuir.cinema.exception.ProjectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDao extends AbstractDao {
    private static final String INSERT_INTO_TICKET = "insert into Ticket(user_id, session_id) values(?, ?)";
    private static final String FIND_ALL_TICKETS = "select Ticket.session_id, name, genre, producers, main_roles, " +
            "date_time from Film join Session on film_id = Film.id join Ticket on Session.id = Ticket.session_id " +
            "where Ticket.user_id = ?";

    public String findAllTickets(int userId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(FIND_ALL_TICKETS);
            preparedStatement.setInt(1, userId);
            String allTickets = "";
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allTickets += resultSet.getInt(1) + " Название фильма - " +
                        resultSet.getString(2) + ", Жанр - " + resultSet.getString(3) +
                        ", Режиссеры " + resultSet.getString(4) + ", В главных ролях " +
                        resultSet.getString(5) + ", Время " + resultSet.getString(6) + "\n";
            }
            return allTickets;
        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
    }

    public boolean insertIntoTicket(int userId, int sessionId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try{
            preparedStatement = connection.prepareStatement(INSERT_INTO_TICKET);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, sessionId);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }
}
