package com.co.init;

import com.co.model.Asignacion;
import com.co.model.Bus;
import com.co.model.Conductor;
import com.co.model.Horario;
import com.co.model.Ruta;
import com.co.repository.AsignacionRepository;
import com.co.repository.BusRepository;
import com.co.repository.ConductorRepository;
import com.co.repository.HorarioRepository;
import com.co.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar Horarios
        Horario horario1 = new Horario();
        horario1.setHoraInicio("08:00");
        horario1.setHoraFin("12:00");
        horario1.setDias("Lunes a Viernes"); // Agregar valor para 'dias'
        horarioRepository.save(horario1);

        Horario horario2 = new Horario();
        horario2.setHoraInicio("13:00");
        horario2.setHoraFin("17:00");
        horario2.setDias("Lunes a Sábado"); // Agregar valor para 'dias'
        horarioRepository.save(horario2);

        // Inicializar Rutas
        Ruta ruta1 = new Ruta();
        ruta1.setNombre("Ruta 1");
        rutaRepository.save(ruta1);

        Ruta ruta2 = new Ruta();
        ruta2.setNombre("Ruta 2");
        rutaRepository.save(ruta2);

        // Inicializar Buses
        Bus bus1 = new Bus();
        bus1.setNumeroPlaca("ABC123");
        bus1.setModelo("Modelo Bus 1");
        busRepository.save(bus1);

        Bus bus2 = new Bus();
        bus2.setNumeroPlaca("XYZ789");
        bus2.setModelo("Modelo Bus 2");
        busRepository.save(bus2);

        // Inicializar Conductores
        Conductor conductor1 = new Conductor();
        conductor1.setNombre("Juan Pérez");
        conductor1.setCedula("12345678");
        conductor1.setTelefono("3001234567");
        conductor1.setDireccion("Calle 1 # 2-3");
        conductorRepository.save(conductor1);

        Conductor conductor2 = new Conductor();
        conductor2.setNombre("María Rodríguez");
        conductor2.setCedula("87654321");
        conductor2.setTelefono("3017654321");
        conductor2.setDireccion("Carrera 4 # 5-6");
        conductorRepository.save(conductor2);

        // Eliminar asignaciones predeterminadas
        // Asignacion asignacion1 = new Asignacion();
        // asignacion1.setBus(bus1);
        // asignacion1.setRuta(ruta1);
        // asignacion1.setHorario(horario1);
        // asignacionRepository.save(asignacion1);

        // Asignacion asignacion2 = new Asignacion();
        // asignacion2.setBus(bus2);
        // asignacion2.setRuta(ruta2);
        // asignacion2.setHorario(horario2);
        // asignacionRepository.save(asignacion2);
    }

}
