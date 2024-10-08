package com.dux.pruebatecnica.demo.mappers;

import com.dux.pruebatecnica.demo.dtos.EquipoDTO;
import com.dux.pruebatecnica.demo.entities.Equipo;
import org.springframework.stereotype.Component;

@Component
public class EquipoMapper {

    public Equipo dtoToEntity(EquipoDTO dto) {
        return Equipo.builder()
                .nombre(dto.getNombre())
                .liga(dto.getLiga())
                .pais(dto.getPais())
                .build();
    }

    public Equipo dtoToEntity(Integer id, EquipoDTO dto) {
        return Equipo.builder()
                .id(id)
                .nombre(dto.getNombre())
                .liga(dto.getLiga())
                .pais(dto.getPais())
                .build();
    }



}
