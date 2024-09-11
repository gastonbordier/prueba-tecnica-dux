package com.dux.pruebatecnica.demo.repositories;

import com.dux.pruebatecnica.demo.entities.Equipo;
import com.dux.pruebatecnica.demo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
