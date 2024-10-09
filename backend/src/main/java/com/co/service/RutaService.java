package com.co.service;

import com.co.conversion.RutaDTOConverter;
import com.co.dto.ConductorDTO;
import com.co.dto.RutaDTO;
import com.co.model.Conductor;
import com.co.model.Ruta;
import com.co.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private RutaDTOConverter rutaDTOConverter;

    public List<Ruta> obtenerTodos() {
        return rutaRepository.findAll();
    }

    //Conseguir ruta por id
    public RutaDTO getRuta(Long id) {
        return rutaDTOConverter.entityToDTO(rutaRepository.findById(id).orElseThrow());
    }

    //Buscar
    public List<RutaDTO> buscarRutasPorNombre(String nombre) {
        // Fetch list of Ruta entities matching the name
        List<Ruta> rutas = rutaRepository.findAllByNombreContainingIgnoreCase(nombre);

        // Convert the list of Ruta entities to a list of RutaDTOs
        return rutas.stream()
                .map(rutaDTOConverter::entityToDTO)
                .collect(Collectors.toList());
    }


    //Crear ruta
    public RutaDTO createRuta(RutaDTO rutaDTO) {
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        return rutaDTOConverter.entityToDTO(rutaRepository.save(ruta));
    }

    //Borrar ruta
    public void deleteRuta(Long id) {
        rutaRepository.deleteById(id);
    }

    // Crear o actualizar un conductor
    public RutaDTO saveRuta(RutaDTO rutaDTO) {
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        return rutaDTOConverter.entityToDTO(rutaRepository.save(ruta));
    }


}
