package com.dux.pruebatecnica.demo.mappers;

import com.dux.pruebatecnica.demo.dtos.EquipoCreateDTO;
import com.dux.pruebatecnica.demo.entities.Equipo;
import org.springframework.stereotype.Component;

@Component
public class EquipoMapper {

    public Equipo createDtoToEntity(EquipoCreateDTO dto) {
        return Equipo.builder()
                .nombre(dto.getNombre())
                .liga(dto.getPais())
                .pais(dto.getPais())
                .build();
    }

}
