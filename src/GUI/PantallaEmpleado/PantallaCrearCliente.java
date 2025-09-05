package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.User;
import Service.ServiceException;
import Service.ServiceUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaCrearCliente extends JPanel {

    ServiceUser serviceUser = new ServiceUser();
    JPanel pantallaCrearCliente;
    JPanel botones;

    JLabel jLabelNombre;
    JLabel jLabelApellido;
    JLabel jLabelDni;
    JLabel jLabelMail;
    JLabel jLabelTelefono;
    JLabel jLabelUsername;
    JLabel jLabelPassword;

    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;
    JTextField jTextFieldDni;
    JTextField jTextFieldMail;
    JTextField jTextFieldTelefono;
    JTextField jTextFieldUsername;
    JPasswordField jPasswordField;

    JButton botonConfirmarCliente;
    JButton botonNuevo;
    JButton botonAtras;

    private PanelManager panelManager;

    public PantallaCrearCliente(PanelManager p)
    {
        panelManager = p;
        armarFormulario();
    }

    public void armarFormulario()
    {
        pantallaCrearCliente = new JPanel();
        pantallaCrearCliente.setLayout(new GridLayout(8, 3));

        jLabelNombre = new JLabel("Nombre: ");
        jLabelApellido = new JLabel("Apellido: ");
        jLabelDni = new JLabel("DNI: ");
        jLabelMail = new JLabel("Mail: ");
        jLabelTelefono = new JLabel("Telefono: ");
        jLabelUsername = new JLabel("Username: ");
        jLabelPassword = new JLabel("Password: ");

        jTextFieldNombre = new JTextField(20);
        jTextFieldApellido = new JTextField(20);
        jTextFieldDni = new JTextField(20);
        jTextFieldMail = new JTextField(20);
        jTextFieldTelefono = new JTextField(20);
        jTextFieldUsername = new JTextField(20);
        jPasswordField = new JPasswordField(20);

        pantallaCrearCliente.add(jLabelNombre);
        pantallaCrearCliente.add(jTextFieldNombre);
        pantallaCrearCliente.add(jLabelApellido);
        pantallaCrearCliente.add(jTextFieldApellido);
        pantallaCrearCliente.add(jLabelDni);
        pantallaCrearCliente.add(jTextFieldDni);
        pantallaCrearCliente.add(jLabelMail);
        pantallaCrearCliente.add(jTextFieldMail);
        pantallaCrearCliente.add(jLabelTelefono);
        pantallaCrearCliente.add(jTextFieldTelefono);
        pantallaCrearCliente.add(jLabelUsername);
        pantallaCrearCliente.add(jTextFieldUsername);
        pantallaCrearCliente.add(jLabelPassword);
        pantallaCrearCliente.add(jPasswordField);

        setLayout(new BorderLayout());
        add(pantallaCrearCliente, BorderLayout.CENTER);

        botones = new JPanel();
        botonConfirmarCliente = new JButton("Confirmar cliente");
        botonNuevo = new JButton("Nuevo cliente");
        botonAtras = new JButton("Atras");

        botones.add(botonConfirmarCliente);
        botones.add(botonNuevo);
        botones.add(botonAtras);
        add(botones, BorderLayout.SOUTH);

        botonConfirmarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(jTextFieldNombre.getText());
                user.setSurname(jTextFieldApellido.getText());
                user.setDni(jTextFieldDni.getText());
                user.setEmail(jTextFieldMail.getText());
                user.setPhoneNumber(jTextFieldTelefono.getText());
                user.setUsername(jTextFieldUsername.getText());
                String password = new String(jPasswordField.getPassword());
                user.setPassword(password);

                try
                {
                    serviceUser.saveUser(user);
                    JOptionPane.showMessageDialog(null, "Client saved Successfully");

                } catch (ServiceException e1)
                {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }

            }
        });

        botonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
            }
        });

        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarIngresadoComoEmpleado();
            }
        });

    }

    public void vaciarComponentes()
    {
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");
        jTextFieldDni.setText("");
        jTextFieldMail.setText("");
        jTextFieldTelefono.setText("");
        jTextFieldUsername.setText("");
        jPasswordField.setText("");
    }


}
