package com.co.service;

import com.co.repository.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SistemaService {

    @Autowired
    private SistemaRepository sistemaRepository;

    public List<Sistema> obtenerTodos() {
        return sistemaRepository.findAll();
    }

    public Optional<Sistema> obtenerPorId(Long id) {
        return sistemaRepository.findById(id);
    }

    public void guardar(Sistema sistema) {
        sistemaRepository.save(sistema);
    }

    public void eliminar(Long id) {
        sistemaRepository.deleteById(id);
    }
}
