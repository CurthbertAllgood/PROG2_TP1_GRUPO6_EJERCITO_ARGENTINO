package org.example.model.infraestructura;

import java.util.ArrayList;
import java.util.List;

public class Cuartel {
    private String codigo;
    private String nombre;
    private String ubicacion;
    private List<Compania> companias;

    public Cuartel(String codigo, String nombre, String ubicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.companias = new ArrayList<>();
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public List<Compania> getCompanias() { return companias; }

    public void agregarCompania(Compania c) {
        this.companias.add(c);
    }

    @Override
    public String toString() {
        return nombre + " (" + ubicacion + ")";
    }
}
