package com.dux.pruebatecnica.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoUpdateDTO {
    @Schema(description = "Nombre del equipo", nullable = true, example = "Aston Villa")
    private String nombre;
    @Schema(description = "Liga del equipo", nullable = true ,example = "Premier League")
    private String liga;
    @Schema(description = "Pais del equipo", nullable = true, example = "Inglaterra")
    private String pais;
}
