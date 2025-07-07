package org.example.vista;

import org.example.model.personal.Suboficial;
import org.example.persistencia.GestorUsuarios;

import javax.swing.*;
import java.awt.*;

public class MenuSuboficialVentana extends JFrame {

    private final Suboficial persona;
    private final GestorUsuarios gestor;
    private  String rutaUsuarios;

    public MenuSuboficialVentana(Suboficial persona, GestorUsuarios gestor, String rutaUsuarios) {
        this.persona = persona;
        this.gestor = gestor;
        this.rutaUsuarios = rutaUsuarios;

        setTitle("Menú Suboficial");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel bienvenida = new JLabel("Bienvenido, Suboficial", SwingConstants.CENTER);
        panel.add(bienvenida);

        JButton botonConsultar = new JButton("Consultar Soldado");
        JButton botonModificar = new JButton("Modificar Soldado");
        JButton botonSalir = new JButton("Salir");

        panel.add(botonConsultar);
        panel.add(botonModificar);
        panel.add(botonSalir);

        botonConsultar.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Consultando datos del soldado (simulado)."));

        botonModificar.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Modificando datos del soldado (simulado)."));

        botonSalir.addActionListener(e -> {
            dispose();
            new MenuLoginVentana(gestor, rutaUsuarios).setVisible(true);
        });

        add(panel);
    }
}