package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "civil")
@Getter @Setter @NoArgsConstructor
public class Civil extends Persona {
    // Campos específicos si aplica (DNI, etc.)
}
