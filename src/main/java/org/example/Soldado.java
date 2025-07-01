package org.example;

public class Soldado {


    private String nombre;
    private String apellido;
    private static int codigoSoldado;


    public Soldado(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        codigoSoldado++;
    }

    public void realizarServicio(){
        System.out.println("Realiza servicio");
    }


}
