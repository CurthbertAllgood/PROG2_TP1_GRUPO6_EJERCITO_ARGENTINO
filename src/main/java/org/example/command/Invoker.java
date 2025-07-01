package org.example.command;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    private List<ICommand> commands = new ArrayList<>();

    public void agregarCommand(ICommand c) {
        commands.add(c);
    }

    public void ejecutarTodo() {
        for (ICommand c : commands) {
            c.ejecutar();
        }
        commands.clear();
    }
}
