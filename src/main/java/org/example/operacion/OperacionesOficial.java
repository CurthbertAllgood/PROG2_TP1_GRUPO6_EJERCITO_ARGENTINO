package org.example.operacion;

import org.example.model.personal.Soldado;
import org.example.model.personal.Usuario;
import org.example.persistencia.GestorUsuarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OperacionesOficial implements IAltaBajaSoldado {

    private final List<Soldado> soldados;
    private final GestorUsuarios gestorUsuarios;
    private final String rutaUsuarios;

    public OperacionesOficial(List<Soldado> soldados, GestorUsuarios gestorUsuarios, String rutaUsuarios) {
        // Se copia la lista para evitar mutaciones externas
        this.soldados = new ArrayList<>(soldados);
        this.gestorUsuarios = gestorUsuarios;
        this.rutaUsuarios = rutaUsuarios;
    }

    @Override
    public void crearSoldado(Soldado s) {
        if (s == null) {
            System.err.println("⚠ No se puede agregar un soldado nulo.");
            return;
        }

        soldados.add(s);
        System.out.println("✔ Soldado creado: " + s.getNombre());

        Usuario nuevo = new Usuario(s); // Crear Usuario desde el Soldado
        gestorUsuarios.guardarUsuario(nuevo, rutaUsuarios); // Persistir en CSV
    }

    @Override
    public void eliminarSoldado(int codigo) {
        // También podrías eliminar el usuario relacionado aquí, si es necesario.
        boolean eliminado = false;
        Iterator<Soldado> it = soldados.iterator();
        while (it.hasNext()) {
            if (it.next().getCodigo() == codigo) {
                it.remove();
                eliminado = true;
                break;
            }
        }

        if (eliminado) {
            System.out.println("✘ Soldado eliminado: " + codigo);
        } else {
            System.err.println("⚠ No se encontró el soldado con código: " + codigo);
        }
    }

    public List<Soldado> listar() {
        return new ArrayList<>(soldados); // Devuelve una copia defensiva
    }
}
