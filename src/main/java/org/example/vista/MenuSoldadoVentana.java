package org.example.vista;

import org.example.model.personal.Soldado;
import org.example.model.servicio.ServicioRealizado;
import org.example.persistencia.GestorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MenuSoldadoVentana extends JFrame {

    private final Soldado soldado;
    private final GestorUsuarios gestor;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public MenuSoldadoVentana(Soldado soldado, GestorUsuarios gestor) {
        this.soldado = soldado;
        this.gestor = gestor;

        setTitle("Menú Soldado");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JButton btnVerDatos = new JButton("Ver mis datos");
        JButton btnVerServicios = new JButton("Ver servicios realizados");
        JButton btnSalir = new JButton("Cerrar sesión");

        // Acción 1: Ver datos
        btnVerDatos.addActionListener(e -> {
            String info = "Nombre: " + soldado.getNombre() + " " + soldado.getApellidos() +
                    "\nCódigo: " + soldado.getCodigo();
            JOptionPane.showMessageDialog(this, info, "Datos del Soldado", JOptionPane.INFORMATION_MESSAGE);
        });

        // Acción 2: Ver servicios realizados
        btnVerServicios.addActionListener(e -> {
            List<ServicioRealizado> servicios = soldado.getServiciosRealizados();
            if (servicios == null || servicios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay servicios registrados.");
                return;
            }

            StringBuilder sb = new StringBuilder("Servicios realizados:\n\n");
            for (ServicioRealizado sr : servicios) {
                String descripcion = sr.getServicio().getDescripcionServicio();
                String fecha = sr.getFecha().format(FORMATO_FECHA);
                sb.append("- ").append(descripcion).append(" (").append(fecha).append(")\n");
            }

            JOptionPane.showMessageDialog(this, sb.toString(), "Servicios Realizados", JOptionPane.INFORMATION_MESSAGE);
        });

        // Acción 3: Cerrar sesión
        btnSalir.addActionListener(e -> {
            new MenuLoginVentana(gestor).setVisible(true);
            dispose();
        });

        panel.add(btnVerDatos);
        panel.add(btnVerServicios);
        panel.add(btnSalir);

        add(panel);
    }
}
