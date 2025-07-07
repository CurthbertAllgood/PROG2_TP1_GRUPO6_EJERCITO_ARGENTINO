package org.example.vista;

import org.example.factory.UsuarioFactory;
import org.example.model.personal.Persona;
import org.example.model.personal.Usuario;
import org.example.persistencia.GestorUsuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaGestionUsuarios extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private GestorUsuarios gestor;
    private String rutaUsuarios;

    public VentanaGestionUsuarios(GestorUsuarios gestor, String rutaUsuarios) {
        this.gestor = gestor;
        this.rutaUsuarios = rutaUsuarios;

        setTitle("Gestión de Usuarios");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Apellido", "Tipo", "Grado"}, 0);
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

        cargarUsuarios();

        btnAgregar.addActionListener(e -> {
            JTextField campoUsuario = new JTextField();
            JTextField campoContrasenia = new JTextField();
            JTextField campoNombre = new JTextField();
            JTextField campoApellido = new JTextField();

            JComboBox<String> comboTipo = new JComboBox<>(new String[]{"MILITAR", "CIVIL"});
            JComboBox<String> comboGrado = new JComboBox<>();
            comboGrado.setModel(new DefaultComboBoxModel<>(new String[]{"SOLDADO", "SUBOFICIAL", "OFICIAL"}));

            comboTipo.addActionListener(event -> {
                String tipo = comboTipo.getSelectedItem().toString();
                if (tipo.equalsIgnoreCase("MILITAR")) {
                    comboGrado.setModel(new DefaultComboBoxModel<>(new String[]{"SOLDADO", "SUBOFICIAL", "OFICIAL"}));
                } else {
                    comboGrado.setModel(new DefaultComboBoxModel<>(new String[]{"-"}));
                }
            });

            Object[] mensaje = {
                    "Usuario:", campoUsuario,
                    "Contraseña:", campoContrasenia,
                    "Nombre:", campoNombre,
                    "Apellido:", campoApellido,
                    "Tipo:", comboTipo,
                    "Grado:", comboGrado
            };

            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Nuevo Usuario", JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
                String user = campoUsuario.getText();
                String pass = campoContrasenia.getText();
                String nombre = campoNombre.getText();
                String apellido = campoApellido.getText();
                String tipo = comboTipo.getSelectedItem().toString();
                String grado = comboGrado.getSelectedItem().toString();

                try {
                    Usuario nuevoUsuario = UsuarioFactory.crearNuevoUsuario(user, pass, tipo, grado, nombre, apellido);
                    gestor.guardarUsuario(nuevoUsuario, rutaUsuarios);

                    modelo.addRow(new Object[]{
                            nuevoUsuario.getCodigo(),
                            nuevoUsuario.getNombre(),
                            nuevoUsuario.getApellidos(),
                            nuevoUsuario.getPersona().getTipo(),
                            nuevoUsuario.getPersona().getGrado()
                    });

                    JOptionPane.showMessageDialog(this, "Usuario creado correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al crear usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                int codigo = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                gestor.eliminarUsuario(codigo, rutaUsuarios);
                modelo.removeRow(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccioná un usuario para eliminar.");
            }
        });

        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarUsuarios() {
        for (Usuario u : gestor.getUsuarios()) {
            Persona p = u.getPersona();
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getApellidos(),
                    p.getTipo(),
                    p.getGrado()
            });
        }
    }
}
