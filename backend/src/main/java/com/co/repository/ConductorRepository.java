package com.co.repository;

import com.co.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    List<Conductor> findAllByNombreContainingIgnoreCase(String textoBusqueda);
    List<Conductor> findAllById(Iterable<Long> ids);

    // Consulta personalizada utilizando JPQL para buscar conductores por nombre
    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE concat(:texto, '%')")
    List<Conductor> findAllByNombreStartingWith2(String texto);


    // Consulta personalizada utilizando JPQL para buscar conductores por nombre, ignorando mayúsculas/minúsculas
    @Query("SELECT c FROM Conductor c WHERE LOWER(c.nombre) LIKE LOWER(concat(:texto, '%'))")
    List<Conductor> findAllByNombreStartingWithIgnoreCase2(String texto);
}


