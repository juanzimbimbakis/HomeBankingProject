package Service;

import DAO.DAOUser;
import Model.User;

import java.util.ArrayList;

public class ServiceUser {

    private DAOUser daoUser;

    public ServiceUser()
    {
        daoUser = new DAOUser();
    }

    public void saveUser(User user) throws ServiceException
    {
        try{
            daoUser.save(user);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateUser(User user) throws ServiceException
    {
        try{
            daoUser.update(user);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteUser(User user) throws ServiceException
    {
        try{
            daoUser.delete(user.getDni());
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public User findUser(String dni) throws ServiceException
    {
        User user = null;
        try{
            user = daoUser.findById(dni);
            return user;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public User findLogin(String username, String password) throws ServiceException
    {
        User user = null;
        try
        {
            user = daoUser.findLogin(username, password);
            return user;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<User> findAll() throws ServiceException
    {
        ArrayList<User> users;

        try{
            users = daoUser.findAll();
            return users;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

}
