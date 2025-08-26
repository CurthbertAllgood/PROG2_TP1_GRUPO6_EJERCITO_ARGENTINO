package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Compania {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String actividadPrincipal;


    @ManyToMany
    @JoinTable(
            name = "compania_cuartel",
            joinColumns = @JoinColumn(name = "compania_id"),
            inverseJoinColumns = @JoinColumn(name = "cuartel_id")
    )
    private Set<Cuartel> cuarteles = new HashSet<>();
}