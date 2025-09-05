package Service;

import DAO.DAOBank;
import Model.Bank;

public class ServiceBank {

    private DAOBank daoBank;

    public ServiceBank()
    {
        daoBank = new DAOBank();
    }

    public void saveBank(Bank bank) throws ServiceException
    {
        try
        {
            daoBank.save(bank);
        } catch(Exception e){
            throw new ServiceException(e.getMessage());

        }
    }

    public void updateBank(Bank bank) throws ServiceException
    {
        try{
            daoBank.update(bank);
        }catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteBank(Bank bank) throws ServiceException
    {
        try{
            daoBank.delete(bank.getName());
        }catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public Bank findLogin(String username, String password) throws ServiceException
    {
        Bank bank = null;
        try{
            bank = daoBank.findLogin(username, password);
            return bank;
        } catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }


}
