package org.example.persistencia;

import org.example.model.Militar;

import java.util.List;

public class PersistenciaTextoSoldado implements Persistencia<Militar> {


    @Override
    public void guardar(Militar entidad) {

    }

    @Override
    public List<Militar> cargar() {
        return List.of();
    }
}
