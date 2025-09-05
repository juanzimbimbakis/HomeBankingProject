package DAO;

import java.util.ArrayList;

public interface IDAO <T>{

    public void save(T t) throws DAOException;

    public void delete(String word) throws DAOException;

    public void update(T t) throws DAOException;

    public T findById(String word) throws DAOException;

    public ArrayList<T> findAll() throws DAOException;
}
