package com.dux.pruebatecnica.demo.dtos;

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
    private String nombre;
    @NotBlank(message = "La liga no puede ser nula ni estar vacia")
    private String liga;
    @NotBlank(message = "El pais no puede ser nulo ni estar vacio")
    private String pais;
}
