package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.CurrentAccount;
import Model.SavingsAccount;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PantallaVerSA extends JPanel {

    // Grilla / Listado
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    // Botones
    JPanel botones;
    JButton botonTransferir;
    JButton botonRetirarDinero;
    JButton botonActividad;
    JButton botonAtras;

    private PanelManager panelManager;


    public PantallaVerSA(PanelManager panelManager, User user)
    {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        // Grilla
        contenidoTabla = new DefaultTableModel();
        jTable = new JTable(contenidoTabla);
        jScrollPane = new JScrollPane(jTable);

        // Titulo de las columnas
        contenidoTabla.addColumn("ID");
        contenidoTabla.addColumn("CBU");
        contenidoTabla.addColumn("ALIAS");
        contenidoTabla.addColumn("SALDO");
        contenidoTabla.addColumn("DEBITADO");
        contenidoTabla.addColumn("ACREDITADO");

        ArrayList<SavingsAccount> arrayAux = user.getSavingsAccount();

        for(Object ca : arrayAux)
        {
            SavingsAccount savingsAccount = (SavingsAccount) ca;
            Object[] row = new Object[6];
            row[0] = savingsAccount.getId();
            row[1] = savingsAccount.getCbu();
            row[2] = savingsAccount.getAlias();
            row[3] = savingsAccount.getBalance();
            row[4] = savingsAccount.getDebit();
            row[5] = savingsAccount.getCredit();

            contenidoTabla.addRow(row);
        }

        // Botones
        botones = new JPanel();
        botonTransferir = new JButton("Transferir");
        botonRetirarDinero = new JButton("Retirar dinero");
        botonActividad = new JButton("Actividad");
        botonAtras = new JButton("Atras");
        botones.add(botonTransferir);
        botones.add(botonRetirarDinero);
        botones.add(botonActividad);
        botones.add(botonAtras);

        add(jScrollPane, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        botonRetirarDinero.addActionListener(e -> {
            String cbu = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
            panelManager.mostrarRetirarDineroSA(user, cbu);
        });

        botonTransferir.addActionListener(e -> {
            String cbu = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
            panelManager.mostrarTransferir(user, cbu, "SA");
        });

        botonActividad.addActionListener(e -> {
            String cbu = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
            panelManager.mostrarActividad(user, cbu, "SA");
        });

        botonAtras.addActionListener(e -> panelManager.mostrarIngresadoComoCliente(user));

    }

}