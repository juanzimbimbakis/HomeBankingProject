package GUI.PantallaEmpleado;

import GUI.PanelManager;
import Model.Bank;
import Service.ServiceUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaModificarCliente extends JPanel {

    private ServiceUser serviceUser = new ServiceUser();
    private JPanel pantallaModificarCliente;
    private JPanel botones;
    private JPanel botoneraListo;

    private JLabel lNuevoMail;
    private JLabel lNuevoPhoneNumber;
    private JLabel lNuevoUsername;
    private JLabel lNuevoPassword;

    private JTextField tNuevoMail;
    private JTextField tNuevoPhoneNumber;
    private JTextField tNuevoUsername;
    private JTextField tNuevoPassword;

    private JButton botonListo;
    private JButton botonConfirmarCambios;
    private JButton botonAtras;
    private JButton botonNuevo;

    private JComboBox<String> box;

    private Bank bank;
    private String dni;

    private PanelManager panelManager;

    public PantallaModificarCliente(PanelManager panelManager, Bank bank, String dni)
    {
        this.panelManager = panelManager;
        this.bank = bank;
        this.dni = dni;

        pantallaModificarCliente = new JPanel();
        pantallaModificarCliente.setLayout(new GridLayout(3, 2));

        /*
        add(new JLabel("Tipo de Cuenta:"));
        // ComboBox para seleccionar el tipo de cuenta
        tipoCuentaBox = new JComboBox<>(new String[]{"Cuenta Corriente", "Caja de Ahorro"});
        add(tipoCuentaBox);


        ///////////////////////////////////


        pantallaCrearCuenta.add(new JLabel("Seleccione tipo de cuenta: "));
        box = new JComboBox<>(new String[]{"Cuenta Corriente", "Caja de Ahorro"});
        pantallaCrearCuenta.add(box);
         */

        lNuevoMail = new JLabel("Nuevo Mail: ");
        lNuevoPhoneNumber = new JLabel("Nuevo Telefono: ");
        lNuevoUsername = new JLabel("Nuevo Username: ");
        lNuevoPassword = new JLabel("Nuevo Password: ");

        tNuevoMail = new JTextField(25);
        tNuevoPhoneNumber = new JTextField(20);
        tNuevoUsername = new JTextField(20);
        tNuevoPassword = new JTextField(20);

        botones = new JPanel();
        botoneraListo = new JPanel();
        botonListo = new JButton("Listo");
        botonConfirmarCambios = new JButton("Confirmar cambios");
        botonAtras = new JButton("Atras");
        botonNuevo = new JButton("Nuevo");

        //pantallaModificarCliente.add(new JLabel("Seleccionar modificacion"));
        // Hacemos el combo box
        box = new JComboBox<>(new String[]{"Mail", "Telefono", "Username", "Password"});
        //pantallaModificarCliente.add(box);
        //String type = (String) box.getSelectedItem();
        box.addActionListener(e -> actualizarComponentes((String) box.getSelectedItem()));

        setLayout(new BorderLayout());
        add(pantallaModificarCliente, BorderLayout.CENTER);

        pantallaModificarCliente.add(new JLabel("Seleccionar modificacion"));
        pantallaModificarCliente.add(box);

        botones.add(botonConfirmarCambios);
        botones.add(botonAtras);
        add(botones, BorderLayout.SOUTH);

        actualizarComponentes((String) box.getSelectedItem());

        botonConfirmarCambios.addActionListener(e -> {
            try
            {
                serviceUser.updateUser(bank.getUserOfArrayListOfUsers(dni));
                JOptionPane.showMessageDialog(null, "Cliente Modificado exitosamente");
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Error al modificar cliente");
            }
        });

        botonAtras.addActionListener(e -> {
           panelManager.mostrarVerClientes();
        });

    }



    public void vaciarComponentes()
    {
        tNuevoMail.setText("");
        tNuevoPhoneNumber.setText("");
        tNuevoUsername.setText("");
        tNuevoPassword.setText("");
    }

    private void actualizarComponentes(String seleccion)
    {
        pantallaModificarCliente.removeAll(); // Limpiamos los componentes anteriores
        pantallaModificarCliente.add(new JLabel("Seleccionar modificacion"));
        pantallaModificarCliente.add(box);

        if (botoneraListo.getComponentCount() == 0)
        {
            botoneraListo.setLayout(new FlowLayout());
            botoneraListo.add(botonListo);
        }

        switch (seleccion)
        {
            case "Mail":
                pantallaModificarCliente.add(lNuevoMail);
                pantallaModificarCliente.add(tNuevoMail);
                break;
            case "Telefono":
                pantallaModificarCliente.add(lNuevoPhoneNumber);
                pantallaModificarCliente.add(tNuevoPhoneNumber);
                break;
            case "Username":
                pantallaModificarCliente.add(lNuevoUsername);
                pantallaModificarCliente.add(tNuevoUsername);
                break;
            case "Password":
                pantallaModificarCliente.add(lNuevoPassword);
                pantallaModificarCliente.add(tNuevoPassword);
                break;
          //default:
              //JOptionPane.showMessageDialog(null, "Opcion no valida");

        }

        pantallaModificarCliente.add(botoneraListo);

        configurarBotonListo(seleccion, dni, bank);

        pantallaModificarCliente.revalidate();
        pantallaModificarCliente.repaint();
    }

    private void configurarBotonListo(String seleccion, String dni, Bank bank)
    {

        // Eliminamos ActionListeners existentes para evitar duplicados
        for (ActionListener al : botonListo.getActionListeners()) {
            botonListo.removeActionListener(al);
        }

        botonListo.addActionListener(e ->  {
                String nuevoValor;

                switch (seleccion)
                {
                    case "Mail":
                        nuevoValor = tNuevoMail.getText();
                        System.out.println("Mail Nuevo: " + nuevoValor);
                        if (nuevoValor == null || nuevoValor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingresá un valor válido.");
                            return;
                        }
                        bank.updateMailOfUserFromArray(dni, nuevoValor);
                        break;
                    case "Telefono":
                        nuevoValor = tNuevoPhoneNumber.getText();
                        if (nuevoValor == null || nuevoValor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingresá un valor válido.");
                            return;
                        }
                        bank.updatePhoneNumberOfUserFromArray(dni, nuevoValor);
                        break;
                    case "Username":
                        nuevoValor = tNuevoUsername.getText();
                        if (nuevoValor == null || nuevoValor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingresá un valor válido.");
                            return;
                        }
                        bank.updateUsernameOfUserFromArray(dni, nuevoValor);
                        break;
                    case "Password":
                        nuevoValor = tNuevoPassword.getText();
                        if (nuevoValor == null || nuevoValor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingresá un valor válido.");
                            return;
                        }
                        bank.updatePasswordOfUserFromArray(dni, nuevoValor);
                        break;
                }
                vaciarComponentes();

        });
    }



}
