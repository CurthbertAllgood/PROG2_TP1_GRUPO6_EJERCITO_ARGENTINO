package org.example.persistencia;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PersistenciaTexto {

    public static List<String[]> leerCSV(String rutaArchivo) {
        List<String[]> datos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                datos.add(campos);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return datos;
    }

    // ================================
    // MÉTODOS ESPECIALIZADOS
    // ================================
/*
    public static List<Usuario> cargarUsuarios(String ruta) {
        List<Usuario> usuarios = new ArrayList<>();
        List<String[]> filas = leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 6) continue; // Validación mínima

            String user = fila[0];
            String pass = fila[1];
            String codigo = fila[2];
            String nombre = fila[3];
            String apellido = fila[4];
            String tipo = fila[5];
            String grado = fila.length > 6 ? fila[6] : null;

            Usuario u = new Usuario(user, pass);
            u.inicializarPersona(tipo, grado, codigo, nombre, apellido);
            usuarios.add(u);
        }

        return usuarios;
    }

    public static List<Servicio> cargarServicios(String ruta) {
        List<Servicio> servicios = new ArrayList<>();
        List<String[]> filas = leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 2) continue;
            int codigo = Integer.parseInt(fila[0]);
            String descripcion = fila[1];

            Servicio s = new Servicio(codigo, descripcion);
            servicios.add(s);
        }

        return servicios;
    }

    public static List<ServicioRealizado> cargarServiciosRealizados(String ruta, List<Militar> militares, List<Servicio> servicios) {
        List<ServicioRealizado> realizados = new ArrayList<>();
        List<String[]> filas = leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 3) continue;

            String codigoMilitar = fila[0];
            int codigoServicio = Integer.parseInt(fila[1]);
            Date fecha = new Date(Long.parseLong(fila[2]));

            Militar m = militares.stream()
                    .filter(s -> s.getCodigo().equals(codigoMilitar))
                    .findFirst()
                    .orElse(null);

            Servicio serv = servicios.stream()
                    .filter(s -> s.getCodigoServicio() == codigoServicio)
                    .findFirst()
                    .orElse(null);

            if (m != null && serv != null) {
                realizados.add(new ServicioRealizado(m, fecha, serv));
            }
        }

        return realizados;
    }
    */

}
