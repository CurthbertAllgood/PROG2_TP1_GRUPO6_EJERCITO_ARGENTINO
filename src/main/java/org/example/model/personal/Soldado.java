package org.example.model.personal;

public class Soldado extends Militar{
    public Soldado(String nombre, String apellidos) {

        super(nombre, apellidos);
    }

    @Override
    public String getTipo() {
        return "MILITAR";
    }
    @Override
    public String getGrado() {return "SOLDADO";}

}
