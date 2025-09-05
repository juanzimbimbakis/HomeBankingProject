package Service;

import DAO.DAOException;
import DAO.DAOTransfer;
import Model.Transfer;

import java.util.ArrayList;

public class ServiceTransfer {

    private DAOTransfer daoTransfer;

    public ServiceTransfer()
    {
        this.daoTransfer = new DAOTransfer();
    }

    public void saveTransfer(Transfer transfer, String id, String cbu, String alias) throws ServiceException
    {
        try
        {
            daoTransfer.save(transfer, id, cbu, alias);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Transfer> findByWord(String word) throws ServiceException
    {
        ArrayList<Transfer> list;
        try
        {
            list = daoTransfer.findAllByWord(word);
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
