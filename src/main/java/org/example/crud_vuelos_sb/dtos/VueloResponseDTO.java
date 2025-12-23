package org.example.crud_vuelos_sb.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VueloResponseDTO {
    private int id;
    private String nombreVuelo;
    private String empresa;
    private String lugarSalida;
    private String lugarLlegada;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaSalida;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaLlegada;
    private long duracionDias;
}
