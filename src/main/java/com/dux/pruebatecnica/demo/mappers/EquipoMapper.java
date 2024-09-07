package com.dux.pruebatecnica.demo.mappers;

import com.dux.pruebatecnica.demo.dtos.EquipoCreateDTO;
import com.dux.pruebatecnica.demo.dtos.EquipoUpdateDTO;
import com.dux.pruebatecnica.demo.entities.Equipo;
import org.springframework.stereotype.Component;

@Component
public class EquipoMapper {

    public Equipo createDtoToEntity(EquipoCreateDTO dto) {
        return Equipo.builder()
                .nombre(dto.getNombre())
                .liga(dto.getLiga())
                .pais(dto.getPais())
                .build();
    }

    public Equipo updateDtoToEntity(Equipo equipo, EquipoUpdateDTO dto) {
        if (dto.getNombre() != null) equipo.setNombre(dto.getNombre());
        if (dto.getLiga() != null) equipo.setLiga(dto.getLiga());
        if (dto.getPais() != null) equipo.setPais(dto.getPais());
        return equipo;
    }
}
