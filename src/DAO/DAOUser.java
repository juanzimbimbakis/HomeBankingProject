package DAO;

import Model.*;
import Service.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DAOUser implements IDAO<User>{

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:/Users/juanizimbi/Documents/IntelliJ projects/FinalLab1";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";


    @Override
    public void save(User user) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getDni());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getUsername());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setString(8, "Chase Bank");


            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving user");
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
            preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE DNI = ?");
            preparedStatement.setString(1, word);

            int i = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while deleting users");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while deleting users");
            }
        }

    }

    public void goodDelete(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE DNI = ?");
            preparedStatement.setString(1, word);

            int i = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while deleting users");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while deleting users");
            }
        }

    }

    @Override
    public void update(User user) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE USERS SET EMAIL = ?, PHONE_NUMBER = ?, USERNAME = ?, PASSWORD = ? WHERE DNI = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getDni());

            int i = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while updating user");
        } finally {
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while updating user");
            }
        }

    }

    @Override
    public User findById(String word) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE DNI = ?");
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();

            User userAux = null;
            if (resultSet.next()) {
                userAux = new User();
                userAux.setName(resultSet.getString("NAME"));
                userAux.setSurname(resultSet.getString("SURNAME"));
                userAux.setDni(resultSet.getString("DNI"));
                userAux.setEmail(resultSet.getString("EMAIL"));
                userAux.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                userAux.setUsername(resultSet.getString("USERNAME"));
                userAux.setPassword(resultSet.getString("PASSWORD"));

            }

            return userAux;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding user");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding user");
            }
        }

    }

    public User findLogin(String username, String password) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            User userAux = null;
            if (resultSet.next()) {
                userAux = new User();
                userAux.setName(resultSet.getString("NAME"));
                userAux.setSurname(resultSet.getString("SURNAME"));
                userAux.setDni(resultSet.getString("DNI"));
                userAux.setEmail(resultSet.getString("EMAIL"));
                userAux.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                userAux.setUsername(resultSet.getString("USERNAME"));
                userAux.setPassword(resultSet.getString("PASSWORD"));


                ArrayList<CurrentAccount> arrayCA = null;
                ArrayList<SavingsAccount> arraySA = null;
                ArrayList<Account> arrayAccount = new ArrayList<>();

                ServiceCurrentAccount serviceCurrentAccount = new ServiceCurrentAccount();
                ServiceSavingsAccount serviceSavingsAccount = new ServiceSavingsAccount();

                try
                {
                    arrayCA = serviceCurrentAccount.findByDni(userAux.getDni());
                } catch (ServiceException e)
                {
                    JOptionPane.showMessageDialog(null, "Error al traer CA en DAOUser", "Error", JOptionPane.ERROR_MESSAGE);
                }
                try
                {
                    arraySA = serviceSavingsAccount.findByDni(userAux.getDni());
                } catch (ServiceException e)
                {
                    JOptionPane.showMessageDialog(null, "Error al traer SA en DAOUser", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(arraySA != null)
                {
                    arrayAccount.addAll(arraySA);
                }
                if(arrayCA != null)
                {
                    arrayAccount.addAll(arrayCA);
                }

                /*
                ServiceTransfer serviceTransfer = new ServiceTransfer();

                for(Account account : arrayAccount)
                {
                    try
                    {
                        ArrayList<Transfer> arrayTransfer = serviceTransfer.findByWord(account.getCbu());
                        account.setArrayTransfer(arrayTransfer);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al traer transfer en DAOUser", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                 */
                userAux.setArrayAccount(arrayAccount);

            }

            return userAux;


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding user");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding user");
            }
        }

    }


    public ArrayList<User> findAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<User> userArrayList = new ArrayList<>();
        User userAux = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                userAux = new User();
                userAux.setName(resultSet.getString("NAME"));
                userAux.setSurname(resultSet.getString("SURNAME"));
                userAux.setDni(resultSet.getString("DNI"));
                userAux.setEmail(resultSet.getString("EMAIL"));
                userAux.setPhoneNumber(resultSet.getString("PHONE_NUMBER"));
                userAux.setUsername(resultSet.getString("USERNAME"));
                userAux.setPassword(resultSet.getString("PASSWORD"));


                userArrayList.add(userAux);

            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException("Error while finding all users");
        } finally {
            try{
                preparedStatement.close();
            }catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding all users");
            }
        }

        return userArrayList;
    }
}
