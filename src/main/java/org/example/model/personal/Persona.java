package org.example.model.personal;

public abstract class Persona {
    protected static int codigo;
    protected String nombre;
    protected String apellidos;


    public Persona( String nombre, String apellidos) {
        this.codigo++;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }


    public static void inicializarLegajoDesde(int maxLegajo) {
        codigo = maxLegajo;
    }

    public static int generarNuevoLegajo() {
        return ++codigo;
    }
}
