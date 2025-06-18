package org.example;

public class Soldado {


    private String nombre;
    private String apellido;
    private Grado grado;
    private static int codigoSoldado;


    public Soldado(String nombre, String apellido, Grado grado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.grado = grado;
        codigoSoldado++;
    }

    public void realizarServicio(){

    }


}
