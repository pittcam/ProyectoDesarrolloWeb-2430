package com.co.repository;

import com.co.model.Bus;
import com.co.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    // Consulta personalizada utilizando JPQL para buscar conductores por nombre
    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE concat(:texto, '%')")
    List<Conductor> findAllByNombreStartingWith2(String texto);

    // Consulta personalizada utilizando JPQL para buscar conductores por nombre, ignorando mayúsculas/minúsculas
    @Query("SELECT c FROM Conductor c WHERE LOWER(c.nombre) LIKE LOWER(concat(:texto, '%'))")
    List<Conductor> findAllByNombreStartingWithIgnoreCase2(String texto);

    // Consulta para encontrar los buses que no están asignados
    @Query("SELECT b FROM Bus b WHERE b.asignaciones IS EMPTY")
    List<Bus> findBusesDisponibles();
}
