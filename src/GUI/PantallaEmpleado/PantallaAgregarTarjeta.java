package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.Account;
import Model.Card;
import Model.CurrentAccount;
import Model.SavingsAccount;
import Service.ServiceCard;
import Service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAgregarTarjeta extends JPanel {

    private JPanel pantallaAgregarTarjeta;
    private JPanel botones;

    private JLabel lNumeroTarjeta;
    //private JLabel lSaldo;
    //private JLabel lGasto;

    private JTextField tfNumeroTarjeta;
    private JTextField tfSaldo;
    private JTextField tfGasto;

    private JButton botonConfirmarTarjeta;
    private JButton botonNuevo;
    private JButton botonAtras;

    private PanelManager panelManager;


    public PantallaAgregarTarjeta(PanelManager panelManager, Account account, String word, String dni)
    {
        this.panelManager = panelManager;

        pantallaAgregarTarjeta = new JPanel();
        pantallaAgregarTarjeta.setLayout(new GridLayout(4, 3));

        lNumeroTarjeta = new JLabel("Numero Tarjeta: ");
        //lSaldo = new JLabel("Saldo: ");
        //lGasto = new JLabel("Gasto: ");

        tfNumeroTarjeta = new JTextField(20);
        tfSaldo = new JTextField(10);
        tfGasto = new JTextField(10);

        pantallaAgregarTarjeta.add(lNumeroTarjeta);
        pantallaAgregarTarjeta.add(tfNumeroTarjeta);
        //pantallaAgregarTarjeta.add(lSaldo);
        //pantallaAgregarTarjeta.add(tfSaldo);
        //pantallaAgregarTarjeta.add(lGasto);
        //pantallaAgregarTarjeta.add(tfGasto);

        setLayout(new BorderLayout());
        add(pantallaAgregarTarjeta, BorderLayout.CENTER);

        botones = new JPanel();
        botonConfirmarTarjeta = new JButton("Confirmar Tarjeta");
        botonNuevo = new JButton("Nuevo");
        botonAtras = new JButton("Atras");

        botones.add(botonConfirmarTarjeta);
        botones.add(botonNuevo);
        botones.add(botonAtras);

        add(botones, BorderLayout.SOUTH);

        botonConfirmarTarjeta.addActionListener(e -> {

            Card card = new Card(tfNumeroTarjeta.getText(), account.getBalance(), 0);
            String id = account.getId();
            String cbu = account.getCbu();
            String alias = account.getAlias();
            ServiceCard serviceCard = new ServiceCard();

            try
            {
                serviceCard.saveCard(card, id, cbu, alias);
                JOptionPane.showMessageDialog(null, "Tarjeta guardada con exito");
            } catch (ServiceException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar tarjeta");
            }
        });

        botonAtras.addActionListener(e -> {
            switch(word)
            {
                case "CA":
                    panelManager.mostrarVerCuentasDeCliente(word, dni);
                    break;
                case "SA":
                    panelManager.mostrarVerCuentasDeCliente(word, dni);
                    break;
            }
        });

        botonNuevo.addActionListener(e -> vaciarComponentes());


    }

    public void vaciarComponentes()
    {
        tfNumeroTarjeta.setText("");
        //tfSaldo.setText("");
        //tfGasto.setText("");
    }
}
