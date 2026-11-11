package com.mycompany.proy;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.util.List;

public class GeneradorPDFAlbum {
    public static boolean exportarAlbumPDF(List<Jugador> jugadores, String rutaSalida) {
        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter.getInstance(document, new FileOutputStream(rutaSalida));
            document.open();

            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.RED);
            Paragraph titulo = new Paragraph("ÁLBUM OFICIAL DE ESTAMPILLAS", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);

            for (Jugador j : jugadores) {
                PdfPCell celda = new PdfPCell();
                celda.setPadding(6);
                celda.setMinimumHeight(180);
                
                celda.addElement(new Paragraph(j.getNombreEquipo().toUpperCase(), new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD)));
                try {
                    Image img = Image.getInstance(j.getRutaImagenJugador());
                    img.scaleToFit(70, 80);
                    img.setAlignment(Element.ALIGN_CENTER);
                    celda.addElement(img);
                } catch (Exception ex) {
                    celda.addElement(new Paragraph("[Sin Imagen]"));
                }
                
                Font fInfo = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL);
                celda.addElement(new Paragraph("Nombre: " + j.getNombrePersona(), fInfo));
                celda.addElement(new Paragraph("Pos: " + j.getPosicion() + " (#" + j.getNumero() + ")", fInfo));
                celda.addElement(new Paragraph("Edad: " + j.getEdad() + " | Nac: " + j.getNacionalidad(), fInfo));
                celda.addElement(new Paragraph("Div: " + j.getDivision(), fInfo));

                tabla.addCell(celda);
            }

            int resto = jugadores.size() % 4;
            if (resto > 0) {
                for (int i = 0; i < (4 - resto); i++) {
                    tabla.addCell(new PdfPCell(new Phrase(" ")));
                }
            }

            document.add(tabla);
            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}