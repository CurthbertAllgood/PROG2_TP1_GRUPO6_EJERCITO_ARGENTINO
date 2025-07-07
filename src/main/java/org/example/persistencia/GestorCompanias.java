package org.example.persistencia;

import org.example.model.infraestructura.Compania;
import org.example.model.infraestructura.Cuartel;

import java.util.ArrayList;
import java.util.List;

public class GestorCompanias {
    private List<Compania> companias;

    public GestorCompanias(String rutaCSV, List<Cuartel> cuarteles) {
        this.companias = cargarDesdeCSV(rutaCSV, cuarteles);
    }

    private List<Compania> cargarDesdeCSV(String ruta, List<Cuartel> cuarteles) {
        List<Compania> lista = new ArrayList<>();
        List<String[]> filas = PersistenciaTexto.leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 3) continue;

            String codigo = fila[0];
            int numero;
            try {
                numero = Integer.parseInt(fila[1]);
            } catch (NumberFormatException e) {
                System.err.println("Número inválido para compañía: " + fila[1]);
                continue;
            }

            String cuartelCodigo = fila[2];

            Cuartel cuartel = cuarteles.stream()
                    .filter(c -> c.getCodigo().equalsIgnoreCase(cuartelCodigo))
                    .findFirst()
                    .orElse(null);

            if (cuartel != null) {
                Compania compania = new Compania(codigo, numero, cuartel);
                lista.add(compania);
                cuartel.agregarCompania(compania);
            } else {
                System.err.println("⚠ Cuartel no encontrado para compañía: " + codigo);
            }
        }

        return lista;
    }

    public List<Compania> getCompanias() {
        return companias;
    }

    public Compania buscarPorCodigo(String codigo) {
        return companias.stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }
}
