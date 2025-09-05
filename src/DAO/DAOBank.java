package DAO;

import Model.Bank;

import java.sql.*;
import java.util.ArrayList;

public class DAOBank implements IDAO<Bank> {


    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:/Users/juanizimbi/Documents/IntelliJ projects/FinalLab1";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";



    @Override
    public void save(Bank bank) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO BANK VALUES(?,?,?,?)");
            preparedStatement.setString(1, bank.getName());
            preparedStatement.setString(2, bank.getAddress());
            preparedStatement.setString(3, bank.getUsername());
            preparedStatement.setString(4, bank.getPassword());

            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving bank");
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
            preparedStatement = connection.prepareStatement("DELETE FROM BANK WHERE NAME = ?");
            preparedStatement.setString(1, word);

            int i = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while deleting bank");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while deleting bank");
            }
        }

    }

    @Override
    public void update(Bank bank) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE BANK SET NAME = ? WHERE NAME = ?");
            preparedStatement.setString(1, bank.getName());

            int i = preparedStatement.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while updating bank");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while updating bank");
            }
        }

    }

    public Bank findLogin(String username, String password) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM BANK WHERE USERNAME = ? AND PASSWORD = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            Bank auxBank = null;
            if (resultSet.next()) {
                auxBank = new Bank();
                auxBank.setName(resultSet.getString("NAME"));
                auxBank.setAddress(resultSet.getString("ADDRESS"));
                auxBank.setUsername(resultSet.getString("USERNAME"));
                auxBank.setPassword(resultSet.getString("PASSWORD"));

            }

            return auxBank;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding bank");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding bank");
            }
        }
    }

    @Override
    public Bank findById(String word) throws DAOException
    {
        return null;
    }

    @Override
    public ArrayList<Bank> findAll() throws DAOException {
        return null;
    }
}
