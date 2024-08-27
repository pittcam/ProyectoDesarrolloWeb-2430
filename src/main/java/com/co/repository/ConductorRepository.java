package com.co.repository;

import com.co.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    List<Conductor> findAllByNombreContainingIgnoreCase(String textoBusqueda);
    List<Conductor> findAllById(Iterable<Long> ids);
}
