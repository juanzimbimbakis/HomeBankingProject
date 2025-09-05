package DAO;

import Model.CurrentAccount;
import Model.User;

import java.sql.*;
import java.util.ArrayList;

public class DAOCurrentAccount implements IDAO<CurrentAccount> {

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:/Users/juanizimbi/Documents/IntelliJ projects/FinalLab1";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";


    @Override
    public void save(CurrentAccount currentAccount) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?,?,)");
            preparedStatement.setString(1, currentAccount.getId());
            preparedStatement.setString(2, currentAccount.getCbu());
            preparedStatement.setString(3, currentAccount.getAlias());
            preparedStatement.setDouble(4, currentAccount.getBalance());
            preparedStatement.setDouble(5, currentAccount.getDebit());
            preparedStatement.setDouble(6, currentAccount.getCredit());
            preparedStatement.setString(7, "CA");



            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving Current Account");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing statement");
            }

        }

    }

    public void save(CurrentAccount currentAccount, String dni) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, currentAccount.getId());
            preparedStatement.setString(2, currentAccount.getCbu());
            preparedStatement.setString(3, currentAccount.getAlias());
            preparedStatement.setDouble(4, currentAccount.getBalance());
            preparedStatement.setDouble(5, currentAccount.getDebit());
            preparedStatement.setDouble(6, currentAccount.getCredit());
            preparedStatement.setString(7, "CA");
            preparedStatement.setString(8, dni);



            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving Current Account");
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
            throw new DAOException("Error while deleting current account");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while deleting current account");
            }
        }

    }

    @Override
    public void update(CurrentAccount currentAccount) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE ACCOUNT SET BALANCE = ?, DEBIT = ?, CREDIT = ? WHERE ID = ?");
            preparedStatement.setDouble(1, currentAccount.getBalance());
            preparedStatement.setDouble(2, currentAccount.getDebit());
            preparedStatement.setDouble(3, currentAccount.getCredit());
            preparedStatement.setString(4, currentAccount.getId());


            int i = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while updating current account");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while updating current account");
            }
        }

    }

    @Override
    public CurrentAccount findById(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE ID = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            CurrentAccount currentAccountAux = null;
            if (resultSet.next()) {
                currentAccountAux = new CurrentAccount();
                currentAccountAux.setId(resultSet.getString("ID"));
                currentAccountAux.setCbu(resultSet.getString("CBU"));
                currentAccountAux.setAlias(resultSet.getString("ALIAS"));
                currentAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                currentAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                currentAccountAux.setCredit(resultSet.getDouble("CREDIT"));
            }

            return currentAccountAux;


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

    public CurrentAccount findByAlias(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE ALIAS = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            CurrentAccount currentAccountAux = null;
            if (resultSet.next()) {
                currentAccountAux = new CurrentAccount();
                currentAccountAux.setId(resultSet.getString("ID"));
                currentAccountAux.setCbu(resultSet.getString("CBU"));
                currentAccountAux.setAlias(resultSet.getString("ALIAS"));
                currentAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                currentAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                currentAccountAux.setCredit(resultSet.getDouble("CREDIT"));
            }

            return currentAccountAux;


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

    public CurrentAccount findAccount(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE (ID = ? OR CBU = ? OR ALIAS = ?) AND TYPE = ?");
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, word);
            preparedStatement.setString(3, word);
            preparedStatement.setString(4, "CA");
            ResultSet resultSet = preparedStatement.executeQuery();

            CurrentAccount currentAccountAux = null;
            if (resultSet.next()) {
                currentAccountAux = new CurrentAccount();
                currentAccountAux.setId(resultSet.getString("ID"));
                currentAccountAux.setCbu(resultSet.getString("CBU"));
                currentAccountAux.setAlias(resultSet.getString("ALIAS"));
                currentAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                currentAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                currentAccountAux.setCredit(resultSet.getDouble("CREDIT"));
            }

            return currentAccountAux;


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

    public CurrentAccount findByCbu(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE CBU = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            CurrentAccount currentAccountAux = null;
            if (resultSet.next()) {
                currentAccountAux = new CurrentAccount();
                currentAccountAux.setId(resultSet.getString("ID"));
                currentAccountAux.setCbu(resultSet.getString("CBU"));
                currentAccountAux.setAlias(resultSet.getString("ALIAS"));
                currentAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                currentAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                currentAccountAux.setCredit(resultSet.getDouble("CREDIT"));
            }

            return currentAccountAux;


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

    public ArrayList<CurrentAccount> findByDni(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<CurrentAccount> arrayCurrentAccounts = new ArrayList<>();
        CurrentAccount currentAccountAux = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE DNI = ? AND TYPE = ?");
            preparedStatement.setString(1, word);
            preparedStatement.setString(2, "CA");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                currentAccountAux = new CurrentAccount();
                currentAccountAux.setId(resultSet.getString("ID"));
                currentAccountAux.setCbu(resultSet.getString("CBU"));
                currentAccountAux.setAlias(resultSet.getString("ALIAS"));
                currentAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                currentAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                currentAccountAux.setCredit(resultSet.getDouble("CREDIT"));

                arrayCurrentAccounts.add(currentAccountAux);

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

        return arrayCurrentAccounts;

    }



    @Override
    public ArrayList<CurrentAccount> findAll() throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<CurrentAccount> arrayCurrentAccounts = new ArrayList<>();
        CurrentAccount currentAccountAux = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE TYPE = ?");
            preparedStatement.setString(1, "CA");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                currentAccountAux = new CurrentAccount();
                currentAccountAux.setId(resultSet.getString("ID"));
                currentAccountAux.setCbu(resultSet.getString("CBU"));
                currentAccountAux.setAlias(resultSet.getString("ALIAS"));
                currentAccountAux.setBalance(resultSet.getDouble("BALANCE"));
                currentAccountAux.setDebit(resultSet.getDouble("DEBIT"));
                currentAccountAux.setCredit(resultSet.getDouble("CREDIT"));

                arrayCurrentAccounts.add(currentAccountAux);

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

        return arrayCurrentAccounts;

    }

}
