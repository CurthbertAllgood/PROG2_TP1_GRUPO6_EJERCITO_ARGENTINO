package org.example.model.servicio;

public class Mantenimiento extends Servicio {

    public Mantenimiento(int codigoServicio, String descripcionServicio) {
        super(codigoServicio, descripcionServicio);
    }

    @Override
    public String toString() {
        return "Mantenimiento{" + super.toString() + "}";
    }
}
