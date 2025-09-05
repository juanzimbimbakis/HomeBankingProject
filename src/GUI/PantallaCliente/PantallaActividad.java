package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.Transfer;
import Model.User;
import Service.ServiceException;
import Service.ServiceTransfer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PantallaActividad extends JPanel {

    // Grilla / Listado
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    // Botones
    JPanel botones;
    JButton botonAtras;

    private PanelManager panelManager;
    private ServiceTransfer serviceTransfer = new ServiceTransfer();

    public PantallaActividad(PanelManager panelManager, User user, String cbu, String word){
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        // Grilla
        contenidoTabla = new DefaultTableModel();
        jTable = new JTable(contenidoTabla);
        jScrollPane = new JScrollPane(jTable);

        // Titulo de las columnas
        contenidoTabla.addColumn("Transfer ID");
        contenidoTabla.addColumn("Amount");
        contenidoTabla.addColumn("Date");

        ArrayList<Transfer> array = new ArrayList<>();

        try
        {
            array = serviceTransfer.findByWord(cbu);
        } catch (ServiceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error PA46", JOptionPane.ERROR_MESSAGE);
        }

        for(Object t : array)
        {
            Transfer transfer = (Transfer) t;
            Object[] row = new Object[3];
            row[0] = transfer.getTransferId();
            row[1] = transfer.getAmount();
            row[2] = transfer.getDate();

            contenidoTabla.addRow(row);

        }

        // Botones
        botones = new JPanel();
        botonAtras = new JButton("Atras");
        botones.add(botonAtras);

        add(jScrollPane, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        botonAtras.addActionListener(e -> {
            switch (word)
            {
                case "CA":
                    panelManager.mostrarVerCA(user);
                    break;
                case "SA":
                    panelManager.mostrarVerSA(user);
            }
        });

    }

}