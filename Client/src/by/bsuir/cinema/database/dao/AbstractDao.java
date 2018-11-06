package by.bsuir.cinema.database.dao;

import by.bsuir.cinema.database.pool.ProxyConnection;
import by.bsuir.cinema.entity.Entity;
import by.bsuir.cinema.exception.ProjectException;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao <T extends Entity>  {
    protected ProxyConnection connection;

    public void setConnection(ProxyConnection connection){
        this.connection = connection;
    }

    public void close(Statement statement) throws ProjectException {
        try{
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new ProjectException("CloseStatementException!", e);
        }
    }
}
