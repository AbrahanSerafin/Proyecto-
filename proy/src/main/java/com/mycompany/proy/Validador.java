package com.mycompany.proy;
import java.util.regex.Pattern;

public class Validador {
    public static boolean esTextoValido(String texto) {
        return Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", texto);
    }

    public static boolean esEnteroValido(String texto) {
        return Pattern.matches("^\\d+$", texto);
    }
}