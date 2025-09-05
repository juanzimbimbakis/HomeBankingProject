package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.Bank;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaIngresadoComoEmpleado extends JPanel {
    JPanel pantallaIngresadoComoEmpleado;
    JPanel botones;

    JButton botonCrearCliente;
    JButton botonVerClientes;
    JButton botonAtras;

    private PanelManager panelManager;

    private Bank bank = null;

    public PantallaIngresadoComoEmpleado(PanelManager p)
    {
        panelManager = p;
        armarFormulario();
    }

    public void armarFormulario()
    {
        pantallaIngresadoComoEmpleado = new JPanel();
        pantallaIngresadoComoEmpleado.setLayout(new GridLayout(1, 3));

        botones = new JPanel();

        botonCrearCliente = new JButton("Crear Cliente");
        botonVerClientes = new JButton("Ver Clientes");
        botonAtras = new JButton("Atras");

        setLayout(new BorderLayout());

        botones.add(botonCrearCliente);
        botones.add(botonVerClientes);
        botones.add(botonAtras);

        add(botones, BorderLayout.CENTER);

        botonCrearCliente.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarCrearCliente();
            }
        });

        botonVerClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarVerClientes();
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarIngresarComoEmpleado();
            }
        });
    }

}

