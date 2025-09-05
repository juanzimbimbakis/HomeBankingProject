package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaBanco extends JPanel {

    JPanel pantallaBanco;
    JPanel botones;

    JButton jButtonIngresarComoEmpleado;
    JButton jButtonIngresarComoCliente;

    private PanelManager panelManager;

    public PantallaBanco(PanelManager p)
    {
        panelManager = p;
        armarFormulario();
    }


    public void armarFormulario()
    {
        pantallaBanco = new JPanel();
        pantallaBanco.setLayout(new GridLayout(2,2));

        botones = new JPanel();

        jButtonIngresarComoEmpleado = new JButton("Ingresar Como Empleado");
        jButtonIngresarComoCliente = new JButton("Ingresar Como Cliente");

        setLayout(new BorderLayout());

        botones.add(jButtonIngresarComoEmpleado);
        botones.add(jButtonIngresarComoCliente);
        add(botones,BorderLayout.CENTER);

        jButtonIngresarComoEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarIngresarComoEmpleado();
            }
        });

        jButtonIngresarComoCliente.addActionListener(e -> panelManager.mostrarIngresarComoCliente());


    }


}

/*

 */
