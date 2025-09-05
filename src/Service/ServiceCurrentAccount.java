package Service;

import DAO.DAOCurrentAccount;
import DAO.DAOException;
import Model.Account;
import Model.CurrentAccount;

import java.util.ArrayList;

public class ServiceCurrentAccount {

    private DAOCurrentAccount daoCurrentAccount;

    public ServiceCurrentAccount()
    {
        daoCurrentAccount = new DAOCurrentAccount();
    }

    public void save(CurrentAccount currentAccount) throws ServiceException
    {
        try{
            daoCurrentAccount.save(currentAccount);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void save(CurrentAccount currentAccount, String dni) throws ServiceException
    {
        try{
            daoCurrentAccount.save(currentAccount, dni);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void update(CurrentAccount currentAccount) throws ServiceException
    {
        try{
            daoCurrentAccount.update(currentAccount);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteById(CurrentAccount currentAccount) throws ServiceException
    {
        try{
            daoCurrentAccount.delete(currentAccount.getId());
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteByCbu(CurrentAccount currentAccount) throws ServiceException
    {
        try{
            daoCurrentAccount.delete(currentAccount.getCbu());
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteByAlias(CurrentAccount currentAccount) throws ServiceException
    {
        try{
            daoCurrentAccount.delete(currentAccount.getAlias());
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public CurrentAccount findAccount(String word) throws ServiceException
    {
        CurrentAccount currentAccount = null;
        try
        {
            currentAccount = daoCurrentAccount.findAccount(word);
            return currentAccount;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    public CurrentAccount findById(String word) throws ServiceException
    {
        CurrentAccount currentAccount = null;
        try{
            currentAccount = daoCurrentAccount.findById(word);
            return currentAccount;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public CurrentAccount findByCbu(String word) throws ServiceException
    {
        CurrentAccount currentAccount = null;
        try{
            currentAccount = daoCurrentAccount.findByCbu(word);
            return currentAccount;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public CurrentAccount findByAlias(String word) throws ServiceException
    {
        CurrentAccount currentAccount = null;
        try{
            currentAccount = daoCurrentAccount.findByAlias(word);
            return currentAccount;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<CurrentAccount> findAll() throws ServiceException
    {
        ArrayList<CurrentAccount> currentAccounts;

        try{
            currentAccounts = daoCurrentAccount.findAll();
            return currentAccounts;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<CurrentAccount> findByDni(String word) throws ServiceException {
        ArrayList<CurrentAccount> currentAccounts;
        try
        {
            currentAccounts = daoCurrentAccount.findByDni(word);
            return currentAccounts;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
