package DAO;

import Model.Account;
import Model.Card;
import Model.CurrentAccount;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class DAOCard implements IDAO<Card> {

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:/Users/juanizimbi/Documents/IntelliJ projects/FinalLab1";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";


    @Override
    public void save(Card card) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO CARD VALUES(?,?,?)");
            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setDouble(2, card.getBalance());
            preparedStatement.setDouble(3, card.getExpense());

            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving card");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing statement");
            }

        }

    }

    public void save(Card card, String id, String cbu, String Alias) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO CARD VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, card.getCardNumber());
            preparedStatement.setDouble(2, card.getBalance());
            preparedStatement.setDouble(3, card.getExpense());
            preparedStatement.setString(4, id);
            preparedStatement.setString(5, cbu);
            preparedStatement.setString(6, Alias);

            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving card");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing statement");
            }

        }

    }

    @Override
    public void delete(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM CARD WHERE CARD_NUMBER = ?");
            preparedStatement.setString(1, word);

            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while deleting card");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing statement");
            }

        }

    }

    @Override
    public void update(Card card) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CARD SET BALANCE = ?, EXPENSE = ? WHERE CARD_NUMBER = ?");
            preparedStatement.setDouble(1, card.getBalance());
            preparedStatement.setDouble(2, card.getExpense());
            preparedStatement.setString(3, card.getCardNumber());

            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while updating card");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing statement");
            }

        }

    }

    public void updateMoreBalance(String cbu, double monto) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CARD SET BALANCE = BALANCE + ? where ID = ? OR CBU = ? OR ALIAS = ?");
            preparedStatement.setDouble(1, monto);
            preparedStatement.setString(2, cbu);
            preparedStatement.setString(3, cbu);
            preparedStatement.setString(4, cbu);

            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while updating card");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing statement");
            }

        }

    }

    public void updateLessBalance(String cbu, double monto) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CARD SET BALANCE = BALANCE - ? where ID = ? OR CBU = ? OR ALIAS = ?");
            preparedStatement.setDouble(1, monto);
            preparedStatement.setString(2, cbu);
            preparedStatement.setString(3, cbu);
            preparedStatement.setString(4, cbu);

            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while updating card");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing statement");
            }

        }

    }

    @Override
    public Card findById(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CARD WHERE CARD_NUMBER = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            Card cardAux = null;
            if (resultSet.next()) {
                cardAux = new Card();
                cardAux.setCardNumber(resultSet.getString("CARD_NUMBER"));
                cardAux.setBalance(resultSet.getDouble("BALANCE"));
                cardAux.setExpense(resultSet.getDouble("EXPENSE"));
            }

            return cardAux;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding card");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding card");
            }
        }
    }

    @Override
    public ArrayList<Card> findAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Card> arrayCard = new ArrayList<>();
        Card cardAux = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CARD");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cardAux = new Card();
                cardAux.setCardNumber(resultSet.getString("CARD_NUMBER"));
                cardAux.setBalance(resultSet.getDouble("BALANCE"));
                cardAux.setExpense(resultSet.getDouble("EXPENSE"));


                arrayCard.add(cardAux);

            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding current account");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding current account");
            }
        }

        return arrayCard;
    }

    public ArrayList<Card> findAll(String id, String cbu, String alias) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Card> arrayCard = new ArrayList<>();
        Card cardAux = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CARD WHERE ID = ? AND CBU = ? AND ALIAS = ?");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, cbu);
            preparedStatement.setString(3, alias);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                cardAux = new Card();
                cardAux.setCardNumber(resultSet.getString("CARD_NUMBER"));
                cardAux.setBalance(resultSet.getDouble("BALANCE"));
                cardAux.setExpense(resultSet.getDouble("EXPENSE"));


                arrayCard.add(cardAux);

            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding current account");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding current account");
            }
        }

        return arrayCard;

    }

}
