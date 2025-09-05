package Service;

import DAO.DAOCard;
import DAO.DAOException;
import DAO.DAOUser;
import Model.Account;
import Model.Card;
import Model.User;

import java.util.ArrayList;

public class ServiceCard {

    private DAOCard daoCard;

    public ServiceCard()
    {
        daoCard = new DAOCard();
    }

    public void saveCard(Card card) throws ServiceException
    {
        try
        {
            daoCard.save(card);
        } catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void saveCard(Card card, String id, String cbu, String Alias) throws ServiceException
    {
        try
        {
            daoCard.save(card, id, cbu, Alias);
        } catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateCard(Card card) throws ServiceException
    {
        try{
            daoCard.update(card);
        } catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateMoreBalance(String cbu, double monto) throws ServiceException
    {
        try
        {
            daoCard.updateMoreBalance(cbu, monto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateLessBalance(String cbu, double monto) throws ServiceException
    {
        try
        {
            daoCard.updateLessBalance(cbu, monto);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteCard(Card card) throws ServiceException
    {
        try
        {
            daoCard.delete(card.getCardNumber());
        } catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }

    public void findCard(Card card) throws ServiceException
    {
        try{
            daoCard.findById(card.getCardNumber());
        } catch(Exception e)
        {
            throw new ServiceException(e.getMessage());
        }
    }


    public ArrayList<Card> findAll(String id, String cbu, String alias) throws ServiceException
    {
        ArrayList<Card> cards = null;
        try
        {
            cards = daoCard.findAll(id, cbu, alias);
            return cards;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList<Card> findAll() throws ServiceException
    {
        ArrayList<Card> cards = null;
        try
        {
            cards = daoCard.findAll();
            return cards;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }



}
