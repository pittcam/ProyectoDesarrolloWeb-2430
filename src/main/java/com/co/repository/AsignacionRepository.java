package com.co.repository;

import com.co.model.Asignacion;
import com.co.model.Conductor;
import com.co.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    List<Asignacion> findByConductor(Conductor conductor);
    List<Asignacion> findByBus(Bus bus);
}
