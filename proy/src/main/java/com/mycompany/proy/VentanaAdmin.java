package com.mycompany.proy;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaAdmin extends JFrame {
    private ControladorAlbum controlador;
    private JTextField txtEquipo, txtNombre, txtPosicion, txtNumero, txtNacionalidad, txtEdad;
    private JComboBox<String> cbDivision;
    private JLabel lblRutaFoto, lblRutaEscudo, lblPreviewFoto, lblPreviewEscudo;

    public VentanaAdmin() {
        controlador = new ControladorAlbum();
        setTitle("Panel de Administración - Álbum de Estampillas");
        setSize(780, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        crearCampo("Equipo:", 20, 20, txtEquipo = new JTextField());
        crearCampo("Nombre Persona:", 20, 55, txtNombre = new JTextField());
        crearCampo("Posición:", 20, 90, txtPosicion = new JTextField());
        crearCampo("Número:", 20, 125, txtNumero = new JTextField());
        crearCampo("Nacionalidad:", 20, 160, txtNacionalidad = new JTextField());
        crearCampo("Edad:", 20, 195, txtEdad = new JTextField());

        JLabel lblDiv = new JLabel("División:");
        lblDiv.setBounds(20, 230, 120, 25);
        add(lblDiv);
        cbDivision = new JComboBox<>(new String[]{"Varonil", "Femenil"});
        cbDivision.setBounds(150, 230, 180, 25);
        add(cbDivision);

        JButton btnFoto = new JButton("Foto Jugador...");
        btnFoto.setBounds(20, 270, 125, 25);
        add(btnFoto);
        lblRutaFoto = new JLabel("Sin Selección");
        lblRutaFoto.setBounds(150, 270, 180, 25);
        add(lblRutaFoto);

        JButton btnEscudo = new JButton("Escudo Equipo...");
        btnEscudo.setBounds(20, 305, 125, 25);
        add(btnEscudo);
        lblRutaEscudo = new JLabel("Sin Selección");
        lblRutaEscudo.setBounds(150, 305, 180, 25);
        add(lblRutaEscudo);

        JPanel panelPreview = new JPanel(null);
        panelPreview.setBorder(BorderFactory.createTitledBorder("Vista Previa Estampilla"));
        panelPreview.setBounds(360, 20, 380, 310);
        add(panelPreview);

        lblPreviewFoto = new JLabel("Foto", SwingConstants.CENTER);
        lblPreviewFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblPreviewFoto.setBounds(20, 30, 180, 220);
        panelPreview.add(lblPreviewFoto);

        lblPreviewEscudo = new JLabel("Escudo", SwingConstants.CENTER);
        lblPreviewEscudo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblPreviewEscudo.setBounds(220, 30, 130, 130);
        panelPreview.add(lblPreviewEscudo);

        JButton btnAgregar = crearBoton("Agregar", 20, 370);
        JButton btnConsultar = crearBoton("Consultar", 140, 370);
        JButton btnModificar = crearBoton("Modificar", 260, 370);
        JButton btnEliminar = crearBoton("Eliminar", 380, 370);
        JButton btnLimpiar = crearBoton("Limpiar", 500, 370);
        JButton btnCerrar = crearBoton("Cerrar Sesión", 620, 370);

        btnFoto.addActionListener(e -> seleccionarImagen(lblRutaFoto, lblPreviewFoto, 180, 220));
        btnEscudo.addActionListener(e -> seleccionarImagen(lblRutaEscudo, lblPreviewEscudo, 130, 130));

        btnAgregar.addActionListener(e -> {
            if (!validar()) return;
            try {
                Jugador j = new Jugador(txtEquipo.getText().trim(), txtNombre.getText().trim(), txtPosicion.getText().trim(),
                        Integer.parseInt(txtNumero.getText().trim()), txtNacionalidad.getText().trim(),
                        Integer.parseInt(txtEdad.getText().trim()), cbDivision.getSelectedItem().toString(),
                        lblRutaFoto.getText(), lblRutaEscudo.getText());
                if (controlador.agregarJugador(j)) {
                    JOptionPane.showMessageDialog(this, "Estampilla agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "El número de jugador ya existe en ese equipo y división.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Revise los campos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnConsultar.addActionListener(e -> {
            String eq = txtEquipo.getText().trim();
            if (eq.isEmpty() || txtNumero.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el equipo y número para consultar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int num = Integer.parseInt(txtNumero.getText().trim());
                Jugador j = controlador.consultarJugador(eq, num);
                if (j != null) {
                    txtNombre.setText(j.getNombrePersona());
                    txtPosicion.setText(j.getPosicion());
                    txtNacionalidad.setText(j.getNacionalidad());
                    txtEdad.setText(String.valueOf(j.getEdad()));
                    cbDivision.setSelectedItem(j.getDivision());
                    lblRutaFoto.setText(j.getRutaImagenJugador());
                    lblRutaEscudo.setText(j.getRutaEscudoEquipo());
                    cargarPreview(j.getRutaImagenJugador(), lblPreviewFoto, 180, 220);
                    cargarPreview(j.getRutaEscudoEquipo(), lblPreviewEscudo, 130, 130);
                    JOptionPane.showMessageDialog(this, "Registro encontrado.", "Consulta", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún registro.", "Sin Resultados", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El número debe ser entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnModificar.addActionListener(e -> {
            if (!validar()) return;
            try {
                String eq = txtEquipo.getText().trim();
                int num = Integer.parseInt(txtNumero.getText().trim());
                Jugador act = new Jugador(eq, txtNombre.getText().trim(), txtPosicion.getText().trim(), num,
                        txtNacionalidad.getText().trim(), Integer.parseInt(txtEdad.getText().trim()),
                        cbDivision.getSelectedItem().toString(), lblRutaFoto.getText(), lblRutaEscudo.getText());
                if (controlador.modificarJugador(eq, num, act)) {
                    JOptionPane.showMessageDialog(this, "Modificación realizada con éxito.", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el registro a modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            String eq = txtEquipo.getText().trim();
            if (eq.isEmpty() || txtNumero.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el equipo y número para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int num = Integer.parseInt(txtNumero.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este registro?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (controlador.eliminarJugador(eq, num)) {
                        JOptionPane.showMessageDialog(this, "Estampilla eliminada.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontró el registro.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Número de camiseta inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnLimpiar.addActionListener(e -> limpiar());
        btnCerrar.addActionListener(e -> {
            new VentanaLogin().setVisible(true);
            dispose();
        });
    }

    private void crearCampo(String t, int x, int y, JTextField f) {
        JLabel l = new JLabel(t);
        l.setBounds(x, y, 120, 25);
        add(l);
        f.setBounds(x + 130, y, 180, 25);
        add(f);
    }

    private JButton crearBoton(String t, int x, int y) {
        JButton b = new JButton(t);
        b.setBounds(x, y, 110, 35);
        add(b);
        return b;
    }

    private boolean validar() {
        if (txtEquipo.getText().trim().isEmpty() || txtNombre.getText().trim().isEmpty() ||
            txtPosicion.getText().trim().isEmpty() || txtNumero.getText().trim().isEmpty() ||
            txtNacionalidad.getText().trim().isEmpty() || txtEdad.getText().trim().isEmpty() ||
            lblRutaFoto.getText().equals("Sin Selección") || lblRutaEscudo.getText().equals("Sin Selección")) {
            JOptionPane.showMessageDialog(this, "Todos los campos e imágenes son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!Validador.esTextoValido(txtNombre.getText().trim()) || !Validador.esTextoValido(txtNacionalidad.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Nombre y Nacionalidad deben contener solo letras.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!Validador.esEnteroValido(txtNumero.getText().trim()) || !Validador.esEnteroValido(txtEdad.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Número y Edad deben ser números enteros.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void seleccionarImagen(JLabel lblR, JLabel lblP, int w, int h) {
        JFileChooser ch = new JFileChooser();
        if (ch.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = ch.getSelectedFile();
            lblR.setText(f.getAbsolutePath());
            cargarPreview(f.getAbsolutePath(), lblP, w, h);
        }
    }

    private void cargarPreview(String ruta, JLabel lbl, int w, int h) {
        File f = new File(ruta);
        if (f.exists()) {
            ImageIcon icon = new ImageIcon(ruta);
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            lbl.setIcon(new ImageIcon(img));
            lbl.setText("");
        }
    }

    private void limpiar() {
        txtEquipo.setText(""); txtNombre.setText(""); txtPosicion.setText("");
        txtNumero.setText(""); txtNacionalidad.setText(""); txtEdad.setText("");
        lblRutaFoto.setText("Sin Selección"); lblRutaEscudo.setText("Sin Selección");
        lblPreviewFoto.setIcon(null); lblPreviewFoto.setText("Foto");
        lblPreviewEscudo.setIcon(null); lblPreviewEscudo.setText("Escudo");
    }
}