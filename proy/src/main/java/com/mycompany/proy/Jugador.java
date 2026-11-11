package com.mycompany.proy;
public class Jugador {
    private String nombreEquipo;
    private String nombrePersona;
    private String posicion;
    private int numero;
    private String nacionalidad;
    private int edad;
    private String division; // "Varonil" o "Femenil"
    private String rutaImagenJugador;
    private String rutaEscudoEquipo;

    public Jugador(String nombreEquipo, String nombrePersona, String posicion, int numero, 
                   String nacionalidad, int edad, String division, String rutaImagenJugador, String rutaEscudoEquipo) {
        this.nombreEquipo = nombreEquipo;
        this.nombrePersona = nombrePersona;
        this.posicion = posicion;
        this.numero = numero;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.division = division;
        this.rutaImagenJugador = rutaImagenJugador;
        this.rutaEscudoEquipo = rutaEscudoEquipo;
    }

    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }

    public String getNombrePersona() { return nombrePersona; }
    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }

    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) { this.posicion = posicion; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getRutaImagenJugador() { return rutaImagenJugador; }
    public void setRutaImagenJugador(String rutaImagenJugador) { this.rutaImagenJugador = rutaImagenJugador; }

    public String getRutaEscudoEquipo() { return rutaEscudoEquipo; }
    public void setRutaEscudoEquipo(String rutaEscudoEquipo) { this.rutaEscudoEquipo = rutaEscudoEquipo; }

    public String toLineaArchivo() {
        return nombreEquipo + "," + nombrePersona + "," + posicion + "," + numero + "," +
               nacionalidad + "," + edad + "," + division + "," + rutaImagenJugador + "," + rutaEscudoEquipo;
    }
}