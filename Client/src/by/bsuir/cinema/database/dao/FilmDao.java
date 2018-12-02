package by.bsuir.cinema.database.dao;

import by.bsuir.cinema.exception.ProjectException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FilmDao extends AbstractDao {
    private static final String INSERT_FILM = "insert into Film(name, genre, producers, main_roles) values(?, ?, ?, ?)";
    private static final String FIND_ID_BY_NAME = "select id from Film where name = ?";
    private static final String FIND_ALL = "select * from Film";
    private static final String DELETE_FILM_BY_ID = "delete from Film where id = ?";

    public boolean insertFilm(String name, String filmGenre, String producers, String mainRoles) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(INSERT_FILM);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, filmGenre);
            preparedStatement.setString(3, producers);
            preparedStatement.setString(4, mainRoles);
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

    public int findFilmIdByName (String name) throws ProjectException {
        PreparedStatement statement = null;
        int id = 0;
        try{
            statement = connection.prepareStatement(FIND_ID_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(statement);
            }
        }
        return id;
    }

    public String findAll() throws ProjectException {
        Statement statement = null;
        String allFilms = "";
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()){
                allFilms += resultSet.getInt(1) + "\n Название фильма - " +
                        resultSet.getString(2) + ", \nЖанр - " + resultSet.getString(3) +
                        ", \nРежиссеры " + resultSet.getString(4) + ", \nВ главных ролях " +
                        resultSet.getString(5) + "\n";
            }
        } catch (SQLException e) {
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(statement);
            }
        }
        return allFilms;
    }

    public boolean deleteFilmById(int filmId) throws ProjectException {
        PreparedStatement preparedStatement = null;
        boolean flag;
        try{
            preparedStatement = connection.prepareStatement(DELETE_FILM_BY_ID);
            preparedStatement.setInt(1, filmId);
            flag = preparedStatement.executeUpdate() != 0;
        } catch (SQLException e){
            throw new ProjectException("SQLException, ", e);
        } finally {
            if (connection != null){
                close(preparedStatement);
            }
        }
        return flag;
    }
}
