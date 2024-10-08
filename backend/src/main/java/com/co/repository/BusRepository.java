package com.co.repository;

import com.co.model.Bus;
import com.co.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    List<Bus> findAllByPlacaContainingIgnoreCase(String textoBusqueda);
    List<Bus> findAllByIdBus(Iterable<Long> ids);

    // Consulta personalizada utilizando JPQL para buscar conductores por nombre
    @Query("SELECT b FROM Bus b WHERE b.numeroPlaca LIKE concat(:texto, '%')")
    List<Bus> findAllByNombreStartingWith2(String texto);

    // Consulta personalizada utilizando JPQL para buscar conductores por nombre, ignorando mayúsculas/minúsculas
    @Query("SELECT b FROM Bus b WHERE LOWER(b.numeroPlaca) LIKE LOWER(concat(:texto, '%'))")
    List<Bus> findAllByNombreStartingWithIgnoreCase2(String texto);
}

