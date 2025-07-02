package org.example.persistencia;

import org.example.model.personal.Militar;
import org.example.model.personal.Persona;
import org.example.model.personal.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios(String rutaCSV) {
        this.usuarios = cargarDesdeCSV(rutaCSV);
        inicializarLegajoMaximo();
    }

    private List<Usuario> cargarDesdeCSV(String ruta) {
        List<Usuario> lista = new ArrayList<>();
        List<String[]> filas = PersistenciaTexto.leerCSV(ruta);

        for (String[] fila : filas) {
            if (fila.length < 6) continue;

            String user = fila[0];
            String pass = fila[1];
            String codigo = fila[2];
            String nombre = fila[3];
            String apellido = fila[4];
            String tipo = fila[5];
            String grado = fila.length > 6 ? fila[6] : null;

            Usuario u = new Usuario(user, pass);
            u.inicializarPersona(tipo, grado, nombre, apellido);
            lista.add(u);
        }

        return lista;
    }

    private void inicializarLegajoMaximo() {
        int maxLegajo = usuarios.stream()
                .map(u -> {
                    try {
                        return u.getPersona().getCodigo();
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max(Comparator.naturalOrder())
                .orElse(0);

        Persona.inicializarLegajoDesde(maxLegajo);
    }

    public List<Militar> getMilitares() {
        List<Militar> militares = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getPersona() instanceof Militar m) {
                militares.add(m);
            }
        }
        return militares;
    }

    public Usuario login(String userInput, String passInput) {
        return usuarios.stream()
                .filter(u -> u.login(userInput, passInput))
                .findFirst()
                .orElse(null);
    }
}
