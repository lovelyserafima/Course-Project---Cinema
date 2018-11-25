package by.bsuir.cinema.database.dao;

import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.entity.user.TypeUser;
import by.bsuir.cinema.entity.user.User;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.util.constant.ConstantFields;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao {
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "select * from User where login = ? and password = ?";
    private static final String FIND_CLIENT_BY_ID = "select * from Client where user_id = ?";
    private static final String FIND_SESSION_PRICE = "select price from Session join Basket on session_id = id where " +
            "session_id = ?";
    private static final String UPDATE_CLIENT_MONEY = "update Client set cash = ? where user_id = ?";

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

    public boolean updateUserMoney(BigDecimal value, Client client) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(UPDATE_CLIENT_MONEY);
            BigDecimal newBalance = BigDecimal.valueOf(client.getMoney().doubleValue() + value.doubleValue());
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setInt(2, client.getId());
            flag = preparedStatement.executeUpdate() != 0;
            client.setMoney(newBalance);
        } catch (SQLException e) {
            throw new ProjectException("UpdateUserMoneyException", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }

    public BigDecimal updateUserMoney(int userId, int sessionId, BigDecimal userBalance) throws ProjectException {
        PreparedStatement preparedStatement = null;
        BigDecimal newBalance = BigDecimal.valueOf(-1);
        try{
            preparedStatement = connection.prepareStatement(FIND_SESSION_PRICE);
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                BigDecimal sessionPrice = resultSet.getBigDecimal(1);
                preparedStatement = connection.prepareStatement(UPDATE_CLIENT_MONEY);
                newBalance = BigDecimal.valueOf(userBalance.doubleValue() - sessionPrice.doubleValue());
                preparedStatement.setBigDecimal(1, newBalance);
                preparedStatement.setInt(2, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ProjectException("UpdateUserMoneyException", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return newBalance;
    }
}
