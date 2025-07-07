package org.example.command;

import org.example.model.personal.Soldado;
import org.example.operacion.IAltaBajaSoldado;


public class CrearSoldadoCommand implements ICommand {
    private Soldado soldado;
    private IAltaBajaSoldado sistema;

    public CrearSoldadoCommand(Soldado soldado, IAltaBajaSoldado sistema) {
        this.soldado = soldado;
        this.sistema = sistema;
    }

    @Override
    public void ejecutar() {
        sistema.crearSoldado(soldado);
        System.out.println("✔ Soldado creado: " + soldado.getNombre());
    }

}
