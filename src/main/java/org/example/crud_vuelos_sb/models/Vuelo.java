package org.example.crud_vuelos_sb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vuelo {
    private int id;
    private String nombre;
    private String empresa;
    private String lugarSalida;
    private String lugarLlegada;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;

    public long getDuracionDias(){
        return ChronoUnit.DAYS.between(fechaSalida, fechaLlegada);
    }
}
