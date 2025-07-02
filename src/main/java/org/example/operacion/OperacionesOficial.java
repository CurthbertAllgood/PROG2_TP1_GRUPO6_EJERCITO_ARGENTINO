package org.example.operacion;

import org.example.model.personal.Soldado;

import java.util.ArrayList;
import java.util.List;

public class OperacionesOficial implements IAltaBajaSoldado {
    private List<Soldado> soldados;

    public OperacionesOficial(List<Soldado> soldados) {
        this.soldados = soldados;
    }

    @Override
    public void crearSoldado(Soldado s) {
        soldados.add(s);
        System.out.println("Soldado creado: " + s.getNombre());
    }
    @Override
    public void eliminarSoldado(int codigo) {
        soldados.removeIf(s -> s.getCodigo() == codigo);
        System.out.println("Soldado eliminado: " + codigo);
    }

    public List<Soldado> listar() {
        return new ArrayList<>(soldados);
    }
}

