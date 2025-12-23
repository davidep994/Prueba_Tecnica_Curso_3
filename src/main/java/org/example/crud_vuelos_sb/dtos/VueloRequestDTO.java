package org.example.crud_vuelos_sb.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VueloRequestDTO {
    @NotBlank(message = "El nombre del vuelo no puede estar vacío")
    private String nombreVuelo;

    @NotBlank(message = "La empresa es obligatoria")
    private String empresa;

    @NotBlank(message = "El lugar de salida es obligatorio")
    private String lugarSalida;

    @NotBlank(message = "El lugar de llegada es obligatorio")
    private String lugarLlegada;

    @NotNull(message = "La fecha de salida es obligatoria")
    @FutureOrPresent(message = "La fecha de salida no puede ser una fecha anterior a hoy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaSalida;

    @NotNull(message = "La fecha de llegada es obligatoria")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaLlegada;
}
