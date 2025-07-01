package org.example.operacion;

import org.example.model.personal.Soldado;

public interface IAltaBajaSoldado {
    void crearSoldado(Soldado s);
    void eliminarSoldado(String codigo);
}
