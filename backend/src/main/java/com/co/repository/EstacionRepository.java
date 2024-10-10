package com.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.co.model.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
