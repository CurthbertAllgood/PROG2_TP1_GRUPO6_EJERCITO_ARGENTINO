package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="servicio",
        uniqueConstraints = @UniqueConstraint(name="uk_servicio_nombre", columnNames = "nombre"))
@Getter @Setter @NoArgsConstructor
public class Servicio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
    private List<ServicioRealizado> realizados = new ArrayList<>();
}
