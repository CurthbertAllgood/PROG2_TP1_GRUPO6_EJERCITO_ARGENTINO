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
        List<String[]> filas = PersistenciaTexto.leerCSV(rutaCSV);
        inicializarLegajoMaximo(filas);
        this.usuarios = cargarDesdeCSV(filas);
    }

    // Inicializa el código más alto antes de crear objetos Persona
    private void inicializarLegajoMaximo(List<String[]> filas) {
        int maxLegajo = filas.stream()
                .map(fila -> {
                    try {
                        return Integer.parseInt(fila[2]);
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .max(Comparator.naturalOrder())
                .orElse(0);

        Persona.inicializarLegajoDesde(maxLegajo);
    }

    // Carga usuarios desde CSV una vez que el código está inicializado
    private List<Usuario> cargarDesdeCSV(List<String[]> filas) {
        List<Usuario> lista = new ArrayList<>();

        for (String[] fila : filas) {
            if (fila.length < 6) continue;

            String user = fila[0];
            String pass = fila[1];
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

    public List<Militar> getMilitares() {
        List<Militar> militares = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getPersona() instanceof Militar m) {
                militares.add(m);
            }
        }
        return militares;
    }

    public void guardarUsuario(Usuario nuevo, String ruta) {
        String[] datos = {
                nuevo.getUser(),
                nuevo.getPassword(),
                String.valueOf(nuevo.getPersona().getCodigo()),
                nuevo.getPersona().getNombre(),
                nuevo.getPersona().getApellidos(),
                nuevo.getPersona().getTipo(),
                nuevo.getPersona().getGrado()
        };

        PersistenciaTexto.agregarLineaCSV(ruta, datos);
        usuarios.add(nuevo);
    }

    public Usuario login(String userInput, String passInput) {
        return usuarios.stream()
                .filter(u -> u.login(userInput, passInput))
                .findFirst()
                .orElse(null);
    }
}
