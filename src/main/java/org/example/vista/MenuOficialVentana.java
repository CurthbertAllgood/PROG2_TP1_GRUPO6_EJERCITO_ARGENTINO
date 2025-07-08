package org.example.vista;

import org.example.model.infraestructura.Cuartel;
import org.example.model.personal.Oficial;
import org.example.model.personal.Soldado;
import org.example.operacion.OperacionesOficial;
import org.example.persistencia.GestorCuarteles;
import org.example.persistencia.GestorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuOficialVentana extends JFrame {

    private final GestorUsuarios gestorU;
    private final GestorCuarteles gestorC;
    private final String rutaUsuarios;
    private final String rutaCuarteles;

    public MenuOficialVentana(Oficial persona, GestorUsuarios gestorU, GestorCuarteles  gestorC,String rutaCuarteles, GestorCuarteles gestorC1, String rutaUsuarios) {
        this.gestorU = gestorU;
        this.gestorC = gestorC;
        this.rutaUsuarios = rutaUsuarios;
        this.rutaCuarteles = rutaCuarteles;

        setTitle("Menú Oficial");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel bienvenida = new JLabel("Bienvenido, Oficial", SwingConstants.CENTER);
        panel.add(bienvenida);

        JButton botonGestionSoldado = new JButton("Gestionar Usuario");
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
            List<Soldado> soldados = gestorU.getMilitares().stream()
                    .filter(m -> m instanceof Soldado)
                    .map(m -> (Soldado) m)
                    .toList();

            OperacionesOficial sistema = new OperacionesOficial(soldados, gestorU, rutaUsuarios); // ✅ Pasás todo
            new VentanaGestionUsuarios(gestorU, rutaUsuarios).setVisible(true);


        });

        botonGestionCuartel.addActionListener(e ->
        {
            List<Cuartel> cuarteles = gestorC.getCuarteles().stream()
                    .filter(m -> m instanceof Cuartel)
                    .map(m -> (Cuartel) m)
                    .toList();

            OperacionesOficial sistema = new OperacionesOficial(cuarteles, gestorC, rutaCuarteles); // ✅ Pasás todo
            new VentanaGestionUsuarios(gestorC, rutaUsuarios).setVisible(true);


        });

        botonGestionCompania.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Gestionando compañías (simulado)."));

        botonGestionServicio.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Gestionando servicios (simulado)."));

        botonSalir.addActionListener(e -> {
            dispose();
            new MenuLoginVentana(gestorU, rutaUsuarios).setVisible(true); // ✅ Usás el constructor correcto
        });
    }
}