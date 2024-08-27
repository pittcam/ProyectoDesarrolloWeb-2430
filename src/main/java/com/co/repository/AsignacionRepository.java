package com.co.repository;

import com.co.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    // Métodos adicionales de consulta personalizada pueden ser añadidos aquí si es necesario
}
