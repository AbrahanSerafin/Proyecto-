package com.mycompany.proy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos {
    private static final String ARCHIVO = "jugadores.txt";

    public static void guardarJugador(Jugador j) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(j.toLineaArchivo());
        } catch (IOException e) {
            System.err.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public static List<Jugador> cargarJugadores() {
        List<Jugador> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 9) {
                    Jugador j = new Jugador(
                        partes[0], partes[1], partes[2],
                        Integer.parseInt(partes[3]), partes[4],
                        Integer.parseInt(partes[5]), partes[6],
                        partes[7], partes[8]
                    );
                    lista.add(j);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    public static void sobrescribirArchivo(List<Jugador> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, false))) {
            for (Jugador j : lista) {
                pw.println(j.toLineaArchivo());
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar el archivo: " + e.getMessage());
        }
    }
}