package com.dux.pruebatecnica.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipoCreateDTO {
    @NotBlank(message = "El nombre no puede ser nulo ni estar vacio")
    @Schema(description = "Nombre del equipo", example = "River Plate")
    private String nombre;
    @NotBlank(message = "La liga no puede ser nula ni estar vacia")
    @Schema(description = "Liga en la que compite el equipo", example = "Primera Division")
    private String liga;
    @NotBlank(message = "El pais no puede ser nulo ni estar vacio")
    @Schema(description = "Pais del equipo", example = "Argentina")
    private String pais;
}
