package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.CurrentAccount;
import Model.Transfer;
import Model.User;
import Service.ServiceCurrentAccount;
import Service.ServiceException;
import Service.ServiceTransfer;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PantallaRetirarDineroCA extends JPanel {

    private JPanel panelRetirarDinero;
    private JPanel botones;

    private JLabel lMonto;

    private JTextField tMonto;

    private JButton botonConfirmarOperacion;
    private JButton botonNuevo;
    private JButton botonAtras;

    private PanelManager panelManager;

    private ServiceCurrentAccount serviceCurrentAccount = new ServiceCurrentAccount();
    private ServiceTransfer serviceTransfer = new ServiceTransfer();

    public PantallaRetirarDineroCA(PanelManager panelManager, User user, String cbu)
    {
        this.panelManager = panelManager;

        panelRetirarDinero = new JPanel();
        panelRetirarDinero.setLayout(new GridLayout(2, 3));

        lMonto = new JLabel("Monto a retirar: ");
        tMonto = new JTextField(12);

        panelRetirarDinero.add(lMonto);
        panelRetirarDinero.add(tMonto);

        setLayout(new BorderLayout());
        add(panelRetirarDinero, BorderLayout.CENTER);

        botones = new JPanel();
        botonConfirmarOperacion = new JButton("Confirmar Operacion");
        botonNuevo = new JButton("Nuevo");
        botonAtras = new JButton("Atras");

        botones.add(botonConfirmarOperacion);
        botones.add(botonNuevo);
        botones.add(botonAtras);
        add(botones, BorderLayout.SOUTH);

        botonConfirmarOperacion.addActionListener(e -> {
            Double monto = Double.valueOf(tMonto.getText());
            CurrentAccount caAux;
            caAux = user.retirarDineroCA(user.getArrayAccount(), cbu, monto);
            LocalDate dateAux = LocalDate.now();
            Transfer transfer = new Transfer(-monto, dateAux);

            try
            {
                serviceCurrentAccount.update(caAux);
                JOptionPane.showMessageDialog(null, "Operacion exitosa");
            } catch (ServiceException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en el servidor", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try
            {
                serviceTransfer.saveTransfer(transfer, caAux.getId(), caAux.getCbu(), caAux.getAlias());
            } catch (ServiceException ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en el servidor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonNuevo.addActionListener(e -> {
           vaciarComponentes();
        });

        botonAtras.addActionListener(e -> panelManager.mostrarVerCA(user));

    }

    public void vaciarComponentes()
    {
        tMonto.setText("");
    }
}
