package org.example.model.personal;

public class Suboficial extends Militar{


    public Suboficial(String nombre, String apellidos) {
        super(nombre, apellidos);
    }

    @Override
    public String getTipo() {
        return "MILITAR";
    }

    @Override
    public String getGrado() {
        return "SUBOFICIAL";
    }
}
