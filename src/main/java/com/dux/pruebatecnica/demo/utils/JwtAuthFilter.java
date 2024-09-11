package com.dux.pruebatecnica.demo.utils;

import com.dux.pruebatecnica.demo.dtos.AuthDTO;
import com.dux.pruebatecnica.demo.dtos.ErrorResponseDTO;
import com.dux.pruebatecnica.demo.exceptions.JwtException;
import com.dux.pruebatecnica.demo.exceptions.LoginException;
import com.dux.pruebatecnica.demo.services.JwtService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {


        String path = request.getServletPath();

        switch (path) {
            case "/auth/login":
                try {
                    loguearUsuario(request.getReader());
                    resolverLoginExitoso(response);

                } catch (LoginException | JwtException | UsernameNotFoundException | JsonParseException |
                         MismatchedInputException e) {
                    resolverLoginFallido(response);
                }
                break;
            default:
                log.error("default");
        }


    }

    private void loguearUsuario(BufferedReader reader) throws IOException {

        AuthDTO authDTO = objectMapper.readValue(reader, AuthDTO.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authDTO.getUsername());

        if (userDetails.getPassword().equals(authDTO.getPassword())) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            log.info("Autenticacion exitosa para {}", userDetails.getUsername());
        } else {
            throw new LoginException();
        }
    }

    private void resolverLoginExitoso(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(Collections.singletonMap("token", null)));

    }

    private void resolverLoginFallido(HttpServletResponse response) throws IOException {
        log.info("Autenticacion fallida");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponseDTO.builder()
                .mensaje("La autenticacion falló")
                .codigo(HttpStatus.UNAUTHORIZED.value())
                .build()));

    }

}
