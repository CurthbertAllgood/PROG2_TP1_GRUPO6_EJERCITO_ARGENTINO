package org.example.model.personal;

public class Suboficial extends Militar{


    public Suboficial(int codigo,String nombre, String apellidos) {
        super(codigo,nombre, apellidos);
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