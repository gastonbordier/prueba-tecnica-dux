package com.dux.pruebatecnica.demo.controllers;

import com.dux.pruebatecnica.demo.dtos.EquipoCreateDTO;
import com.dux.pruebatecnica.demo.dtos.EquipoUpdateDTO;
import com.dux.pruebatecnica.demo.entities.Usuario;
import com.dux.pruebatecnica.demo.exceptions.EquipoNotFoundException;
import com.dux.pruebatecnica.demo.exceptions.ValidationFailedException;
import com.dux.pruebatecnica.demo.services.EquipoService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> crearEquipo() {
        return ResponseEntity.ok().build();
    }
}
