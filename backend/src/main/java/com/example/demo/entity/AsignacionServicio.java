package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "asignacion_servicio",
        uniqueConstraints = @UniqueConstraint(columnNames = {"militar_id","servicio_id","fecha_programada"})
)
public class AsignacionServicio {

    public enum Estado { PENDIENTE, REALIZADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "militar_id")
    private Militar militar;

    @ManyToOne(optional = false) @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @Column(name = "fecha_programada", nullable = false)
    private LocalDate fechaProgramada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.PENDIENTE;

    @Column(name = "fecha_realizacion")
    private LocalDate fechaRealizacion;

    // Auditoría básica (quién creó la asignación)
    @ManyToOne @JoinColumn(name = "registrado_por_usuario_id")
    private Usuario registradoPor;

    @CreationTimestamp @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
