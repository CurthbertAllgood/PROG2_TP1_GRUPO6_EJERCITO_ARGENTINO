package org.example.command;

import org.example.model.infraestructura.Compania;
import org.example.operacion.IGestionCompania;

public class CrearCompaniaCommand implements ICommand {

    private Compania compania;
    private IGestionCompania gestionCompania;

    @Override
    public void ejecutar() {

    }
}
