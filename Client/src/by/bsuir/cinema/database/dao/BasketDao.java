package by.bsuir.cinema.database.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BasketDao extends AbstractDao {
    private static final String INSERT_INTO_BASKET = "insert into Basket(user_id, session_id) values(?, ?);";

    public boolean insertIntoBasket(int userId, int sessionId){
        PreparedStatement preparedStatement = null;
        boolean flag = false;
        try{
            preparedStatement = connection.prepareStatement(INSERT_INTO_BASKET);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, sessionId);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
