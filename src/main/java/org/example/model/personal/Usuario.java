package org.example.model.personal;

import org.example.factory.ReclutarMilitar;
import org.example.factory.CivilFactory;

public class Usuario {
    private String user;
    private String password;
    private Persona persona;

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Usuario(Persona persona) {
        this.persona = persona;
    }


    public Usuario(String user, String password, Persona persona) {
        this.user = user;
        this.password = password;
        this.persona = persona;
    }

    public boolean login(String inputUser, String inputPass) {
        return this.user.equals(inputUser) && this.password.equals(inputPass);
    }

    public void inicializarPersona(int codigo, String tipo, String grado, String nombre, String apellidos) {
        if (tipo.equalsIgnoreCase("MILITAR")) {
            this.persona = ReclutarMilitar.crearMilitar(codigo, grado, nombre, apellidos, tipo);
        } else if (tipo.equalsIgnoreCase("CIVIL")) {
            this.persona = CivilFactory.crearCivil(codigo, nombre, apellidos);
        } else {
            throw new IllegalArgumentException("Tipo de persona no reconocido: " + tipo);
        }
    }

    public int getCodigo() {
        return persona.getCodigo();
    }

    public String getNombre() {
        return persona.getNombre();
    }

    public String getApellidos() {
        return persona.getApellidos();
    }

    public Persona getPersona() {
        return persona;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
