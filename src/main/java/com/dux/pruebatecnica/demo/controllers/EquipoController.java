package com.dux.pruebatecnica.demo.controllers;

import com.dux.pruebatecnica.demo.dtos.EquipoCreateDTO;
import com.dux.pruebatecnica.demo.services.EquipoService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService service;


    @GetMapping("/equipos")
    public ResponseEntity<?> obtenerEquipos() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/equipos")
    public ResponseEntity<?> crearEquipo(EquipoCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/equipo/{id}")
    public ResponseEntity<?> obtenerEquipo(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/equipos/buscar")
    public ResponseEntity<?> obtenerEquipo(@PathParam("nombre") String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }


}
