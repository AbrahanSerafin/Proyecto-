package com.mycompany.proy;
import javax.swing.*;

public class VentanaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public VentanaLogin() {
        setTitle("Login - Sistema de Álbum");
        setSize(330, 210);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblU = new JLabel("Usuario:");
        lblU.setBounds(30, 30, 80, 25);
        add(lblU);
        txtUsuario = new JTextField();
        txtUsuario.setBounds(110, 30, 170, 25);
        add(txtUsuario);

        JLabel lblP = new JLabel("Contraseña:");
        lblP.setBounds(30, 70, 80, 25);
        add(lblP);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(110, 70, 170, 25);
        add(txtPassword);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(110, 120, 100, 30);
        add(btnIngresar);

        btnIngresar.addActionListener(e -> {
            String u = txtUsuario.getText().trim();
            String p = new String(txtPassword.getPassword());

            if (u.equals("admin") && p.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador", "Acceso Exitoso", JOptionPane.INFORMATION_MESSAGE);
                new VentanaAdmin().setVisible(true);
                dispose();
            } else if (u.equals("user") && p.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Bienvenido Usuario", "Acceso Exitoso", JOptionPane.INFORMATION_MESSAGE);
                new VentanaAlbumUsuario().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}