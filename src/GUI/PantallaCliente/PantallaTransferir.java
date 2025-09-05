package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.CurrentAccount;
import Model.SavingsAccount;
import Model.Transfer;
import Model.User;
import Service.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PantallaTransferir extends JPanel {

    JPanel pantallaTransferir;
    JPanel botones;

    JLabel labelChoice;
    JLabel lMonto;

    JTextField tChoice;
    JTextField tMonto;

    JButton botonTransferir;
    JButton botonNuevo;
    JButton botonAtras;


    private PanelManager panelManager;

    private ServiceCurrentAccount serviceCurrentAccount = new ServiceCurrentAccount();
    private ServiceSavingsAccount serviceSavingsAccount = new ServiceSavingsAccount();
    private ServiceCard serviceCard = new ServiceCard();
    private ServiceTransfer serviceTransfer = new ServiceTransfer();

    public PantallaTransferir(PanelManager panelManager, User user, String cbu, String word)
    {
        this.panelManager = panelManager;
        String tipoOrigen;
        if(word.equals("CA"))
        {
            tipoOrigen = "CA";
        } else
        {
            tipoOrigen = "SA";
        }

        pantallaTransferir = new JPanel();
        pantallaTransferir.setLayout(new GridLayout(3,3));

        labelChoice = new JLabel("ID/CBU/Alias destino: ");
        tChoice = new JTextField(20);
        lMonto = new JLabel("Monto a transferir: ");
        tMonto = new JTextField(20);

        pantallaTransferir.add(labelChoice);
        pantallaTransferir.add(tChoice);
        pantallaTransferir.add(lMonto);
        pantallaTransferir.add(tMonto);

        setLayout(new BorderLayout());
        add(pantallaTransferir, BorderLayout.CENTER);

        botones = new JPanel();
        botonTransferir = new JButton("Transferir");
        botonNuevo = new JButton("Nuevo");
        botonAtras = new JButton("Atras");

        botones.add(botonTransferir);
        botones.add(botonNuevo);
        botones.add(botonAtras);
        add(botones, BorderLayout.SOUTH);

        botonTransferir.addActionListener(e -> {
            LocalDate date = LocalDate.now();
            System.out.println("Hasta aca llegamos 1111");
            String destino = tChoice.getText();
            double monto = Double.parseDouble(tMonto.getText());

            CurrentAccount currentAccount = null;
            SavingsAccount savingsAccount = null;
            try
            {
                currentAccount = serviceCurrentAccount.findAccount(destino);
            } catch (ServiceException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en el servidor PT83", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if(currentAccount == null)
            {
                try
                {
                    savingsAccount = serviceSavingsAccount.findAccount(destino);
                    System.out.println(savingsAccount);
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error en el servidor PT90", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            System.out.println("Hasta aca llegamos 2222");
            if(currentAccount == null && savingsAccount == null)
            {
                System.out.println("Hasta aca llegamos 3333");
                JOptionPane.showMessageDialog(null, "No existe la cuenta destino", "Error", JOptionPane.ERROR_MESSAGE);
            } else
            {
                System.out.println("Hasta aca llegamos 4444");
                boolean n;
                if(currentAccount != null && savingsAccount == null)
                {
                    System.out.println("Hasta aca llegamos 5555");
                    n = user.quitarDinero(cbu, monto, destino);
                    System.out.println("Pudo quitar dinero: " + n + "1");
                    if(n)
                    {
                        System.out.println("Hasta aca llegamos 8888");
                        currentAccount.agregarDinero(monto);
                        if(tipoOrigen.equals("CA"))
                        {
                            System.out.println("Hasta aca llegamos 9999");
                            CurrentAccount c = user.getCurrentAccount(cbu);
                            Transfer transferFrom = new Transfer(-(monto), date);
                            Transfer transferTo = new Transfer(monto, date);
                            try
                            {
                                serviceCurrentAccount.update(c);
                                serviceCurrentAccount.update(currentAccount);
                                serviceTransfer.saveTransfer(transferFrom, c.getId(), c.getCbu(), c.getAlias());
                                serviceTransfer.saveTransfer(transferTo, currentAccount.getId(), currentAccount.getCbu(), currentAccount.getAlias());
                                serviceCard.updateMoreBalance(destino, monto);
                                serviceCard.updateLessBalance(cbu, monto);
                                System.out.println("Hasta aca llegamos 1010");
                                JOptionPane.showMessageDialog(null, "Dinero transferido exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                            } catch (ServiceException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error en el servidor PT107", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if(tipoOrigen.equals("SA"))
                        {
                            SavingsAccount s = user.getSavingAccount(cbu);
                            Transfer transferFrom = new Transfer(-(monto), date);
                            Transfer transferTo = new Transfer(monto, date);
                            try
                            {
                                serviceSavingsAccount.update(s);
                                serviceCurrentAccount.update(currentAccount);
                                serviceTransfer.saveTransfer(transferFrom, s.getId(), s.getCbu(), s.getAlias());
                                serviceTransfer.saveTransfer(transferTo, currentAccount.getId(), currentAccount.getCbu(), currentAccount.getAlias());
                                serviceCard.updateMoreBalance(destino, monto);
                                serviceCard.updateLessBalance(cbu, monto);
                                JOptionPane.showMessageDialog(null, "Dinero transferido exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                            } catch (ServiceException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error en el servidor PT121", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }


                    } else
                    {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (currentAccount == null && savingsAccount != null)
                {
                    System.out.println("Hasta aca llegamos 6666");
                    n = user.quitarDinero(cbu, monto, destino);
                    System.out.println("Pudo quitar dinero: " + n + "2");
                    if (n)
                    {
                        savingsAccount.agregarDinero(monto);
                        if(tipoOrigen.equals("CA"))
                        {
                            CurrentAccount c = user.getCurrentAccount(cbu);
                            Transfer transferFrom = new Transfer(-(monto), date);
                            Transfer transferTo = new Transfer(monto, date);
                            try
                            {
                                serviceCurrentAccount.update(c);
                                serviceSavingsAccount.update(savingsAccount);
                                serviceTransfer.saveTransfer(transferFrom, c.getId(), c.getCbu(), c.getAlias());
                                serviceTransfer.saveTransfer(transferTo, savingsAccount.getId(), savingsAccount.getCbu(), savingsAccount.getAlias());
                                serviceCard.updateMoreBalance(destino, monto);
                                serviceCard.updateLessBalance(cbu, monto);
                                JOptionPane.showMessageDialog(null, "Dinero transferido exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                            } catch (ServiceException ex)
                            {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error en el servidor PT148", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if(tipoOrigen.equals("SA"))
                        {
                            SavingsAccount s = user.getSavingAccount(cbu);
                            Transfer transferFrom = new Transfer(-(monto), date);
                            Transfer transferTo = new Transfer(monto, date);
                            try
                            {
                                serviceSavingsAccount.update(s);
                                serviceSavingsAccount.update(savingsAccount);
                                serviceTransfer.saveTransfer(transferFrom, s.getId(), s.getCbu(), s.getAlias());
                                serviceTransfer.saveTransfer(transferTo, savingsAccount.getId(), savingsAccount.getCbu(), savingsAccount.getAlias());
                                serviceCard.updateMoreBalance(destino, monto);
                                serviceCard.updateLessBalance(cbu, monto);
                                JOptionPane.showMessageDialog(null, "Dinero transferido exitosamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                            } catch (ServiceException ex)
                            {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error en el servidor PT164", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    } else
                    {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }



        });

        botonNuevo.addActionListener(e -> vaciarComponentes());

        botonAtras.addActionListener(e -> {
            if(tipoOrigen.equals("CA"))
            {
                panelManager.mostrarVerCA(user);
            }
            if(tipoOrigen.equals("SA"))
            {
                panelManager.mostrarVerSA(user);
            }
        });

    }

    public void vaciarComponentes()
    {
        tChoice.setText("");
        tMonto.setText("");
    }

}
