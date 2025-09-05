package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.CurrentAccount;
import Model.SavingsAccount;
import Model.User;
import Service.ServiceCurrentAccount;
import Service.ServiceException;
import Service.ServiceSavingsAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PantallaVerCuentasDeCliente extends JPanel {

    // Grilla / Listado
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    // Botones
    private JPanel botones;
    private JButton botonAgregarTarjeta;
    private JButton botonVerTarjeta;
    private JButton botonAtras;

    private PanelManager panelManager;

    private ServiceCurrentAccount serviceCurrentAccount;
    private ServiceSavingsAccount serviceSavingsAccount;

    private String word;
    private String dni;

    public PantallaVerCuentasDeCliente(PanelManager panelManager, String word, String dni)
    {
        this.panelManager = panelManager;
        this.word = word;
        this.dni = dni;
        setLayout(new BorderLayout());

        // Grilla
        contenidoTabla = new DefaultTableModel();
        jTable = new JTable(contenidoTabla);
        jScrollPane = new JScrollPane(jTable);

        // Titulo de las columnas
        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("CBU");
        contenidoTabla.addColumn("Alias");
        contenidoTabla.addColumn("Saldo");
        contenidoTabla.addColumn("Debitado");
        contenidoTabla.addColumn("Acreditado");

        ArrayList arregloCuentas = null;

        switch (word)
        {
            case "CA":
                serviceCurrentAccount = new ServiceCurrentAccount();
                try
                {
                    arregloCuentas = serviceCurrentAccount.findByDni(dni);
                } catch (ServiceException e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error al ver CA", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "SA":
                serviceSavingsAccount = new ServiceSavingsAccount();
                try
                {
                    arregloCuentas = serviceSavingsAccount.findByDni(dni);
                } catch (ServiceException e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error al ver SA", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

        User userAux = new User(arregloCuentas);

        switch (word)
        {
            case "CA":
                for(Object a: arregloCuentas)
                {
                    CurrentAccount currentAccount = (CurrentAccount) a;
                    Object[] row = new Object[6];
                    row[0] = currentAccount.getId();
                    row[1] = currentAccount.getCbu();
                    row[2] = currentAccount.getAlias();
                    row[3] = currentAccount.getBalance();
                    row[4] = currentAccount.getCredit();
                    row[5] = currentAccount.getDebit();

                    contenidoTabla.addRow(row);
                }
                break;

            case "SA":
                for(Object a: arregloCuentas)
                {
                    SavingsAccount savingsAccount = (SavingsAccount) a;
                    Object[] row = new Object[6];
                    row[0] = savingsAccount.getId();
                    row[1] = savingsAccount.getCbu();
                    row[2] = savingsAccount.getAlias();
                    row[3] = savingsAccount.getBalance();
                    row[4] = savingsAccount.getCredit();
                    row[5] = savingsAccount.getDebit();

                    contenidoTabla.addRow(row);
                }
                break;
        }

        // Botones
        botones = new JPanel();
        botonVerTarjeta = new JButton("Ver Tarjeta de cuenta");
        botonAgregarTarjeta = new JButton("Agregar tarjeta");
        botonAtras = new JButton("Atras");

        botones.add(botonVerTarjeta);
        botones.add(botonAgregarTarjeta);
        botones.add(botonAtras);

        add(jScrollPane, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        botonAtras.addActionListener(e -> panelManager.mostrarVerClientes());

        botonAgregarTarjeta.addActionListener(e -> {

            String id = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();
            String cbu = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
            String alias = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
            Double balance = Double.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 3).toString());
            Double credit = Double.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 4).toString());
            Double debit = Double.valueOf(jTable.getValueAt(jTable.getSelectedRow(), 5).toString());

            switch (word)
            {
                case "CA":
                    CurrentAccount currentAccount = new CurrentAccount(id, cbu, alias, balance, credit, debit);
                    panelManager.mostrarAgregarTarjeta(currentAccount, word, dni);
                    break;
                case "SA":
                    SavingsAccount savingsAccount = new SavingsAccount(id, cbu, alias, balance, credit, debit);
                    panelManager.mostrarAgregarTarjeta(savingsAccount, word, dni);
                    break;
            }

        });

        botonVerTarjeta.addActionListener(e -> {
            String id = jTable.getValueAt(jTable.getSelectedRow(), 0).toString();
            String cbu = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
            String alias = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
            panelManager.mostrarVerTarjetaDeCuenta(id, cbu, alias, word, dni);
        });


    }

    public void refreshList()
    {
        removeAll();
        panelManager.mostrarVerCuentasDeCliente(word, dni);
        validate();
        repaint();
    }

}
