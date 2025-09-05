package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.User;

import javax.swing.*;
import java.awt.*;

public class PantallaElegirTransferencia extends JPanel {

    JPanel panelElegirTransferencia;
    JPanel botones;

    JButton botonCbu;
    JButton botonId;
    JButton botonAlias;
    JButton botonAtras;

    private PanelManager panelManager;

    public PantallaElegirTransferencia(PanelManager panelManager, User user, String cbu)
    {
        this.panelManager = panelManager;

        panelElegirTransferencia = new JPanel();
        panelElegirTransferencia.setLayout(new GridLayout(1, 4));

        botones = new JPanel();

        botonCbu = new JButton("Transferir por CBU");
        botonId = new JButton("Transferir por ID");
        botonAlias = new JButton("Transferir por Alias");
        botonAtras = new JButton("Atras");

        setLayout(new BorderLayout());
        botones.add(botonCbu);
        botones.add(botonId);
        botones.add(botonAlias);
        botones.add(botonAtras);

        add(botones, BorderLayout.CENTER);

        botonId.addActionListener(e -> {

        });

        botonCbu.addActionListener(e -> {

        });

        botonAlias.addActionListener(e -> {

        });

        botonAtras.addActionListener(e -> {});

    }

}
