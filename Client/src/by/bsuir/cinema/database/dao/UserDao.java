package by.bsuir.cinema.database.dao;

import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.entity.user.TypeUser;
import by.bsuir.cinema.entity.user.User;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.util.constant.ConstantFields;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao {
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "select * from User where login = ? and password = ?";
    private static final String FIND_CLIENT_BY_ID = "select * from Client where user_id = ?";

    public User findUserByLoginAndPassword(String login, String password) throws ProjectException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if (resultSet.getString(4).toUpperCase().equals(ConstantFields.ADMIN)){
                    return new User(resultSet.getInt(1), resultSet.getString(2),
                            TypeUser.valueOf(resultSet.getString(4).toUpperCase()));
                } else {
                    PreparedStatement preparedStatementForClient = connection.prepareStatement(FIND_CLIENT_BY_ID);
                    preparedStatementForClient.setInt(1, resultSet.getInt(1));
                    ResultSet resultSetForClient = preparedStatementForClient.executeQuery();
                    if (resultSetForClient.next()){
                        return new Client(resultSet.getInt(1), resultSet.getString(2),
                                resultSetForClient.getBigDecimal(2),
                                TypeUser.valueOf(resultSet.getString(4).toUpperCase()));
                    }
                }
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return null;
    }
}
