package com.example.demo.dto;


import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginResponseDTO {
    private String username;
    private String rol;
    private Long militarId;
    private String token; // si luego usás JWT
}
