package org.example;

public abstract class Usuario {
    private Soldado soldado;
    private String user;
    private String password;
    private String codigo;
         


    public void login(){
        System.out.println("Se prueba el login");
    }

    public void consulta(){
        System.out.println("Se prueba la cons");
    }

    public void admin(){
        System.out.println("Admin");
    }


}
