package org.example.model.personal;

public class Soldado extends Militar {

    public Soldado(int codigo, String nombre, String apellidos) {
        super(codigo, nombre, apellidos);
    }

    @Override
    public String getTipo() {
        return "MILITAR";
    }

    @Override
    public String getGrado() {
        return "SOLDADO";
    }
}
