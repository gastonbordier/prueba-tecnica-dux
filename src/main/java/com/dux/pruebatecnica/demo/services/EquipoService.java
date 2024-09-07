package com.dux.pruebatecnica.demo.services;

import com.dux.pruebatecnica.demo.dtos.EquipoCreateDTO;
import com.dux.pruebatecnica.demo.dtos.EquipoUpdateDTO;
import com.dux.pruebatecnica.demo.entities.Equipo;
import com.dux.pruebatecnica.demo.exceptions.NotFoundException;
import com.dux.pruebatecnica.demo.mappers.EquipoMapper;
import com.dux.pruebatecnica.demo.repositories.EquipoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquipoService {

    private final EquipoRepository repository;
    private final EquipoMapper mapper;

    public List<?> findAll() {
        return repository.findAll();
    }

    public Equipo findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
    }

    public List<Equipo> findByNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public Equipo create(EquipoCreateDTO dto) {
        return repository.save(mapper.createDtoToEntity(dto));
    }

    public Equipo update(Integer id, EquipoUpdateDTO dto) {
        return repository.save(mapper.updateDtoToEntity(findById(id), dto));
    }

    public void deleteById(Integer id) {
        repository.delete(findById(id));
    }
}
