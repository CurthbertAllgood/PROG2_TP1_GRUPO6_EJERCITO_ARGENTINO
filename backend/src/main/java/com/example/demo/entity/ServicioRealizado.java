package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"militar_id","servicio_id","fecha"})
)

public class ServicioRealizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "militar_id")
    private Militar militar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @Column(nullable = false)
    private LocalDate fecha;
}
