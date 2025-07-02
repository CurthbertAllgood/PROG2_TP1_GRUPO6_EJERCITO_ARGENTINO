package org.example;

import org.example.model.personal.Militar;
import org.example.model.servicio.ServicioRealizado;
import org.example.persistencia.GestorServicios;
import org.example.persistencia.GestorUsuarios;
import org.example.vista.MenuLoginVentana;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String rutaUsuarios = "src/main/java/org/example/resources/usuarios.csv";
        String rutaServicios = "src/main/java/org/example/resources/servicios.csv";
        String rutaServiciosRealizados = "src/main/java/org/example/resources/servicios_realizados.csv";

        GestorUsuarios gestorUsuarios = new GestorUsuarios(rutaUsuarios);
        List<Militar> militares = gestorUsuarios.getMilitares();

        GestorServicios gestorServicios = new GestorServicios(rutaServicios, rutaServiciosRealizados, militares);

        for (ServicioRealizado sr : gestorServicios.getServiciosRealizados()) {
            sr.getSoldado().agregarServicioRealizado(sr);
        }


        for (ServicioRealizado s : gestorServicios.getServiciosRealizados()) {
            System.out.println(s);
        }

        SwingUtilities.invokeLater(() -> {
            MenuLoginVentana login = new MenuLoginVentana(gestorUsuarios, rutaUsuarios);

            login.setVisible(true);
        });
    }
}
