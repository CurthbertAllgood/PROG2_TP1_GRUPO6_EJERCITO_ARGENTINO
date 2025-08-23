package com.example.demo.dto;

import com.example.demo.entity.Role;
import jakarta.validation.constraints.NotBlank; import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank private String username;
    @NotBlank private String password;
    @NotNull  private Role role;      // SOLDADO/SUBOFICIAL/OFICIAL/CIVIL
    @NotNull  private Long personaId; // id de Persona existente (Militar o Civil)
}
