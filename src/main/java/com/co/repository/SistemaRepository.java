package com.co.repository;

import com.co.model.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
    // Métodos adicionales de consulta personalizada pueden ser añadidos aquí si es necesario
}
