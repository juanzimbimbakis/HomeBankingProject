package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.Bank;
import Model.User;
import Service.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaVerClientes extends JPanel {

    // Grilla / Listado
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    // Botones
    JPanel botones;
    JButton botonCrearCuenta;
    JButton botonmodificarCliente;
    JButton botonEliminarCliente;
    JButton botonVerCuentasCorrientes;
    JButton botonVerCajasAhorro;
    JButton botonAtras;

    private PanelManager panelManager;

    ServiceUser serviceUser = new ServiceUser();
    ServiceSavingsAccount serviceSavingsAccount = new ServiceSavingsAccount();
    ServiceCurrentAccount serviceCurrentAccount = new ServiceCurrentAccount();
    ServiceCard serviceCard = new ServiceCard();

    public PantallaVerClientes(PanelManager panelManager)
    {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        // Grilla
        contenidoTabla = new DefaultTableModel();
        jTable = new JTable(contenidoTabla);
        jScrollPane = new JScrollPane(jTable);

        // Titulo de las columnas
        contenidoTabla.addColumn("Nombre");
        contenidoTabla.addColumn("Apellido");
        contenidoTabla.addColumn("DNI");
        contenidoTabla.addColumn("Mail");
        contenidoTabla.addColumn("Telefono");
        contenidoTabla.addColumn("Username");

        ArrayList arregloUsuarios = null;

        try
        {
            arregloUsuarios = serviceUser.findAll();
        } catch (ServiceException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al ver todos los clientes", JOptionPane.ERROR_MESSAGE);
        }



        Bank bank = new Bank(arregloUsuarios);

        for(Object obj : arregloUsuarios)
        {
            User user = (User)obj;
            Object[] row = new Object[6];
            row[0] = user.getName();
            row[1] = user.getSurname();
            row[2] = user.getDni();
            row[3] = user.getEmail();
            row[4] = user.getPhoneNumber();
            row[5] = user.getUsername();

            contenidoTabla.addRow(row);
        }

        // Botones
        botones = new JPanel();
        botonCrearCuenta = new JButton("Crear cuenta");
        botonmodificarCliente = new JButton("Modificar cliente");
        botonEliminarCliente = new JButton("Eliminar cliente");
        botonVerCuentasCorrientes = new JButton("Ver cuentas corrientes");
        botonVerCajasAhorro = new JButton("Ver cajas ahorros");
        botonAtras = new JButton("Atras");

        botones.add(botonCrearCuenta);
        botones.add(botonmodificarCliente);
        botones.add(botonEliminarCliente);
        botones.add(botonVerCuentasCorrientes);
        botones.add(botonVerCajasAhorro);
        botones.add(botonAtras);

        add(jScrollPane, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        botonEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String word = (String)jTable.getValueAt(jTable.getSelectedRow(), 2);
                    serviceUser.deleteUser(bank.getUserOfArrayListOfUsers(word));
                    refreshList();
                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al eliminar usuario", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        botonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = (String)jTable.getValueAt(jTable.getSelectedRow(), 2);
                panelManager.mostrarCrearCuenta(word);
            }
        });

        botonmodificarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = (String)jTable.getValueAt(jTable.getSelectedRow(), 2);
                panelManager.mostrarModificarCliente(bank, word);
            }
        });

        botonAtras.addActionListener(e -> panelManager.mostrarIngresadoComoEmpleado());

        botonVerCuentasCorrientes.addActionListener(e -> {
            String word = (String)jTable.getValueAt(jTable.getSelectedRow(), 2);
            panelManager.mostrarVerCuentasDeCliente("CA", word);
        });

        botonVerCajasAhorro.addActionListener(e -> {
            String word = (String)jTable.getValueAt(jTable.getSelectedRow(), 2);
            panelManager.mostrarVerCuentasDeCliente("SA", word);
        });


    }

    public void refreshList()
    {
        removeAll();
        panelManager.mostrarVerClientes();
        validate();
        repaint();
    }

}