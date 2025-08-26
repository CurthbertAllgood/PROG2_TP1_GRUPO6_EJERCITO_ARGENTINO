package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Suboficial extends Militar {
    // Puede administrar soldados y servicios
    // Podés agregar lógica más adelante si es necesario
}