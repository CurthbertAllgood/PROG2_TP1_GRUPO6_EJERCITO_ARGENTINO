package org.example.operacion;

import org.example.model.personal.Soldado;
import org.example.model.personal.Usuario;

public interface IAltaBajaSoldado {
    void crearSoldado(Soldado s);
    void eliminarSoldado(int codigo);
}
