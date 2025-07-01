package org.example.model.personal;

import org.example.factory.MilitarFactory;
import org.example.factory.CivilFactory;

public class Usuario {
    private String user;
    private String password;
    private Persona persona;

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public boolean login(String inputUser, String inputPass) {
        return this.user.equals(inputUser) && this.password.equals(inputPass);
    }

    public void inicializarPersona(String tipo, String grado, String codigo, String nombre, String apellidos) {
        if (tipo.equalsIgnoreCase("MILITAR")) {
            this.persona = MilitarFactory.crearMilitar(grado, codigo, nombre, apellidos);
        } else if (tipo.equalsIgnoreCase("CIVIL")) {
            this.persona = CivilFactory.crearCivil(codigo, nombre, apellidos);
        } else {
            throw new IllegalArgumentException("Tipo de persona no reconocido");
        }
    }

    // Delegación
    public String getCodigo() {
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
}
