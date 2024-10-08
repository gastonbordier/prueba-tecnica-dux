package com.dux.pruebatecnica.demo.utils;

import com.dux.pruebatecnica.demo.dtos.AuthDTO;
import com.dux.pruebatecnica.demo.dtos.ErrorResponseDTO;
import com.dux.pruebatecnica.demo.exceptions.JwtValidationException;
import com.dux.pruebatecnica.demo.exceptions.LoginException;
import com.dux.pruebatecnica.demo.services.JwtService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final JwtService jwtService;

    private String path;

    public boolean isThisPath(String pathRequired) {
        return Pattern.matches("^" + pathRequired + ".*", path);
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        path = request.getServletPath();

        if (isThisPath("/api/auth/login")) {
            try {
                loguearUsuario(request, response);
            } catch (LoginException | JwtValidationException | UsernameNotFoundException | JsonParseException |
                     MismatchedInputException e) {
                resolverLoginFallido(response);
            }
        } else if (isThisPath("/api")) {
                try {
                    validarToken(request, response);
                } catch (MalformedJwtException | IllegalArgumentException | NullPointerException e) {
                    resolverValidacionTokenFallida(response);
                }
        }
        filterChain.doFilter(request, response);


    }

    private void loguearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AuthDTO authDTO = objectMapper.readValue(request.getReader(), AuthDTO.class);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authDTO.getUsername());

        if (userDetails.getPassword().equals(authDTO.getPassword())) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            log.info("Autenticacion exitosa para {}", userDetails.getUsername());
            resolverLoginExitoso(response, jwtService.generarToken(userDetails.getUsername()));
        } else {
            throw new LoginException();
        }
    }

    private void resolverLoginExitoso(HttpServletResponse response, String token) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(Collections.singletonMap("token", token)));

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

    private void validarToken(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization))
            throw new JwtValidationException();

        Matcher matcher = Pattern.compile("Bearer (.+)").matcher(authorization);
        matcher.matches();
        String username = jwtService.obtenerUsuario(matcher.group(1));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        log.info("Autenticacion exitosa para {}", username);
    }

    private void resolverValidacionTokenFallida(HttpServletResponse response) throws IOException {
        log.info("Validacion de token fallida");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponseDTO.builder()
                .mensaje("Validacion de token fallida")
                .codigo(HttpStatus.UNAUTHORIZED.value())
                .build()));

    }

}
