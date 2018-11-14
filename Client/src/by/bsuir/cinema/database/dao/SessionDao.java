package by.bsuir.cinema.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionDao extends AbstractDao {
    private static final String FIND_ALL_SESSIONS = "select Session.id, name, genre, producers, main_roles, date_time, " +
            "price from Session join Film on film_id = Film.id";

    public String findAllSessions(){
        String allSessions = "";
        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SESSIONS);
            while (resultSet.next()){
                allSessions += resultSet.getInt(1) + " Название фильма - " +
                        resultSet.getString(2) + "\nЖанр - " + resultSet.getString(3) +
                        "\nРежиссеры " + resultSet.getString(4) + "\nВ главных ролях " +
                        resultSet.getString(5) + "\nВремя " + resultSet.getString(6) +
                        "\nЦена в синемакоинах " + resultSet.getString(7) + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSessions;
    }

}
