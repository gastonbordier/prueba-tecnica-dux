package com.dux.pruebatecnica.demo.repositories;

import com.dux.pruebatecnica.demo.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
}
