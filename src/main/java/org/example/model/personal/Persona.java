package org.example.model.personal;

public abstract class Persona {
    protected String codigo;
    protected String nombre;
    protected String apellidos;

    public Persona(String codigo, String nombre, String apellidos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
}
