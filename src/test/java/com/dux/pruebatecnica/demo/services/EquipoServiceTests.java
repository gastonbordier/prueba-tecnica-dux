package com.dux.pruebatecnica.demo.services;

import com.dux.pruebatecnica.demo.dtos.EquipoDTO;
import com.dux.pruebatecnica.demo.dtos.EquipoUpdateDTO;
import com.dux.pruebatecnica.demo.entities.Equipo;
import com.dux.pruebatecnica.demo.exceptions.EquipoNotFoundException;
import com.dux.pruebatecnica.demo.exceptions.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Equipos")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class EquipoServiceTests {

    @Autowired
    EquipoService service;

    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Obtener equipo/s")
    class obtenerEquipos {
        @Order(1)
        @DisplayName("Listar equipos")
        @Test
        void testListarEquipos_DeberiaDevolverListaDeEquipos() {
            // Verifica que la lista no esté vacía y contenga los equipos esperados
            List<?> equipo = service.findAll();
            assertFalse(equipo.isEmpty());
            assertEquals(24, equipo.size());
        }

        @Order(2)
        @DisplayName("Obtener equipo por id")
        @Test
        void testObtenerEquipoPorId_DeberiaDevolverEquipoExistente() {
            // Simula un ID existente y verifica que el equipo retornado es el esperado
            Equipo equipo = service.findById(5);
            assertNotNull(equipo);
            assertEquals("Juventus FC", equipo.getNombre());
        }

        @Order(3)
        @DisplayName("Obtener equipo por id - Excepcion")
        @Test
        void testObtenerEquipoPorId_NoExistente_DeberiaLanzarExcepcion() {
            // Simula un ID no existente y verifica que se lanza la excepción correcta
            assertThrows(EquipoNotFoundException.class, () -> service.findById(30));
        }
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Crear equipo")
    class CrearEquipo {

        @Order(1)
        @DisplayName("Crear equipo")
        @Test
        void testCrearEquipo_DeberiaCrearEquipoCorrectamente() {
            // Verifica que el equipo es creado correctamente cuando los datos son válidos
            Equipo equipo = service.create(EquipoDTO.builder()
                    .nombre("Boca Juniors")
                    .liga("Primera Division")
                    .pais("Argentina")
                    .build());

            assertNotNull(equipo);
            assertNotNull(equipo.getId());
        }

        @Order(2)
        @DisplayName("Crear Equipo- Excepcion")
        @Test
        void testCrearEquipo_DatosInvalidos_DeberiaLanzarExcepcion() {
            // Verifica que se lance una excepción cuando se intentan crear datos inválidos
            EquipoDTO dto = EquipoDTO.builder()
                    .nombre(null)
                    .liga("Primera Division")
                    .pais("Argentina")
                    .build();
            assertThrows(ValidationFailedException.class, () -> service.create(dto));
        }
    }

    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Actualizar equipo")
    class ActualizarEquipo {

        @Order(1)
        @DisplayName("Actualizar equipo")
        @Test
        void testActualizarEquipo_DeberiaActualizarEquipoExistente() {
            // Verifica que el equipo sea actualizado cuando el ID es válido
            Equipo equipo = service.update(2, EquipoUpdateDTO.builder()
                    .nombre("Portugal")
                    .liga("Primeira liga")
                    .build());

            assertNotNull(equipo);
            assertEquals("Portugal", equipo.getNombre());
        }

        @Order(2)
        @DisplayName("Actualizar equipo - Excepcion")
        @Test
        void testActualizarEquipo_NoExistente_DeberiaLanzarExcepcion() {
            // Verifica que se lance una excepción al intentar actualizar un equipo no existente
            assertThrows(EquipoNotFoundException.class, () -> service.update(40, EquipoUpdateDTO.builder().build()));
        }
    }

    @Nested
    @Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Eliminar equipo")
    class EliminarEquipo {
        @Order(1)
        @DisplayName("Eliminar equipo")
        @Test
        void testEliminarEquipo_DeberiaEliminarEquipoExistente() {
            // Verifica que el equipo sea eliminado cuando el ID es válido
            service.deleteById(5);
            assertThrows(EquipoNotFoundException.class, () -> service.findById(5));

        }

        @Order(2)
        @DisplayName("Eliminar equipo - Excepcion")
        @Test
        void testEliminarEquipo_NoExistente_DeberiaLanzarExcepcion() {
            // Verifica que se lance una excepción al intentar eliminar un equipo no existente
            assertThrows(EquipoNotFoundException.class, () -> service.deleteById(5));
        }
    }

    @Nested
    @Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Buscar equipo por nombre")
    class BuscarEquipo {

        @Order(1)
        @DisplayName("Buscar equipo")
        @Test
        void testBuscarEquipoPorNombre_Existente_DeberiaDevolverEquipo() {
            // Verifica que la busqueda devuelva el equipo esperado
            List<Equipo> equipos = service.findByNombre("Boca");
            assertEquals(1, equipos.size());
            assertEquals("Boca Juniors", equipos.get(0).getNombre());

        }

        @Order(2)
        @DisplayName("Buscar equipo - lista vacia")
        @Test
        void testBuscarEquipoPorNombre_Inexistente_DeberiaDevolverListaVacia() {
            // Verifica que la busqueda devuelva una lista vacia
            List<Equipo> equipos = service.findByNombre("River");
            assertEquals(Collections.emptyList(), equipos);
        }
    }

}