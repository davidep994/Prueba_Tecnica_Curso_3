package org.example.crud_vuelos_sb.dtos;

import org.example.crud_vuelos_sb.models.Vuelo;
import org.springframework.stereotype.Component;

@Component
public class VueloMapper {

    public Vuelo toEntity(VueloRequestDTO dto) {
        return new Vuelo(
                0,
                dto.getNombreVuelo(),
                dto.getEmpresa(),
                dto.getLugarSalida(),
                dto.getLugarLlegada(),
                dto.getFechaSalida(),
                dto.getFechaLlegada()
        );
    }

    public VueloResponseDTO toResponseDTO(Vuelo entity) {
        VueloResponseDTO dto = new VueloResponseDTO();
        dto.setId(entity.getId());
        dto.setNombreVuelo(entity.getNombre());
        dto.setEmpresa(entity.getEmpresa());
        dto.setLugarSalida(entity.getLugarSalida());
        dto.setLugarLlegada(entity.getLugarLlegada());
        dto.setFechaSalida(entity.getFechaSalida());
        dto.setFechaLlegada(entity.getFechaLlegada());
        dto.setDuracionDias(entity.getDuracionDias());
        return dto;
    }
}
