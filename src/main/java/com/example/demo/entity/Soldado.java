package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("SOLDADO")
@Getter @Setter @NoArgsConstructor
public class Soldado extends Militar {
    private static final String RANGO = "SOLDADO";
    @Override public String getRango() { return RANGO; }
}
