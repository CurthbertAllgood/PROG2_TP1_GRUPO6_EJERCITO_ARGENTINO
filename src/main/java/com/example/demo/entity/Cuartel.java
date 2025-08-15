package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuartel",
        uniqueConstraints = @UniqueConstraint(name="uk_cuartel_nombre", columnNames = "nombre"))
@Getter @Setter @NoArgsConstructor
public class Cuartel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 200)
    private String ubicacion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cuartel_compania",
            joinColumns = @JoinColumn(name = "cuartel_id"),
            inverseJoinColumns = @JoinColumn(name = "compania_id")
    )
    private List<Compania> companias = new ArrayList<>();
}
