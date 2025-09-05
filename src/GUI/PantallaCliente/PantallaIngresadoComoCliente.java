package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.User;

import javax.swing.*;
import java.awt.*;

public class PantallaIngresadoComoCliente extends JPanel {

    JPanel panelIngresadoComoCliente;
    JPanel botones;

    JButton botonVerCuentasCorrientes;
    JButton botonVerCajasDeAhorro;
    JButton botonAtras;

    private PanelManager panelManager;

    public PantallaIngresadoComoCliente(PanelManager panelManager, User user)
    {
        this.panelManager = panelManager;
        panelIngresadoComoCliente = new JPanel();
        panelIngresadoComoCliente.setLayout(new GridLayout(1, 4));

        botones = new JPanel();

        botonVerCuentasCorrientes = new JButton("Ver cuentas corrientes ");
        botonVerCajasDeAhorro = new JButton("Ver cajas de ahorro");
        botonAtras = new JButton("Atras");

        setLayout(new BorderLayout());
        botones.add(botonVerCuentasCorrientes);
        botones.add(botonVerCajasDeAhorro);
        botones.add(botonAtras);
        add(botones, BorderLayout.CENTER);

        botonVerCuentasCorrientes.addActionListener(e -> panelManager.mostrarVerCA(user));

        botonVerCajasDeAhorro.addActionListener(e -> panelManager.mostrarVerSA(user));

        botonAtras.addActionListener(e -> panelManager.mostrarIngresarComoCliente());

    }

}
