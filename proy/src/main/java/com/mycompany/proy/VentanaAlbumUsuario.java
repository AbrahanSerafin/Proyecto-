package com.mycompany.proy;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VentanaAlbumUsuario extends JFrame {
    private ControladorAlbum controlador;

    public VentanaAlbumUsuario() {
        controlador = new ControladorAlbum();
        setTitle("Álbum Virtual - Usuario Normal");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblT = new JLabel("ÁLBUM DE ESTAMPILLAS - VISTA DE USUARIO", SwingConstants.CENTER);
        lblT.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblT, BorderLayout.NORTH);

        JPanel panelCuadricula = new JPanel(new GridLayout(0, 4, 10, 10));
        panelCuadricula.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Jugador j : controlador.getListaJugadores()) {
            panelCuadricula.add(crearTarjeta(j));
        }

        add(new JScrollPane(panelCuadricula), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnPDF = new JButton("Imprimir / Descargar Álbum PDF");
        JButton btnSalir = new JButton("Cerrar Sesión");
        panelBotones.add(btnPDF);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.SOUTH);

        btnPDF.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("AlbumEstampillas.pdf"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                boolean ok = GeneradorPDFAlbum.exportarAlbumPDF(controlador.getListaJugadores(), fc.getSelectedFile().getAbsolutePath());
                if (ok) {
                    JOptionPane.showMessageDialog(this, "PDF generado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al generar el PDF.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnSalir.addActionListener(e -> {
            new VentanaLogin().setVisible(true);
            dispose();
        });
    }

    private JPanel crearTarjeta(Jugador j) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel lEq = new JLabel(j.getNombreEquipo().toUpperCase());
        lEq.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(lEq);

        JLabel lImg = new JLabel();
        lImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        File f = new File(j.getRutaImagenJugador());
        if (f.exists()) {
            ImageIcon icon = new ImageIcon(j.getRutaImagenJugador());
            lImg.setIcon(new ImageIcon(icon.getImage().getScaledInstance(90, 110, Image.SCALE_SMOOTH)));
        } else {
            lImg.setText("[Sin Foto]");
        }
        card.add(lImg);

        card.add(crearLabelCentro(j.getNombrePersona()));
        card.add(crearLabelCentro(j.getPosicion() + " (#" + j.getNumero() + ")"));
        card.add(crearLabelCentro("Div: " + j.getDivision()));
        return card;
    }

    private JLabel crearLabelCentro(String texto) {
        JLabel l = new JLabel(texto);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }
}