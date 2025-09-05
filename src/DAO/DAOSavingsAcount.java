package DAO;

import Model.CurrentAccount;
import Model.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;

public class DAOSavingsAcount implements IDAO<SavingsAccount>{

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:/Users/juanizimbi/Documents/IntelliJ projects/FinalLab1";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";


    @Override
    public void save(SavingsAccount savingsAccount) throws DAOException {

    }

    public void save(SavingsAccount savingsAccount, String dni) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, savingsAccount.getId());
            preparedStatement.setString(2, savingsAccount.getCbu());
            preparedStatement.setString(3, savingsAccount.getAlias());
            preparedStatement.setDouble(4, savingsAccount.getBalance());
            preparedStatement.setDouble(5, savingsAccount.getDebit());
            preparedStatement.setDouble(6, savingsAccount.getCredit());
            preparedStatement.setString(7, "SA");
            preparedStatement.setString(8, dni);



            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving Savings account");
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
            preparedStatement = connection.prepareStatement("DELETE FROM ACCOUNT WHERE ID = ? OR CBU = ? OR ALIAS = ?");
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, word);
            preparedStatement.setString(3, word);

            int i = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while deleting savings account");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while deleting avings account");
            }
        }

    }

    @Override
    public void update(SavingsAccount savingsAccount) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE ACCOUNT SET BALANCE = ?, DEBIT = ?, CREDIT = ? WHERE ID = ? OR ALIAS = ? OR CBU = ?");
            preparedStatement.setDouble(1, savingsAccount.getBalance());
            preparedStatement.setDouble(2, savingsAccount.getDebit());
            preparedStatement.setDouble(3, savingsAccount.getCredit());
            preparedStatement.setString(4, savingsAccount.getId());
            preparedStatement.setString(5, savingsAccount.getAlias());
            preparedStatement.setString(6, savingsAccount.getCbu());


            int i = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while updating savings account");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while updating savings account");
            }
        }

    }

    public SavingsAccount findAccount(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE (ID = ? OR CBU = ? OR ALIAS = ?) AND TYPE = ?");
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, word);
            preparedStatement.setString(3, word);
            preparedStatement.setString(4, "SA");
            ResultSet resultSet = preparedStatement.executeQuery();

            SavingsAccount savingsAccount  = null;
            if (resultSet.next()) {
                savingsAccount = new SavingsAccount();
                savingsAccount.setId(resultSet.getString("ID"));
                savingsAccount.setCbu(resultSet.getString("CBU"));
                savingsAccount.setAlias(resultSet.getString("ALIAS"));
                savingsAccount.setBalance(resultSet.getDouble("BALANCE"));
                savingsAccount.setDebit(resultSet.getDouble("DEBIT"));
                savingsAccount.setCredit(resultSet.getDouble("CREDIT"));
            }

            return savingsAccount;


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

    }

    @Override
    public SavingsAccount findById(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE DNI = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            SavingsAccount savingsAccountAux = null;
            if (resultSet.next()) {
                savingsAccountAux = new SavingsAccount();
                savingsAccountAux.setId(resultSet.getString("ID"));
                savingsAccountAux.setCbu(resultSet.getString("CBU"));
                savingsAccountAux.setAlias(resultSet.getString("ALIAS"));
                savingsAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                savingsAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                savingsAccountAux.setCredit(resultSet.getDouble("CREDIT"));
            }

            return savingsAccountAux;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding savings account");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding savings account");
            }
        }
    }

    public SavingsAccount findByAlias(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE ALIAS = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            SavingsAccount savingsAccountAux = null;
            if (resultSet.next()) {
                savingsAccountAux = new SavingsAccount();
                savingsAccountAux.setId(resultSet.getString("ID"));
                savingsAccountAux.setCbu(resultSet.getString("CBU"));
                savingsAccountAux.setAlias(resultSet.getString("ALIAS"));
                savingsAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                savingsAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                savingsAccountAux.setCredit(resultSet.getDouble("CREDIT"));
            }

            return savingsAccountAux;


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

    }

    public SavingsAccount findByCbu(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE CBU = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            SavingsAccount savingsAccountAux = null;
            if (resultSet.next()) {
                savingsAccountAux = new SavingsAccount();
                savingsAccountAux.setId(resultSet.getString("ID"));
                savingsAccountAux.setCbu(resultSet.getString("CBU"));
                savingsAccountAux.setAlias(resultSet.getString("ALIAS"));
                savingsAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                savingsAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                savingsAccountAux.setCredit(resultSet.getDouble("CREDIT"));
            }

            return savingsAccountAux;


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

    }

    public ArrayList<SavingsAccount> findByDni(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<SavingsAccount> arraySavingsAccount = new ArrayList<>();
        SavingsAccount savingsAccountAux = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE DNI = ? AND TYPE = ?");
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, "SA");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                savingsAccountAux = new SavingsAccount();
                savingsAccountAux.setId(resultSet.getString("ID"));
                savingsAccountAux.setCbu(resultSet.getString("CBU"));
                savingsAccountAux.setAlias(resultSet.getString("ALIAS"));
                savingsAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                savingsAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                savingsAccountAux.setCredit(resultSet.getDouble("CREDIT"));

                arraySavingsAccount.add(savingsAccountAux);

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

        return arraySavingsAccount;

    }

    @Override
    public ArrayList<SavingsAccount> findAll() throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<SavingsAccount> arraySavingsAccount = new ArrayList<>();
        SavingsAccount savingsAccountAux = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE TYPE = ?");
            preparedStatement.setString(1, "CA");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                savingsAccountAux = new SavingsAccount();
                savingsAccountAux.setId(resultSet.getString("ID"));
                savingsAccountAux.setCbu(resultSet.getString("CBU"));
                savingsAccountAux.setAlias(resultSet.getString("ALIAS"));
                savingsAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                savingsAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                savingsAccountAux.setCredit(resultSet.getDouble("CREDIT"));

                arraySavingsAccount.add(savingsAccountAux);

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

        return arraySavingsAccount;

    }
}
