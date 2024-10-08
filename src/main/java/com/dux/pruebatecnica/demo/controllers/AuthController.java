package com.dux.pruebatecnica.demo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @GetMapping
    public ResponseEntity<?> Equipo() {
        return ResponseEntity.ok("base");
    }

    @GetMapping("/hola")
    public ResponseEntity<?> Equipo2() {
        return ResponseEntity.ok("hola");
    }

    @PostMapping("/login")
    public ResponseEntity<?> crearEquipo() {
        return ResponseEntity.ok().build();
    }
}
