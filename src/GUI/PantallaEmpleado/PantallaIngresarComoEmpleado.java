package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.Bank;
import Service.ServiceBank;
import Service.ServiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PantallaIngresarComoEmpleado extends JPanel {

    JPanel pantallaIngresarComoEmpleado;
    JPanel botones;

    JLabel jLabelUsername;
    JLabel jLabelPassword;

    JTextField jTextFieldUsername;
    JPasswordField jPasswordField;

    JButton jButtonIngresar;
    JButton jButtonNuevo;
    JButton jButtonAtras;

    private PanelManager panelManager;

    ServiceBank serviceBank = new ServiceBank();

    public PantallaIngresarComoEmpleado(PanelManager p)
    {
        panelManager = p;
        armarFormulario();
    }

    public void armarFormulario()
    {

        pantallaIngresarComoEmpleado = new JPanel();
        pantallaIngresarComoEmpleado.setLayout(new GridLayout(3, 3));

        jLabelUsername = new JLabel("Username: ");
        jTextFieldUsername = new JTextField(15);
        jLabelPassword = new JLabel("Password: ");
        jPasswordField = new JPasswordField(15);

        pantallaIngresarComoEmpleado.add(jLabelUsername);
        pantallaIngresarComoEmpleado.add(jTextFieldUsername);
        pantallaIngresarComoEmpleado.add(jLabelPassword);
        pantallaIngresarComoEmpleado.add(jPasswordField);

        setLayout(new BorderLayout());
        add(pantallaIngresarComoEmpleado, BorderLayout.CENTER);

        botones = new JPanel();
        jButtonIngresar = new JButton("Ingresar");
        jButtonNuevo = new JButton("Nuevo");
        jButtonAtras = new JButton("Atras");

        botones.add(jButtonIngresar);
        botones.add(jButtonNuevo);
        botones.add(jButtonAtras);
        add(botones, BorderLayout.SOUTH);

        jButtonIngresar.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Bank bank = new Bank();
                String username = jTextFieldUsername.getText();
                String password = new String(jPasswordField.getPassword());

                try
                {
                    bank = serviceBank.findLogin(username, password);

                    if(bank != null)
                    {
                        panelManager.mostrarIngresadoComoEmpleado();
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "Username o password incorrecto");
                    }

                } catch (ServiceException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
            }
        });

        jButtonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPantallaBanco();
            }
        });



    }

    public void vaciarComponentes()
    {
        jTextFieldUsername.setText("");
        jPasswordField.setText("");
    }

}
