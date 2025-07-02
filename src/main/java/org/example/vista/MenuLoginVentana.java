package org.example.vista;

import org.example.model.personal.*;
import org.example.persistencia.GestorUsuarios;

import javax.swing.*;
import java.awt.*;

public class MenuLoginVentana extends JFrame {

    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JButton ingresarButton;
    private final GestorUsuarios gestor;

    public MenuLoginVentana(GestorUsuarios gestor) {
        this.gestor = gestor;
        initComponents();
    }

    private void initComponents() {
        setTitle("Login Militar");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Usuario:"), gbc);

        usuarioField = new JTextField(15);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(usuarioField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Contraseña:"), gbc);

        contrasenaField = new JPasswordField(15);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(contrasenaField, gbc);


        ingresarButton = new JButton("Ingresar");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(ingresarButton, gbc);

        ingresarButton.addActionListener(e -> {
            String inputUser = usuarioField.getText();
            String inputPass = new String(contrasenaField.getPassword());

            Usuario u = gestor.login(inputUser, inputPass);

            if (u != null) {
                elegirMenu(u.getPersona());
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }
        });

        add(panel);
    }

    private void elegirMenu(Persona persona) {
        if (persona instanceof Soldado) {
            new MenuSoldadoVentana((Soldado) persona, gestor).setVisible(true);
        } else if (persona instanceof Suboficial) {
            new MenuSuboficialVentana((Suboficial) persona, gestor).setVisible(true);
        } else if (persona instanceof Oficial) {
            new MenuOficialVentana((Oficial) persona, gestor).setVisible(true);
        } else if (persona instanceof Civil) {
            JOptionPane.showMessageDialog(this, "Bienvenido, civil.");
        }
    }
}
