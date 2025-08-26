package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
@Entity @Table(name="usuario", uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true) private String username;
    @Column(nullable=false) private String password; // BCrypt
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;

    @OneToOne @JoinColumn(name="persona_id") private Persona persona; // Militar o Civil
}
