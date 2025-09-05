package DAO;

import Model.CurrentAccount;
import Model.Transfer;

import java.sql.*;
import java.util.ArrayList;

public class DAOTransfer implements IDAO<Transfer> {

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:/Users/juanizimbi/Documents/IntelliJ projects/FinalLab1";
    private String DB_USER = "sa";
    private String DB_PASSWORD = "";


    @Override
    public void save(Transfer transfer) throws DAOException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO TRANSFER VALUES(?,?,?)");
            preparedStatement.setString(1, transfer.getTransferId());
            preparedStatement.setDouble(2, transfer.getAmount());
            preparedStatement.setDate(3, java.sql.Date.valueOf(transfer.getDate()));


            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving transfer");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing transfer statement");
            }

        }


    }

    public void save(Transfer transfer, String id, String cbu, String alias) throws DAOException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO TRANSFER (AMOUNT, DATE, ID, CBU, ALIAS) VALUES(?,?,?,?,?)");
            preparedStatement.setDouble(1, transfer.getAmount());
            preparedStatement.setDate(2, java.sql.Date.valueOf(transfer.getDate()));
            preparedStatement.setString(3, id);
            preparedStatement.setString(4, cbu);
            preparedStatement.setString(5, alias);



            int i = preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            throw new DAOException("Error while saving transfer");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while closing transfer statement");
            }

        }


    }

    public ArrayList<Transfer> findAllByWord(String word) throws DAOException {


            Connection connection = null;
            PreparedStatement preparedStatement = null;

            ArrayList<Transfer> transfers = new ArrayList<>();
            Transfer transferAux = null;

            try {
                Class.forName(DB_JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                preparedStatement = connection.prepareStatement("SELECT * FROM TRANSFER WHERE ID = ? OR CBU = ? OR ALIAS = ?");
                preparedStatement.setString(1, word);
                preparedStatement.setString(2, word);
                preparedStatement.setString(3, word);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    transferAux = new Transfer();
                    transferAux.setTransferId(resultSet.getString("TRANSFER_ID"));
                    transferAux.setAmount(resultSet.getDouble("AMOUNT"));
                    transferAux.setDate(resultSet.getDate("DATE").toLocalDate());

                    transfers.add(transferAux);

                }


            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new DAOException("Error while finding transfers");
            } finally {
                try{
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                    throw new DAOException("Error while finding transfers");
                }
            }

            return transfers;

    }

    @Override
    public void delete(String word) throws DAOException {

    }

    @Override
    public void update(Transfer transfer) throws DAOException {

    }

    @Override
    public Transfer findById(String word) throws DAOException {
        return null;
    }

    @Override
    public ArrayList<Transfer> findAll() throws DAOException {
        return null;
    }
}
