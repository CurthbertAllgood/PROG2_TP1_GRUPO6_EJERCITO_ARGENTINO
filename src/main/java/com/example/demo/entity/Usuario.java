package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario",
        indexes = @Index(name="ix_usuario_username", columnList = "username", unique = true))
@Getter @Setter @NoArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String username;

    @Column(nullable = false)
    private String passwordHash; // BCrypt (no texto plano)

    @Column(nullable = false, length = 20)
    private String rol; // "SOLDADO" | "SUBOFICIAL" | "OFICIAL" | "ADMIN"

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "militar_id")
    private Militar militar; // puede ser null (por ej. ADMIN)
}
