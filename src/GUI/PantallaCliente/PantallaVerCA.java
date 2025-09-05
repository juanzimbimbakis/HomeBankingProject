package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.CurrentAccount;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PantallaVerCA extends JPanel {

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

    public PantallaVerCA(PanelManager panelManager, User user)
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

        ArrayList<CurrentAccount> arrayAux = user.getCurrentsAccounts();

        for(Object ca : arrayAux)
        {
            CurrentAccount currentAccount = (CurrentAccount) ca;
            Object[] row = new Object[6];
            row[0] = currentAccount.getId();
            row[1] = currentAccount.getCbu();
            row[2] = currentAccount.getAlias();
            row[3] = currentAccount.getBalance();
            row[4] = currentAccount.getDebit();
            row[5] = currentAccount.getCredit();

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
            panelManager.mostrarRetirarDineroCA(user, cbu);
        });

        botonTransferir.addActionListener(e -> {
            String cbu = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
            panelManager.mostrarTransferir(user, cbu, "CA");
        });

        botonActividad.addActionListener(e -> {
            String cbu = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
            panelManager.mostrarActividad(user, cbu, "CA");
        });

        botonAtras.addActionListener(e -> panelManager.mostrarIngresadoComoCliente(user));

    }

}