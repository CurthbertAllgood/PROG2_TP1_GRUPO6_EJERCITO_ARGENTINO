package org.example.persistencia;

import org.example.model.personal.Militar;
import org.example.model.servicio.Servicio;
import org.example.model.servicio.ServicioRealizado;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestorServicios {
    private List<Servicio> servicios;
    private List<ServicioRealizado> realizados;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public GestorServicios(String rutaServicios, String rutaRealizados, List<Militar> militares) {
        this.servicios = cargarServicios(rutaServicios);
        this.realizados = cargarServiciosRealizados(rutaRealizados, militares);
    }

    private List<Servicio> cargarServicios(String ruta) {
        List<Servicio> lista = new ArrayList<>();
        List<String[]> filas = PersistenciaTexto.leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 2) continue;

            int codigo = Integer.parseInt(fila[0]);
            String descripcion = fila[1];
            lista.add(new Servicio(codigo, descripcion));
        }

        return lista;
    }

    private List<ServicioRealizado> cargarServiciosRealizados(String ruta, List<Militar> militares) {
        List<ServicioRealizado> lista = new ArrayList<>();
        List<String[]> filas = PersistenciaTexto.leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 3) continue;

            int scodMilitar = Integer.parseInt(fila[0]);
            int codServicio = Integer.parseInt(fila[1]);
            LocalDateTime fecha = LocalDateTime.parse(fila[2], FORMATO);

            Militar militar = militares.stream()
                    .filter(m -> m.getCodigo()== scodMilitar)
                    .findFirst()
                    .orElse(null);

            Servicio servicio = servicios.stream()
                    .filter(s -> s.getCodigoServicio() == codServicio)
                    .findFirst()
                    .orElse(null);

            if (militar != null && servicio != null) {
                lista.add(new ServicioRealizado(militar, fecha, servicio));
            }
        }

        return lista;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public List<ServicioRealizado> getServiciosRealizados() {
        return realizados;
    }
}