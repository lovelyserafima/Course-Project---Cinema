package by.bsuir.cinema.logic;

import by.bsuir.cinema.database.dao.DaoManager;
import by.bsuir.cinema.database.dao.FilmDao;
import by.bsuir.cinema.exception.ProjectException;

public class FilmLogic {
    public static boolean addNewFilm (String name, String filmGenre, String producers, String mainRoles)
            throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        boolean flag;
        try{
            daoManager.startDAO(filmDao);
            flag = filmDao.insertFilm(name, filmGenre, producers, mainRoles);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }

    public static String findAllFilms() throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        try{
            daoManager.startDAO(filmDao);
            return filmDao.findAll();
        } finally {
            daoManager.endDAO();
        }
    }

    public static boolean deleteFilmById(int filmId) throws ProjectException {
        DaoManager daoManager = new DaoManager();
        FilmDao filmDao = new FilmDao();
        boolean flag;
        try{
            daoManager.startDAO(filmDao);
            flag = filmDao.deleteFilmById(filmId);
            daoManager.commit();
        } catch (ProjectException e) {
            daoManager.rollback();
            throw e;
        } finally {
            daoManager.endDAO();
        }
        return flag;
    }
}
