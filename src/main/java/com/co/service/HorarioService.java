package com.co.service;

import com.co.model.Horario;
import com.co.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    // Método para obtener todos los horarios
    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    // Método para obtener horarios por una lista de IDs
    public List<Horario> findByIds(List<Long> ids) {
        return horarioRepository.findByIdIn(ids);
    }

    // Método para obtener un horario por su ID
    public Optional<Horario> findById(Long id) {
        return horarioRepository.findById(id);
    }

    // Método para guardar un horario
    public Horario save(Horario horario) {
        return horarioRepository.save(horario);
    }

    // Método para eliminar un horario por su ID
    public void delete(Long id) {
        horarioRepository.deleteById(id);
    }
}
