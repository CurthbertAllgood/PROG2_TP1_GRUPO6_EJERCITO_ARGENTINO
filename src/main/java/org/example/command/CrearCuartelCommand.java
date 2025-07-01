package org.example.command;

import org.example.model.infraestructura.Cuartel;
import org.example.operacion.IGestionCuartel;

public class CrearCuartelCommand implements ICommand {

    private Cuartel cuartel;
    private IGestionCuartel gestionCuartel;

    @Override
    public void ejecutar() {

    }
}
