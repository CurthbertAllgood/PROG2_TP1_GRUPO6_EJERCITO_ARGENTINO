package org.example.factory;

import org.example.model.personal.Militar;
import org.example.model.personal.Oficial;
import org.example.model.personal.Soldado;
import org.example.model.personal.Suboficial;

public class ReclutarMilitar {

    public static Militar crearMilitar(int codigo,String grado, String nombre, String apellidos, String s) {
        switch (grado.toUpperCase()) {
            case "SOLDADO":
                return new Soldado(codigo,nombre, apellidos);
            case "SUBOFICIAL":
                return new Suboficial(codigo,nombre, apellidos);
            case "OFICIAL":
                return new Oficial(codigo,nombre, apellidos);
            default:
                throw new IllegalArgumentException("Grado militar no reconocido: " + grado);
        }
    }
}