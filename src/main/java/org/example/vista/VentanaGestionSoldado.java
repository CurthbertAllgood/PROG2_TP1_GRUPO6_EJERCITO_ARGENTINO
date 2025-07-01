package org.example.vista;

import org.example.command.Invoker;
import org.example.command.CrearSoldadoCommand;
import org.example.model.personal.Soldado;
import org.example.operacion.OperacionesOficial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaGestionSoldado extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private OperacionesOficial sistema;

    public VentanaGestionSoldado(OperacionesOficial sistema) {
        this.sistema = sistema;

        setTitle("Gestión de Soldados");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Apellido"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCerrar = new JButton("Cerrar");

        JPanel botones = new JPanel();
        botones.add(btnAgregar);
        botones.add(btnEliminar);
        botones.add(btnCerrar);

        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        cargarSoldados();

        btnAgregar.addActionListener(e -> {
            JTextField campoCodigo = new JTextField();
            JTextField campoNombre = new JTextField();
            JTextField campoApellido = new JTextField();

            Object[] mensaje = {
                    "Código:", campoCodigo,
                    "Nombre:", campoNombre,
                    "Apellido:", campoApellido
            };

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Nuevo Soldado", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                Soldado nuevo = new Soldado(
                        campoCodigo.getText(),
                        campoNombre.getText(),
                        campoApellido.getText()
                );

                Invoker invoker = new Invoker();
                invoker.agregarCommand(new CrearSoldadoCommand(nuevo, sistema));
                invoker.ejecutarTodo();

                modelo.addRow(new Object[]{nuevo.getCodigo(), nuevo.getNombre(), nuevo.getApellidos()});
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                String codigo = modelo.getValueAt(fila, 0).toString();
                sistema.eliminarSoldado(codigo);
                modelo.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccioná un soldado para eliminar.");
            }
        });

        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarSoldados() {
        List<Soldado> soldados = sistema.listar();
        for (Soldado s : soldados) {
            modelo.addRow(new Object[]{s.getCodigo(), s.getNombre(), s.getApellidos()});
        }
    }
}
