package com.co.conversion;

import org.springframework.stereotype.Component;

import com.co.dto.BusDTO;
import com.co.model.Bus;

@Component
public class BusDTOConverter {

    // Metodo para convertir de Bus a BusDTO
    public BusDTO entityToDTO(Bus bus) {
        return new BusDTO(bus.getNumeroPlaca(), bus.getModelo());
    }

    // Metodo para convertir de BusDTO a Bus
    public Bus DTOToEntity(BusDTO busDTO) {
        Bus bus = new Bus();
        bus.setNumeroPlaca(busDTO.getNumeroPlaca());
        bus.setModelo(busDTO.getModelo());
        return bus;
    }
}
