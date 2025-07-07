package org.example.persistencia;

import org.example.model.infraestructura.Cuartel;

import java.util.ArrayList;
import java.util.List;

public class GestorCuarteles {
    private List<Cuartel> cuarteles;

    public GestorCuarteles(String rutaCSV) {
        this.cuarteles = cargarDesdeCSV(rutaCSV);
    }

    private List<Cuartel> cargarDesdeCSV(String ruta) {
        List<Cuartel> lista = new ArrayList<>();
        List<String[]> filas = PersistenciaTexto.leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 3) continue;

            String codigo = fila[0];
            String nombre = fila[1];
            String ubicacion = fila[2];
            lista.add(new Cuartel(codigo, nombre, ubicacion));
        }
        return lista;
    }

    public List<Cuartel> getCuarteles() {
        return cuarteles;
    }

    public Cuartel buscarPorCodigo(String codigo) {
        return cuarteles.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }
}
