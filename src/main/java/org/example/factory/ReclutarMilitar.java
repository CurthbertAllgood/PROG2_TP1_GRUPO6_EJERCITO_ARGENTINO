package org.example.factory;

import org.example.model.personal.Militar;
import org.example.model.personal.Oficial;
import org.example.model.personal.Soldado;
import org.example.model.personal.Suboficial;

public class ReclutarMilitar {

    public static Militar crearMilitar(String grado, String nombre, String apellidos) {
        switch (grado.toUpperCase()) {
            case "SOLDADO":
                return new Soldado(nombre, apellidos);
            case "SUBOFICIAL":
                return new Suboficial(nombre, apellidos);
            case "OFICIAL":
                return new Oficial(nombre, apellidos);
            default:
                throw new IllegalArgumentException("Grado militar no reconocido: " + grado);
        }
    }
}
