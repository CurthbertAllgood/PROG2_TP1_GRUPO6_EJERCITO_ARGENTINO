package org.example.model.infraestructura;

import org.example.model.personal.Soldado;

import java.util.ArrayList;
import java.util.List;

public class Compania {
    private String codigo;
    private int numero;
    private Cuartel cuartel;
    private List<Soldado> soldados;

    public Compania(String codigo, int numero, Cuartel cuartel) {
        this.codigo = codigo;
        this.numero = numero;
        this.cuartel = cuartel;
        this.soldados = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public int getNumero() {
        return numero;
    }

    public Cuartel getCuartel() {
        return cuartel;
    }

    public List<Soldado> getSoldados() {
        return soldados;
    }

    public void agregarSoldado(Soldado s) {
        soldados.add(s);
    }

    @Override
    public String toString() {
        return "Compañía " + numero + " (" + cuartel.getNombre() + ")";
    }
}
