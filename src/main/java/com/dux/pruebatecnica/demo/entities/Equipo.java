package com.dux.pruebatecnica.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Equipo")
@Table(name = "equipos", schema = "dux")
@Builder
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String liga;
    private String pais;


}
