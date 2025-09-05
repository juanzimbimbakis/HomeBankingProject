package GUI;

import GUI.PantallaCliente.*;
import GUI.PantallaEmpleado.*;
import Model.Account;
import Model.Bank;
import Model.User;
import Service.ServiceException;

import javax.swing.*;
import java.awt.*;

public class PanelManager {

    private PantallaBanco pantallaBanco;

    // Package PantallaEmpleado
    private PantallaIngresarComoEmpleado ingresarComoEmpleado;
    private PantallaIngresadoComoEmpleado ingresadoComoEmpleado;
    private PantallaCrearCliente crearCliente;
    private PantallaVerClientes verClientes;
    private PantallaCrearCuenta crearCuenta;
    private PantallaModificarCliente modificarCliente;
    private PantallaVerCuentasDeCliente verCuentasDeCliente;
    private PantallaVerTarjetaDeCuenta verTarjetaDeCuenta;
    private PantallaAgregarTarjeta agregarTarjeta;

    // Package PantallaCliente
    private PantallaIngresarComoCliente ingresarComoCliente;
    private PantallaIngresadoComoCliente ingresadoComoCliente;
    private PantallaVerCA verCA;
    private PantallaVerSA verSA;
    private PantallaRetirarDineroCA retirarDineroCA;
    private PantallaRetirarDineroSA retirarDineroSA;
    private PantallaTransferir transferir;
    private PantallaActividad actividad;



    JFrame ventana;

    public PanelManager()
    {
        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pantallaBanco = new PantallaBanco(this);
        mostrar(pantallaBanco);
        ventana.setVisible(true);
        ventana.pack();
    }

    void mostrar(JPanel panel)
    {
        ventana.getContentPane().removeAll();
        ventana.getContentPane().add(BorderLayout.CENTER, panel);
        ventana.getContentPane().validate();
        ventana.getContentPane().repaint();
        ventana.pack();
    }

    public void mostrarPantallaBanco()
    {
        mostrar(pantallaBanco);
    }

    public void mostrarIngresarComoEmpleado()
    {
        ingresarComoEmpleado = new PantallaIngresarComoEmpleado(this);
        ingresarComoEmpleado.vaciarComponentes();
        mostrar(ingresarComoEmpleado);
    }

    public void mostrarIngresadoComoEmpleado()
    {
        ingresadoComoEmpleado = new PantallaIngresadoComoEmpleado(this);
        mostrar(ingresadoComoEmpleado);
    }

    public void mostrarCrearCliente()
    {
        crearCliente = new PantallaCrearCliente(this);
        crearCliente.vaciarComponentes();
        mostrar(crearCliente);
    }

    public void mostrarVerClientes()
    {
        verClientes = new PantallaVerClientes(this);
        mostrar(verClientes);
    }

    public void mostrarCrearCuenta(String word)
    {
        crearCuenta = new PantallaCrearCuenta(this, word);
        crearCuenta.vaciarComponentes();
        mostrar(crearCuenta);
    }

    public void mostrarModificarCliente(Bank bank, String word)
    {
        modificarCliente = new PantallaModificarCliente(this, bank, word);
        modificarCliente.vaciarComponentes();
        mostrar(modificarCliente);
    }

    public void mostrarVerCuentasDeCliente(String word, String dni) {
        verCuentasDeCliente = new PantallaVerCuentasDeCliente(this, word, dni);
        mostrar(verCuentasDeCliente);
    }

    public void mostrarVerTarjetaDeCuenta(String id, String cbu, String alias, String word, String dni)
    {
        verTarjetaDeCuenta = new PantallaVerTarjetaDeCuenta(this, id, cbu, alias, word, dni);
        mostrar(verTarjetaDeCuenta);
    }

    public void mostrarAgregarTarjeta(Account account, String word, String dni)
    {
        agregarTarjeta = new PantallaAgregarTarjeta(this, account, word, dni);
        mostrar(agregarTarjeta);
    }

    // Package Pantalla Cliente

    public void mostrarIngresarComoCliente()
    {
        ingresarComoCliente = new PantallaIngresarComoCliente(this);
        ingresarComoCliente.vaciarComponentes();
        mostrar(ingresarComoCliente);
    }

    public void mostrarIngresadoComoCliente(User user)
    {
        ingresadoComoCliente = new PantallaIngresadoComoCliente(this, user);
        mostrar(ingresadoComoCliente);
    }

    public void mostrarVerCA(User user)
    {
        verCA = new PantallaVerCA(this, user);
        mostrar(verCA);
    }

    public void mostrarVerSA(User user)
    {
        verSA = new PantallaVerSA(this, user);
        mostrar(verSA);
    }

    public void mostrarRetirarDineroCA(User user, String cbu)
    {
        retirarDineroCA = new PantallaRetirarDineroCA(this, user, cbu);
        retirarDineroCA.vaciarComponentes();
        mostrar(retirarDineroCA);
    }

    public void mostrarRetirarDineroSA(User user, String cbu)
    {
        retirarDineroSA = new PantallaRetirarDineroSA(this, user, cbu);
        retirarDineroSA.vaciarComponentes();
        mostrar(retirarDineroSA);
    }

    public void mostrarTransferir(User user, String cbu, String word)
    {
        transferir = new PantallaTransferir(this, user, cbu, word);
        transferir.vaciarComponentes();
        mostrar(transferir);
    }

    public void mostrarActividad(User user, String cbu, String word)  {
        actividad = new PantallaActividad(this, user, cbu, word);
        mostrar(actividad);
    }


}
