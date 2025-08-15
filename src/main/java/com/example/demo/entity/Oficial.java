package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("OFICIAL")
@Getter @Setter @NoArgsConstructor
public class Oficial extends Militar {
    private static final String RANGO = "OFICIAL";
    @Override public String getRango() { return RANGO; }
}
