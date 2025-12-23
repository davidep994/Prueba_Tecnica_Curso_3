package org.example.crud_vuelos_sb.repositories;

import org.example.crud_vuelos_sb.models.Vuelo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class VuelosRepository {

    // Inicializamos con 10 porque ya tienes 10 vuelos pre-cargados
    private final AtomicInteger idGenerator = new AtomicInteger(10);

    private final List<Vuelo> listaVuelos = new ArrayList<>(List.of(
            new Vuelo(1, "IB101", "Iberia", "Madrid", "Buenos Aires", LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 11)),
            new Vuelo(2, "TK202", "Turkish", "Estambul", "New York", LocalDate.of(2026, 1, 12), LocalDate.of(2026, 1, 13)),
            new Vuelo(3, "EM303", "Emirates", "Dubai", "Madrid", LocalDate.of(2026, 2, 15), LocalDate.of(2026, 2, 15)),
            new Vuelo(4, "AD404", "AeroDreams", "Buenos Aires", "Madrid", LocalDate.of(2026, 2, 20), LocalDate.of(2026, 2, 21)),
            new Vuelo(5, "AF505", "Air France", "Paris", "Tokyo", LocalDate.of(2026, 3, 5), LocalDate.of(2026, 3, 7)),
            new Vuelo(6, "LH606", "Lufthansa", "Berlin", "New York", LocalDate.of(2026, 3, 15), LocalDate.of(2026, 3, 16)),
            new Vuelo(7, "IB102", "Iberia", "Madrid", "Barcelona", LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 10)),
            new Vuelo(8, "TK203", "Turkish", "Ankara", "Estambul", LocalDate.of(2026, 5, 20), LocalDate.of(2026, 5, 20)),
            new Vuelo(9, "EM304", "Emirates", "Dubai", "Paris", LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 2)),
            new Vuelo(10, "BA707", "British", "Londres", "Madrid", LocalDate.of(2026, 7, 12), LocalDate.of(2026, 7, 13))
    ));

    public List<Vuelo> findAll(){
        return listaVuelos;
    }

    public void save(Vuelo vuelo){
        vuelo.setId(idGenerator.incrementAndGet());
        listaVuelos.add(vuelo);
    }

    public void delete(int id){
        listaVuelos.removeIf(v -> v.getId() == id);
    }
}
