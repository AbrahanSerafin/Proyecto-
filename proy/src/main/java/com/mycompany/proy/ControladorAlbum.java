package com.mycompany.proy;
import java.util.List;

public class ControladorAlbum {
    private List<Jugador> listaJugadores;

    public ControladorAlbum() {
        this.listaJugadores = ManejoArchivos.cargarJugadores();
    }

    public boolean agregarJugador(Jugador nuevo) {
        for (Jugador j : listaJugadores) {
            if (j.getNombreEquipo().equalsIgnoreCase(nuevo.getNombreEquipo()) &&
                j.getNumero() == nuevo.getNumero() &&
                j.getDivision().equalsIgnoreCase(nuevo.getDivision())) {
                return false;
            }
        }
        listaJugadores.add(nuevo);
        ManejoArchivos.guardarJugador(nuevo);
        return true;
    }

    public Jugador consultarJugador(String equipo, int numero) {
        for (Jugador j : listaJugadores) {
            if (j.getNombreEquipo().equalsIgnoreCase(equipo) && j.getNumero() == numero) {
                return j;
            }
        }
        return null;
    }

    public boolean modificarJugador(String equipo, int numero, Jugador actualizado) {
        for (int i = 0; i < listaJugadores.size(); i++) {
            Jugador j = listaJugadores.get(i);
            if (j.getNombreEquipo().equalsIgnoreCase(equipo) && j.getNumero() == numero) {
                listaJugadores.set(i, actualizado);
                ManejoArchivos.sobrescribirArchivo(listaJugadores);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarJugador(String equipo, int numero) {
        for (int i = 0; i < listaJugadores.size(); i++) {
            Jugador j = listaJugadores.get(i);
            if (j.getNombreEquipo().equalsIgnoreCase(equipo) && j.getNumero() == numero) {
                listaJugadores.remove(i);
                ManejoArchivos.sobrescribirArchivo(listaJugadores);
                return true;
            }
        }
        return false;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }
}