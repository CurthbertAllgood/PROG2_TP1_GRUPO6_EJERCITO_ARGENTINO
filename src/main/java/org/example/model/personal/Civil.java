package org.example.model.personal;

public class Civil extends Persona {

    public Civil(int codigo, String nombre, String apellidos) {
        super(codigo, nombre, apellidos);
    }

    @Override
    public String getTipo() {
        return "CIVIL";
    }

    @Override
    public String getGrado() {
        return "";
    }


}