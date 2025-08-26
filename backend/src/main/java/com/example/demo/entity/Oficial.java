package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Oficial extends Militar {
    // Puede administrar todo
    // Espacio para lógica futura (como reportes o auditoría)
}