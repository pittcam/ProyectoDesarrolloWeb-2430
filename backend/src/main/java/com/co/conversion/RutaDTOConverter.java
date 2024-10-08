package com.co.conversion;

import com.co.dto.RutaDTO;
import com.co.model.Ruta;
import com.co.model.Asignacion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RutaDTOConverter {

    public RutaDTO entityToDTO(Ruta ruta) {
        // Convertir Set a List
        List<Asignacion> asignaciones = new ArrayList<>(ruta.getAsignaciones());
        return new RutaDTO(ruta.getId(), ruta.getNombre(), asignaciones);
    }

    // Metodo para convertir una lista de Rutas a una lista de RutasDTOs
    public List<RutaDTO> entitiesToDTOs(List<Ruta> rutas) {
        return rutas.stream()
                .map(this::entityToDTO)  // Convierte cada ruta en RutaDTO
                .collect(Collectors.toList());  // Colecta los resultados en una lista
    }

    public Ruta DTOToEntity(RutaDTO rutaDTO) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDTO.getId());
        ruta.setNombre(rutaDTO.getNombre());
        return ruta;
    }


}
