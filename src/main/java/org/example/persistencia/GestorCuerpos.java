package org.example.persistencia;

import org.example.model.infraestructura.Cuerpo;

import java.util.ArrayList;
import java.util.List;

public class GestorCuerpos {
    private List<Cuerpo> cuerpos;

    public GestorCuerpos(String rutaCSV) {
        this.cuerpos = cargarDesdeCSV(rutaCSV);
    }

    private List<Cuerpo> cargarDesdeCSV(String ruta) {
        List<Cuerpo> lista = new ArrayList<>();
        List<String[]> filas = PersistenciaTexto.leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 2) continue;

            String codigo = fila[0];
            String denominacion = fila[1];

            lista.add(new Cuerpo(codigo, denominacion));
        }

        return lista;
    }

    public List<Cuerpo> getCuerpos() {
        return cuerpos;
    }

    public Cuerpo buscarPorCodigo(String codigo) {
        return cuerpos.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }
}
