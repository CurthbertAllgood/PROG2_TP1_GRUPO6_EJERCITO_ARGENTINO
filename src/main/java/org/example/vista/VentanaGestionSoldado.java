package org.example.vista;

import org.example.command.CrearSoldadoCommand;
import org.example.command.Invoker;
import org.example.model.personal.Soldado;
import org.example.model.personal.Usuario;
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
            JTextField campoUsuario = new JTextField();
            JTextField campoContrasenia = new JTextField();
            JTextField campoNombre = new JTextField();
            JTextField campoApellido = new JTextField();
            JTextField campoTipo = new JTextField();
            JTextField campoGrado = new JTextField();
            Object[] mensaje = {
                    "Usuario:", campoUsuario,
                    "Contraseña:", campoContrasenia,
                    "Nombre:", campoNombre,
                    "Apellido:", campoApellido,
                    "Tipo:",campoTipo,
                    "Grado:", campoGrado
            };

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Nuevo Soldado", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {

                Soldado nuevoSoldado = new Soldado(campoNombre.getText(), campoApellido.getText());


                Usuario nuevoUsuario = new Usuario(campoUsuario.getText(), campoContrasenia.getText());
                nuevoUsuario.setPersona(nuevoSoldado);


                Invoker invoker = new Invoker();
                invoker.agregarCommand(new CrearSoldadoCommand(nuevoSoldado, sistema));
                invoker.ejecutarTodo();


                modelo.addRow(new Object[]{
                        nuevoSoldado.getCodigo(),
                        nuevoSoldado.getNombre(),
                        nuevoSoldado.getApellidos()
                });


            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int codigo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                sistema.eliminarSoldado(codigo);
                modelo.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccioná un soldado para eliminar.");
            }
        });

        // Cerrar ventana
        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarSoldados() {
        List<Soldado> soldados = sistema.listar();
        for (Soldado s : soldados) {
            modelo.addRow(new Object[]{
                    s.getCodigo(),
                    s.getNombre(),
                    s.getApellidos()
            });
        }
    }
}
