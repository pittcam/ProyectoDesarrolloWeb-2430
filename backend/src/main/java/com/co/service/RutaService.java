package com.co.service;

import com.co.conversion.RutaDTOConverter;
import com.co.dto.RutaDTO;
import com.co.model.Ruta;
import com.co.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /*public List<RutaDTO> buscarRutasPorNombre(String nombre) {
        List<Ruta> rutas = rutaRepository.findAllByNombreContainingIgnoreCase(nombre);
        return rutaDTOConverter.entityToDTO(rutaRepository.save(ruta));
    }*/

    //Crear ruta
    public RutaDTO createRuta(RutaDTO rutaDTO) {
        Ruta ruta = rutaDTOConverter.DTOToEntity(rutaDTO);
        return rutaDTOConverter.entityToDTO(rutaRepository.save(ruta));
    }

    public Ruta guardar(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    public void eliminar(Long id) {
        rutaRepository.deleteById(id);
    }
}
