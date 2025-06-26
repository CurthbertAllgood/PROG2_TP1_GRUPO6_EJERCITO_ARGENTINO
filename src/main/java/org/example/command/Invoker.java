package org.example.command;

import java.util.List;

public class Invoker {
    private List<ICommand> commands;


    public void agregarCommand(ICommand command) {
        commands.add(command);
    }

    public void ejecutarTodo() {}
}
