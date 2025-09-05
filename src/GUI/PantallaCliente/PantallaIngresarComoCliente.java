package GUI.PantallaCliente;

import GUI.PanelManager;
import Model.User;
import Service.ServiceException;
import Service.ServiceUser;

import javax.swing.*;
import java.awt.*;

public class PantallaIngresarComoCliente extends JPanel {

    private JPanel pantallaIngresarComoCliente;
    private JPanel botones;

    private JLabel lUsername;
    private JLabel lPassword;

    private JTextField tUsername;
    private JPasswordField tPassword;

    private JButton botonIngresar;
    private JButton botonNuevo;
    private JButton botonAtras;

    private PanelManager panelManager;

    private ServiceUser serviceUser = new ServiceUser();

    public PantallaIngresarComoCliente(PanelManager panelManager)
    {
        this.panelManager = panelManager;

        pantallaIngresarComoCliente = new JPanel();
        pantallaIngresarComoCliente.setLayout(new GridLayout(3, 3));

        lUsername = new JLabel("Username: ");
        tUsername = new JTextField(15);
        lPassword = new JLabel("Password: ");
        tPassword = new JPasswordField(15);

        pantallaIngresarComoCliente.add(lUsername);
        pantallaIngresarComoCliente.add(tUsername);
        pantallaIngresarComoCliente.add(lPassword);
        pantallaIngresarComoCliente.add(tPassword);

        setLayout(new BorderLayout());
        add(pantallaIngresarComoCliente, BorderLayout.CENTER);

        botones = new JPanel();
        botonIngresar = new JButton("Ingresar");
        botonNuevo = new JButton("Nuevo");
        botonAtras = new JButton("Atras");

        botones.add(botonIngresar);
        botones.add(botonNuevo);
        botones.add(botonAtras);
        add(botones, BorderLayout.SOUTH);

        botonIngresar.addActionListener(e -> {

            String username = tUsername.getText();
            String password = new String(tPassword.getPassword());
            User userAux = new User();
            try
            {
                userAux = serviceUser.findLogin(username, password);
                if(userAux != null)
                {
                    panelManager.mostrarIngresadoComoCliente(userAux);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
                }
            } catch (ServiceException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error en el servidor", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        botonNuevo.addActionListener(e -> vaciarComponentes());

        botonAtras.addActionListener(e -> panelManager.mostrarPantallaBanco());

    }

    public void vaciarComponentes()
    {
        tUsername.setText("");
        tPassword.setText("");
    }
}