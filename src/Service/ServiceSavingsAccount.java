package Service;

import DAO.DAOCurrentAccount;
import DAO.DAOException;
import DAO.DAOSavingsAcount;
import Model.CurrentAccount;
import Model.SavingsAccount;

import java.util.ArrayList;

public class ServiceSavingsAccount {

    private DAOSavingsAcount daoSavingsAcount;

    public ServiceSavingsAccount()
    {
        daoSavingsAcount = new DAOSavingsAcount();
    }

    public void save(SavingsAccount savingsAccount) throws ServiceException
    {
        try{
            daoSavingsAcount.save(savingsAccount);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void save(SavingsAccount savingsAccount, String dni) throws ServiceException
    {
        try{
            daoSavingsAcount.save(savingsAccount, dni);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void update(SavingsAccount savingsAccount) throws ServiceException
    {
        try{
            daoSavingsAcount.update(savingsAccount);
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteById(SavingsAccount savingsAccount) throws ServiceException
    {
        try{
            daoSavingsAcount.delete(savingsAccount.getId());
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteByCbu(SavingsAccount savingsAccount) throws ServiceException
    {
        try{
            daoSavingsAcount.delete(savingsAccount.getCbu());
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteByAlias(SavingsAccount savingsAccount) throws ServiceException
    {
        try{
            daoSavingsAcount.delete(savingsAccount.getAlias());
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public SavingsAccount findAccount(String word) throws ServiceException
    {
        SavingsAccount savingsAccount = null;
        try
        {
            savingsAccount = daoSavingsAcount.findAccount(word);
            return savingsAccount;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public SavingsAccount findById(String word) throws ServiceException
    {
        SavingsAccount savingsAccount = null;
        try{
            savingsAccount = daoSavingsAcount.findById(word);
            return savingsAccount;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public SavingsAccount findByCbu(String word) throws ServiceException
    {
        SavingsAccount savingsAccount = null;
        try{
            savingsAccount = daoSavingsAcount.findByCbu(word);
            return savingsAccount;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public SavingsAccount findByAlias(String word) throws ServiceException
    {
        SavingsAccount savingsAccount = null;
        try{
            savingsAccount = daoSavingsAcount.findByAlias(word);
            return savingsAccount;
        } catch (Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<SavingsAccount> findAll() throws ServiceException
    {
        ArrayList<SavingsAccount> savingsAccountArrayList;

        try
        {
            savingsAccountArrayList = daoSavingsAcount.findAll();
            return savingsAccountArrayList;
        } catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<SavingsAccount> findByDni(String word) throws ServiceException {
        ArrayList<SavingsAccount> savingsAccountArrayList;
        try
        {
            savingsAccountArrayList = daoSavingsAcount.findByDni(word);
            return savingsAccountArrayList;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
