package com.dux.pruebatecnica.demo.controllers;

import com.dux.pruebatecnica.demo.dtos.EquipoCreateDTO;
import com.dux.pruebatecnica.demo.dtos.EquipoUpdateDTO;
import com.dux.pruebatecnica.demo.services.EquipoService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class EquipoController {

    private final EquipoService service;


    @GetMapping("/equipos")
    public ResponseEntity<?> obtenerEquipos() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/equipos")
    public ResponseEntity<?> crearEquipo(@RequestBody EquipoCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/equipos/{id}")
    public ResponseEntity<?> actualizarEquipo(@PathVariable Integer id, @RequestBody @Valid EquipoUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<?> eliminarEquipo(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
