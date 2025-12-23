package org.example.crud_vuelos_sb.controllers;

import jakarta.validation.Valid;
import org.example.crud_vuelos_sb.dtos.VueloMapper;
import org.example.crud_vuelos_sb.dtos.VueloRequestDTO;
import org.example.crud_vuelos_sb.dtos.VueloResponseDTO;
import org.example.crud_vuelos_sb.models.Vuelo;
import org.example.crud_vuelos_sb.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vuelos")
public class VuelosController {

    @Autowired
    private VueloService service;

    @Autowired
    private VueloMapper mapper;

    /**
     * GET /api/vuelos
     * Obtiene todos los vuelos con filtros opcionales y ordenamiento.
     * Retorna una lista de VueloResponseDTO.
     */
    @GetMapping
    public ResponseEntity<List<VueloResponseDTO>> getAll(
            @RequestParam(required = false) String empresa,
            @RequestParam(required = false) String lugarLlegada,
            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaSalida,
            @RequestParam(required = false) String ordenarPor,
            @RequestParam(required = false, defaultValue = "ASC") String ordenar) {

        List<VueloResponseDTO> dtos = service.listarVuelos(empresa, lugarLlegada, fechaSalida, ordenarPor, ordenar)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    /**
     * GET /api/vuelos/{id}
     * Busca un vuelo por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VueloResponseDTO> getById(@PathVariable int id) {
        // Si el service no lo encuentra, saltará automáticamente al GlobalExceptionHandler
        Vuelo vuelo = service.obtenerPorId(id);
        return ResponseEntity.ok(mapper.toResponseDTO(vuelo));
    }

    /**
     * POST /api/vuelos
     * Crea un nuevo vuelo a partir de un VueloRequestDTO.
     */
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody VueloRequestDTO dto) {
        Vuelo nuevoVuelo = mapper.toEntity(dto);
        service.crear(nuevoVuelo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vuelo creado exitosamente");
    }

    /**
     * PUT /api/vuelos/{id}
     * Actualiza un vuelo existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @Valid @RequestBody VueloRequestDTO dto) {
        Vuelo vueloActualizado = mapper.toEntity(dto);
        service.actualizar(id, vueloActualizado);
        return ResponseEntity.ok("Vuelo con ID " + id + " actualizado correctamente.");
    }

    /**
     * DELETE /api/vuelos/{id}
     * Elimina un vuelo por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.ok("Vuelo eliminado satisfactoriamente.");
    }
}
