package by.bsuir.cinema.database.dao;

import by.bsuir.cinema.exception.ProjectException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDao extends AbstractDao {
    private static final String FIND_ALL_SESSIONS = "select Session.id, name, genre, producers, main_roles, date_time, " +
            "price from Session join Film on film_id = Film.id";

    /*select Session.id, name, genre, producers, main_roles, date_time, price from
    Film join Session on film_id = Film.id join Basket
    on Session.id = Basket.session_id where Basket.user_id = 3*/

    public String findAllSessions() throws ProjectException {
        String allSessions = "";
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SESSIONS);
            while (resultSet.next()){
                allSessions += resultSet.getInt(1) + " Название фильма - " +
                        resultSet.getString(2) + ", Жанр - " + resultSet.getString(3) +
                        ", Режиссеры " + resultSet.getString(4) + ", В главных ролях " +
                        resultSet.getString(5) + " Время " + resultSet.getString(6) +
                        ", Цена в синемакоинах " + resultSet.getString(7) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                close(statement);
            }
        }
        return allSessions;
    }

}
