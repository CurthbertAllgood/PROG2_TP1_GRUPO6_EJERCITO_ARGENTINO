package org.example.model.personal;

public class Civil extends Persona {

    public Civil(String nombre, String apellidos) {
        super(nombre, apellidos);
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
