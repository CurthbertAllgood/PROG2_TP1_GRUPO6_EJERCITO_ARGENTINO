package org.example.model.personal;

public class Oficial extends Militar{
    public Oficial(int codigo,String nombre, String apellidos) {
        super(codigo, nombre, apellidos);
    }

    @Override
    public String getTipo() {
        return "MILITAR";
    }

    @Override
    public String getGrado() {
        return "OFICIAL";
    }
}