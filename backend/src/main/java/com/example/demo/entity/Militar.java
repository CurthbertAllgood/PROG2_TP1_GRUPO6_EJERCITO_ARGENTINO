package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_militar")
public abstract class Militar extends Persona {



    @Column(nullable = false, unique = true)
    private String codigoMilitar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuerpo_id")
    private Cuerpo cuerpo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "compania_id")
    private Compania compania;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cuartel_id")
    private Cuartel cuartel;

}