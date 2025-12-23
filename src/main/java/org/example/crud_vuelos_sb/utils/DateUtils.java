package org.example.crud_vuelos_sb.utils;

import java.time.LocalDate;

public class DateUtils {
    // Válida que la fecha de salida sea anterior o igual a la de llegada
    public static boolean esPeriodoValido(LocalDate salida, LocalDate llegada){
        if (salida == null || llegada == null) return false;
        return !salida.isAfter(llegada);
    }
}
