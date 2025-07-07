package org.example.factory;

import org.example.model.personal.Civil;
import org.example.model.personal.Persona;
import org.example.model.personal.Usuario;

public class UsuarioFactory {

    public static Usuario crearNuevoUsuario(String user, String pass, String tipo, String grado, String nombre, String apellido) {
        int nuevoCodigo = Persona.generarNuevoLegajo();
        Persona persona;

        if ("MILITAR".equalsIgnoreCase(tipo)) {
            persona = ReclutarMilitar.crearMilitar(nuevoCodigo, grado, nombre, apellido, tipo);
        } else if ("CIVIL".equalsIgnoreCase(tipo)) {
            persona = new Civil(nuevoCodigo, nombre, apellido);
        } else {
            throw new IllegalArgumentException("Tipo de persona no reconocido: " + tipo);
        }

        return new Usuario(user, pass, persona);
    }
}
