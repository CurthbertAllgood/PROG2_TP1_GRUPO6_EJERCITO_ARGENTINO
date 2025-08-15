package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO {
    private Long id;

    @NotBlank private String username;
    @NotBlank private String rol;     // SOLDADO|SUBOFICIAL|OFICIAL|ADMIN
    private Long militarId;           // puede ser null (ADMIN/CIVIL)
}
