package com.dux.pruebatecnica.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Equipo")
@Table(name = "equipos", schema = "dux")
@Builder
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String liga;
    private String pais;
}
