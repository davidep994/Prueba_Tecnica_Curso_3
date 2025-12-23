package org.example.crud_vuelos_sb.services;

import org.example.crud_vuelos_sb.exceptions.ResourceNotFoundException;
import org.example.crud_vuelos_sb.models.Vuelo;
import org.example.crud_vuelos_sb.repositories.VuelosRepository;
import org.example.crud_vuelos_sb.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VueloService {

    @Autowired
    private VuelosRepository repository;

    public List<Vuelo> listarVuelos(String empresa, String destino, LocalDate fecha, String ordenarPor, String ordenar) {
        Stream<Vuelo> stream = repository.findAll().stream()
                .filter(v -> empresa == null || v.getEmpresa().equalsIgnoreCase(empresa))
                .filter(v -> destino == null || v.getLugarLlegada().equalsIgnoreCase(destino))
                .filter(v -> fecha == null || v.getFechaSalida().equals(fecha));

        // 1. Definimos el comparador por defecto (Fecha de Salida)
        Comparator<Vuelo> comparator = Comparator.comparing(Vuelo::getFechaSalida);

        // 2. Si el usuario pide otro campo, cambiamos el comparador
        if ("lugarLlegada".equalsIgnoreCase(ordenarPor)) {
            comparator = Comparator.comparing(Vuelo::getLugarLlegada);
        } else if ("empresa".equalsIgnoreCase(ordenarPor)) {
            comparator = Comparator.comparing(Vuelo::getEmpresa);
        } else if ("fechaSalida".equalsIgnoreCase(ordenarPor)) {
            comparator = Comparator.comparing(Vuelo::getFechaSalida);
        }

        // 3. Aplicamos ASC o DESC
        if ("DESC".equalsIgnoreCase(ordenar)) {
            comparator = comparator.reversed();
        }

        return stream.sorted(comparator).collect(Collectors.toList());
    }

    public Vuelo obtenerPorId(int id) {
        return repository.findAll().stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("El vuelo con ID " + id + " no se ha encontrado."));
    }

    public void crear(Vuelo vuelo) {
        // 1. Comprobamos si ya existe un vuelo con el mismo nombre y fecha de salida
        boolean existeDuplicado = repository.findAll().stream()
                .anyMatch(v -> v.getNombre().equalsIgnoreCase(vuelo.getNombre())
                        && v.getFechaSalida().equals(vuelo.getFechaSalida()));

        if (existeDuplicado) {
            throw new RuntimeException("Ya existe un vuelo programado con el nombre "
                    + vuelo.getNombre() + " para la fecha " + vuelo.getFechaSalida());
        }

        // 2. Validación de fechas (coherencia salida/llegada)
        if (!DateUtils.esPeriodoValido(vuelo.getFechaSalida(), vuelo.getFechaLlegada())) {
            throw new RuntimeException("La fecha de salida no puede ser posterior a la fecha de llegada.");
        }

        // 3. Validación de campos obligatorios
        if (vuelo.getNombre() == null || vuelo.getNombre().isBlank()) {
            throw new RuntimeException("El nombre del vuelo no puede estar vacío.");
        }

        // El ID se asigna automáticamente en el repositorio gracias al AtomicInteger
        repository.save(vuelo);
    }

    public void actualizar(int id, Vuelo vueloActualizado) {
        // 1. Buscamos si el vuelo existe
        Vuelo existente = obtenerPorId(id);

        // 2. Validamos que las nuevas fechas sean coherentes
        if (!DateUtils.esPeriodoValido(vueloActualizado.getFechaSalida(), vueloActualizado.getFechaLlegada())) {
            throw new RuntimeException("La fecha de salida no puede ser posterior a la llegada en la actualización.");
        }

        // 3. Actualizamos los campos
        existente.setNombre(vueloActualizado.getNombre());
        existente.setEmpresa(vueloActualizado.getEmpresa());
        existente.setLugarSalida(vueloActualizado.getLugarSalida());
        existente.setLugarLlegada(vueloActualizado.getLugarLlegada());
        existente.setFechaSalida(vueloActualizado.getFechaSalida());
        existente.setFechaLlegada(vueloActualizado.getFechaLlegada());

        // No es necesario llamar a un "save", ya que 'existente' es una referencia al objeto en la lista
    }

    public void eliminar(int id){
        repository.delete(id);
    }

}
