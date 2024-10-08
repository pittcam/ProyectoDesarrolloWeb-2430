package com.co.repository;

import com.co.model.Conductor;
import com.co.model.Estacion;
import com.co.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    List<Ruta> findAllByNombreContainingIgnoreCase(String textoBusqueda);
    List<Ruta> findAllByIdRuta(Iterable<Long> ids);

    // Consulta personalizada utilizando JPQL para buscar conductores por nombre
    @Query("SELECT r FROM Ruta r WHERE r.nombre LIKE concat(:texto, '%')")
    List<Conductor> findAllByNombreStartingWith2(String texto);


    // Consulta personalizada utilizando JPQL para buscar conductores por nombre, ignorando mayúsculas/minúsculas
    @Query("SELECT r FROM Ruta r WHERE LOWER(r.nombre) LIKE LOWER(concat(:texto, '%'))")
    List<Conductor> findAllByNombreStartingWithIgnoreCase2(String texto);

    //Metodo para asociar estaciones con ruta
    @Query("SELECT e.id FROM Estacion e INNER JOIN Ruta r ON e.id_ruta = r.id WHERE r.id = :id_ruta")
    List<Estacion> findAllByRutaIdRuta(Long id_ruta);



}
