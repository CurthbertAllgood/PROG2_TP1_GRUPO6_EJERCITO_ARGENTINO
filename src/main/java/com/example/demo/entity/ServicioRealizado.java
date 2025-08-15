package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "servicio_realizado",
        indexes = {
                @Index(name="ix_sr_militar", columnList = "militar_id"),
                @Index(name="ix_sr_servicio", columnList = "servicio_id")
        })
@Getter @Setter @NoArgsConstructor
public class ServicioRealizado {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "militar_id", nullable = false)
    private Militar militar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    private LocalDate fecha;
    private Integer horas;

    @Column(length = 255)
    private String observaciones;
}
