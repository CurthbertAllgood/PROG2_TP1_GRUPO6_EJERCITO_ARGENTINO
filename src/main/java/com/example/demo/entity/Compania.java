package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compania")
@Getter @Setter @NoArgsConstructor
public class Compania {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero; // si tu TP usa número de compañía

    @Column(length = 120)
    private String actividadPrincipal;

    @ManyToMany(mappedBy = "companias", fetch = FetchType.LAZY)
    private List<Cuartel> cuarteles = new ArrayList<>();

    @OneToMany(mappedBy = "compania", fetch = FetchType.LAZY)
    private List<Militar> militares = new ArrayList<>();
}
