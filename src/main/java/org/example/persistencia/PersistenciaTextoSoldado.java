package org.example.persistencia;

import org.example.model.Soldado;

import java.util.List;

public class PersistenciaTextoSoldado implements Persistencia<Soldado> {


    @Override
    public void guardar(Soldado entidad) {

    }

    @Override
    public List<Soldado> cargar() {
        return List.of();
    }
}
