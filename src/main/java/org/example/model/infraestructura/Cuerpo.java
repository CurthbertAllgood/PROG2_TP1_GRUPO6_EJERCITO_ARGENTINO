package org.example.model.infraestructura;

public class Cuerpo {
    private String codigo;
    private String denominacion;

    public Cuerpo(String codigo, String denominacion) {
        this.codigo = codigo;
        this.denominacion = denominacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    @Override
    public String toString() {
        return denominacion + " (" + codigo + ")";
    }
}
