package org.example.command;

import org.example.model.Militar;
import org.example.operacion.IModificarSoldado;

public class CrearSoldadoCommand implements ICommand {

    private Militar soldado;
    private IModificarSoldado gestionSoldado;

    @Override
    public void ejecutar() {

    }
}
