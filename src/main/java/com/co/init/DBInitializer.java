package com.co.init;

import com.co.model.Bus;
import com.co.model.Horario;
import com.co.model.Ruta;
import com.co.repository.BusRepository;
import com.co.repository.HorarioRepository;
import com.co.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar Horarios
        Horario horario1 = new Horario("08:00", "12:00", Arrays.asList("Lunes", "Martes"));
        Horario horario2 = new Horario("13:00", "17:00", Arrays.asList("Miércoles", "Jueves"));

        horarioRepository.saveAll(Arrays.asList(horario1, horario2));

        // Inicializar Rutas
        Ruta ruta1 = new Ruta(horario1, Arrays.asList("Estación 1", "Estación 2"));
        Ruta ruta2 = new Ruta(horario2, Arrays.asList("Estación 3", "Estación 4"));

        rutaRepository.saveAll(Arrays.asList(ruta1, ruta2));

        // Inicializar Buses
        Bus bus1 = new Bus();
        bus1.setNumeroPlaca("ABC123");
        bus1.setModelo("Modelo Bus 1");
        bus1.setRuta(ruta1);
        bus1.setHorarios(Arrays.asList(horario1));

        Bus bus2 = new Bus();
        bus2.setNumeroPlaca("XYZ789");
        bus2.setModelo("Modelo Bus 2");
        bus2.setRuta(ruta2);
        bus2.setHorarios(Arrays.asList(horario2));

        busRepository.saveAll(Arrays.asList(bus1, bus2));
    }
}
