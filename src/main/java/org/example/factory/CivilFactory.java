package org.example.factory;

import org.example.model.personal.Civil;
import org.example.model.personal.Persona;

public class CivilFactory {

    public static Persona crearCivil(int codigo,String nombre, String apellidos) {

        return new Civil(codigo,nombre, apellidos);
    }


}