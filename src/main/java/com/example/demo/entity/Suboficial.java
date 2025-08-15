package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("SUBOFICIAL")
@Getter @Setter @NoArgsConstructor
public class Suboficial extends Militar {
    private static final String RANGO = "SUBOFICIAL";
    @Override public String getRango() { return RANGO; }
}
