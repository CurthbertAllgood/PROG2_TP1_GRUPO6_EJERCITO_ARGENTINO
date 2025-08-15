package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public abstract class Persona {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank @Column(nullable = false, length = 100)
    protected String nombre;

    @NotBlank @Column(nullable = false, length = 100)
    protected String apellido;
}
