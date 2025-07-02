package org.example.model.personal;

public class Oficial extends Militar{
    public Oficial(String nombre, String apellidos) {
        super(nombre, apellidos);
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
