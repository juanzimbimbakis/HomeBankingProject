package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.Card;
import Model.User;
import Service.ServiceCard;
import Service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PantallaVerTarjetaDeCuenta extends JPanel {

    // Grilla / Listado
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    // Botones
    private JPanel botones;
    private JButton botonAtras;
    private JButton botonEliminarTarjeta;

    private PanelManager panelManager;

    private ServiceCard serviceCard = new ServiceCard();




    public PantallaVerTarjetaDeCuenta(PanelManager panelManager, String id, String cbu, String alias, String word, String dni)
    {
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        // Grilla
        contenidoTabla = new DefaultTableModel();
        jTable = new JTable(contenidoTabla);
        jScrollPane = new JScrollPane(jTable);

        // Titulo de las columnas

        contenidoTabla.addColumn("Numero de Tarjeta");
        contenidoTabla.addColumn("Saldo");
        contenidoTabla.addColumn("Gasto");

        ArrayList arregloCard = null;

        try
        {
            arregloCard = serviceCard.findAll(id, cbu, alias);
        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error al ver tarjetas", JOptionPane.ERROR_MESSAGE);
        }

        for (Object o : arregloCard)
        {
            Card card = (Card) o;
            Object[] row = new Object[3];
            row[0] = card.getCardNumber();
            row[1] = card.getBalance();
            row[2] = card.getExpense();

            contenidoTabla.addRow(row);
        }

        // Botones
        botones = new JPanel();
        botonAtras = new JButton("Atras");
        botonEliminarTarjeta = new JButton("Eliminar Tarjeta");

        botones.add(botonAtras);
        botones.add(botonEliminarTarjeta);

        add(jScrollPane, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        botonAtras.addActionListener(e -> panelManager.mostrarVerCuentasDeCliente(word, dni));

        botonEliminarTarjeta.addActionListener(e -> {
           String number = (String) jTable.getValueAt(jTable.getSelectedRow(), 0);
           Double saldo = (Double) jTable.getValueAt(jTable.getSelectedRow(), 1);
           Double expense = (Double) jTable.getValueAt(jTable.getSelectedRow(), 2);
           Card card = new Card(number, saldo, expense);
           ServiceCard serviceCard = new ServiceCard();
           try
           {
               serviceCard.deleteCard(card);
           } catch (ServiceException ex) {
               ex.printStackTrace();
               JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al eliminar tarjeta", JOptionPane.ERROR_MESSAGE);
           }

        });

    }
}
