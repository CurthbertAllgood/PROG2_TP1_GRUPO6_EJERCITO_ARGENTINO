package org.example.model.servicio;

public class Limpieza extends Servicio {

    public Limpieza(int codigoServicio, String descripcionServicio) {
        super(codigoServicio, descripcionServicio);
    }

    @Override
    public String toString() {
        return "Limpieza{" + super.toString() + "}";
    }
}
