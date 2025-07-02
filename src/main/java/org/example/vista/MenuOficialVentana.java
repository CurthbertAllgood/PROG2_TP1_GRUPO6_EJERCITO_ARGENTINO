package org.example.vista;

import org.example.model.personal.Oficial;
import org.example.model.personal.Soldado;
import org.example.operacion.OperacionesOficial;
import org.example.persistencia.GestorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuOficialVentana extends JFrame {

    private final GestorUsuarios gestor;
    private String rutaUsuarios;

    public MenuOficialVentana(Oficial persona, GestorUsuarios gestor, String rutaUsuarios) {
        this.gestor = gestor;
        this.rutaUsuarios = rutaUsuarios; // ✅ Asignás el parámetro recibido

        setTitle("Menú Oficial");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel bienvenida = new JLabel("Bienvenido, Oficial", SwingConstants.CENTER);
        panel.add(bienvenida);

        JButton botonGestionSoldado = new JButton("Gestionar Soldado");
        JButton botonGestionCuartel = new JButton("Gestionar Cuartel");
        JButton botonGestionCompania = new JButton("Gestionar Compañía");
        JButton botonGestionServicio = new JButton("Gestionar Servicio");
        JButton botonSalir = new JButton("Salir");

        panel.add(botonGestionSoldado);
        panel.add(botonGestionCuartel);
        panel.add(botonGestionCompania);
        panel.add(botonGestionServicio);
        panel.add(botonSalir);

        add(panel);

        botonGestionSoldado.addActionListener(e -> {
            List<Soldado> soldados = gestor.getMilitares().stream()
                    .filter(m -> m instanceof Soldado)
                    .map(m -> (Soldado) m)
                    .toList();

            OperacionesOficial sistema = new OperacionesOficial(soldados, gestor, rutaUsuarios); // ✅ Pasás todo
            new VentanaGestionSoldado(sistema).setVisible(true);
        });

        botonGestionCuartel.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Gestionando cuarteles (simulado)."));

        botonGestionCompania.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Gestionando compañías (simulado)."));

        botonGestionServicio.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Gestionando servicios (simulado)."));

        botonSalir.addActionListener(e -> {
            dispose();
            new MenuLoginVentana(gestor, rutaUsuarios).setVisible(true); // ✅ Usás el constructor correcto
        });
    }
}
