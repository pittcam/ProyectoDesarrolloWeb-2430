package com.co.repository;

import com.co.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// @Repository // No es necesario anotar la interfaz con @Repository, ya que JpaRepository lo hace automáticamente
public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    // Métodos derivados basados en nombres de métodos
    List<Conductor> findAllByNombre(String nombre);

    List<Conductor> findAllByNombreStartingWith(String texto);

    List<Conductor> findAllByNombreStartingWithIgnoreCase(String texto);

    // Consultas personalizadas usando JPQL
    @Query("SELECT c FROM Conductor c WHERE c.nombre LIKE concat(:texto, '%')")
    List<Conductor> findConductoresByNombreStartingWith(@Param("texto") String texto);

    @Query("SELECT c FROM Conductor c WHERE LOWER(c.nombre) LIKE LOWER(concat(:texto,'%'))")
    List<Conductor> findConductoresByNombreStartingWithCaseInsensitive(@Param("texto") String texto);
}
