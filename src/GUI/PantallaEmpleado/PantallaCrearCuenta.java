package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.Card;
import Model.CurrentAccount;
import Model.SavingsAccount;
import Service.ServiceCard;
import Service.ServiceCurrentAccount;
import Service.ServiceException;
import Service.ServiceSavingsAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaCrearCuenta extends JPanel {

    ServiceSavingsAccount serviceSavingsAccount = new ServiceSavingsAccount();
    ServiceCurrentAccount serviceCurrentAccount = new ServiceCurrentAccount();
    ServiceCard serviceCard = new ServiceCard();

    JPanel pantallaCrearCuenta;
    JPanel botones;

    JLabel lId;
    JLabel lCbu;
    JLabel lAlias;
    JLabel lBalance;
    JLabel lCard;

    JTextField tId;
    JTextField tCbu;
    JTextField tAlias;
    JTextField tBalance;
    JTextField tCard;

    JButton botonConfirmarCuenta;
    JButton botonNuevo;
    JButton botonAtras;

    PanelManager panelManager;

    public PantallaCrearCuenta(PanelManager panelManager, String dni)
    {
        this.panelManager = panelManager;

        pantallaCrearCuenta = new JPanel();
        pantallaCrearCuenta.setLayout(new GridLayout(6, 3));

        lId = new JLabel("ID: ");
        lCbu = new JLabel("CBu: ");
        lAlias = new JLabel("Alias: ");
        lBalance = new JLabel("Saldo: ");
        lCard = new JLabel("Nro. Tarjeta (opcional): ");

        tId = new JTextField(20);
        tCbu = new JTextField(20);
        tAlias = new JTextField(20);
        tBalance = new JTextField(10);
        tCard = new JTextField(20);

        pantallaCrearCuenta.add(lId);
        pantallaCrearCuenta.add(tId);
        pantallaCrearCuenta.add(lCbu);
        pantallaCrearCuenta.add(tCbu);
        pantallaCrearCuenta.add(lAlias);
        pantallaCrearCuenta.add(tAlias);
        pantallaCrearCuenta.add(lBalance);
        pantallaCrearCuenta.add(tBalance);
        pantallaCrearCuenta.add(lCard);
        pantallaCrearCuenta.add(tCard);
        /*
        add(new JLabel("Tipo de Cuenta:"));
        // ComboBox para seleccionar el tipo de cuenta
        tipoCuentaBox = new JComboBox<>(new String[]{"Cuenta Corriente", "Caja de Ahorro"});
        add(tipoCuentaBox);
         */
        pantallaCrearCuenta.add(new JLabel("Seleccione tipo de cuenta: "));
        JComboBox box;
        box = new JComboBox<>(new String[]{"Cuenta Corriente", "Caja de Ahorro"});
        pantallaCrearCuenta.add(box);

        setLayout(new BorderLayout());
        add(pantallaCrearCuenta, BorderLayout.CENTER);

        botones = new JPanel();
        botonConfirmarCuenta = new JButton("Confirmar cuenta");
        botonNuevo = new JButton("Nuevo");
        botonAtras = new JButton("Atras");

        botones.add(botonConfirmarCuenta);
        botones.add(botonNuevo);
        botones.add(botonAtras);
        add(botones, BorderLayout.SOUTH);



        botonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarVerClientes();
            }
        });

        botonConfirmarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = tId.getText();
                String cbu = tCbu.getText();
                String alias = tAlias.getText();
                Double balance = Double.parseDouble(tBalance.getText());
                Double credit = Double.parseDouble(tBalance.getText());
                String card = tCard.getText();
                String type = (String) box.getSelectedItem();

                if (type.equals("Cuenta Corriente"))
                {
                    if (card.equals("")) {
                        CurrentAccount ca = new CurrentAccount(id, cbu, alias, balance, 0, credit);
                        try {
                            serviceCurrentAccount.save(ca, dni);
                            JOptionPane.showMessageDialog(null, "Cuenta corriente creada");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error guardando Cuenta corriente", JOptionPane.ERROR_MESSAGE);
                        }
                    } else
                    {
                        CurrentAccount ca = new CurrentAccount(id, cbu, alias, balance, 0, credit);
                        Card solid = new Card(card, balance, 0);
                        try {
                            serviceCurrentAccount.save(ca, dni);
                            JOptionPane.showMessageDialog(null, "Cuenta corriente creada");
                            try
                            {
                                serviceCard.saveCard(solid, id, cbu, alias);
                            } catch (ServiceException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error guardando Tarjeta", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error guardando Cuenta corriente", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                if (type.equals("Caja de Ahorro"))
                {
                    if (card.equals("")) {
                        SavingsAccount sa = new SavingsAccount(id, cbu, alias, balance, 0, credit);
                        try {
                            serviceSavingsAccount.save(sa, dni);
                            JOptionPane.showMessageDialog(null, "Caja de Ahorro creada");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error guardando Cuenta corriente", JOptionPane.ERROR_MESSAGE);
                        }
                    } else
                    {
                        SavingsAccount sa = new SavingsAccount(id, cbu, alias, balance, 0, credit);
                        Card solid = new Card(card, balance, 0);
                        try {
                            serviceSavingsAccount.save(sa, dni);
                            JOptionPane.showMessageDialog(null, "Caja de Ahorro creada");
                            try
                            {
                                serviceCard.saveCard(solid, id, cbu, alias);
                            } catch (ServiceException ex) {
                                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error guardando Tarjeta", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error guardando Cuenta corriente", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }




            }
        });

    }

    public void vaciarComponentes()
    {
        tId.setText("");
        tCbu.setText("");
        tAlias.setText("");
        tBalance.setText("");
        tCard.setText("");
    }

}