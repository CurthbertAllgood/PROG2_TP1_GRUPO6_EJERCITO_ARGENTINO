package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Soldado extends Militar {
    // Soldado solo puede consultar: no requiere atributos extra por ahora
}