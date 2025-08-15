package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "militar",
        uniqueConstraints = @UniqueConstraint(name = "uk_militar_legajo", columnNames = "legajo"),
        indexes = {
                @Index(name = "ix_militar_cuartel", columnList = "cuartel_id"),
                @Index(name = "ix_militar_compania", columnList = "compania_id"),
                @Index(name = "ix_militar_cuerpo", columnList = "cuerpo_id")
        }
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_militar")
@Getter @Setter @NoArgsConstructor
public abstract class Militar extends Persona {

    @Column(nullable = false, unique = true, length = 40)
    private String legajo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cuartel_id", nullable = false)
    private Cuartel cuartel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "compania_id", nullable = false)
    private Compania compania;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cuerpo_id", nullable = false)
    private Cuerpo cuerpo;

    @OneToMany(mappedBy = "militar", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicioRealizado> servicios = new ArrayList<>();

    public abstract String getRango();
}
