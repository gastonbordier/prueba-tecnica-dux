package com.dux.pruebatecnica.demo.controllers;

import com.dux.pruebatecnica.demo.dtos.EquipoDTO;
import com.dux.pruebatecnica.demo.dtos.EquipoUpdateDTO;
import com.dux.pruebatecnica.demo.entities.Equipo;
import com.dux.pruebatecnica.demo.services.EquipoService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipos")
@RequiredArgsConstructor
@Slf4j
public class EquipoController {

    private final EquipoService service;


    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerEquipos() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody @Valid EquipoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable @Parameter(description = "Id del equipo", example = "3") Integer id,
                                              @RequestBody @Valid EquipoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEquipo(@PathVariable @Parameter(description = "Id del equipo", example = "6") Integer id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipo(@PathVariable @Parameter(description = "Id del equipo", example = "9") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Equipo>> obtenerEquipoPorNombre(@PathParam("nombre") @Parameter(description = "Texto a buscar", example = "Ajax") String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }

}
