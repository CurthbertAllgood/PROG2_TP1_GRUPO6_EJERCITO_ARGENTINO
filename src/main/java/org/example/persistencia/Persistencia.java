package org.example.persistencia;

import java.util.List;

public interface Persistencia<T> {
    public void guardar(T entidad);
    public List<T> cargar();

}
