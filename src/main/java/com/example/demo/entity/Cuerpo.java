package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuerpo",
        uniqueConstraints = @UniqueConstraint(name="uk_cuerpo_denominacion", columnNames = "denominacion"))
@Getter @Setter @NoArgsConstructor
public class Cuerpo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String denominacion;

    @OneToMany(mappedBy = "cuerpo", fetch = FetchType.LAZY)
    private List<Militar> militares = new ArrayList<>();
}
