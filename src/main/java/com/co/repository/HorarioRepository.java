package com.co.repository;

import com.co.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Método para encontrar horarios por una lista de IDs
    List<Horario> findByIdIn(List<Long> ids);
}
