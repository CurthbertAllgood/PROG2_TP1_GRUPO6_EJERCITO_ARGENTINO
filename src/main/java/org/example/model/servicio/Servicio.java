package org.example.model.servicio;

public class Servicio {
    private int codigoServicio;
    private String descripcionServicio;

    public Servicio(int codigoServicio, String descripcionServicio) {
        this.codigoServicio = codigoServicio;
        this.descripcionServicio = descripcionServicio;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "codigo=" + codigoServicio +
                ", descripcion='" + descripcionServicio + '\'' +
                '}';
    }
}
