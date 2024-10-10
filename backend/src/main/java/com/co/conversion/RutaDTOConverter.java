package com.co.conversion;

import org.springframework.stereotype.Component;
import com.co.dto.RutaDTO;
import com.co.model.Ruta;
import com.co.model.Estacion;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RutaDTOConverter {

    public RutaDTO entityToDTO(Ruta ruta) {
        Set<Long> estacionesIds = ruta.getEstaciones().stream()
                .map(Estacion::getId)
                .collect(Collectors.toSet());
        return new RutaDTO(
                ruta.getId(),
                ruta.getNombre(),
                estacionesIds,
                ruta.getHorarioFuncionamiento()
        );
    }

    public Ruta dtoToEntity(RutaDTO rutaDTO, Set<Estacion> estaciones) {
        Ruta ruta = new Ruta();
        ruta.setId(rutaDTO.getId());
        ruta.setNombre(rutaDTO.getNombre());
        ruta.setHorarioFuncionamiento(rutaDTO.getHorarioFuncionamiento());
        ruta.setEstaciones(estaciones);
        return ruta;
    }
}
