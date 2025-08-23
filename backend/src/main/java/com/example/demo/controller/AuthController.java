package com.example.demo.controller;

import com.example.demo.dto.*; import com.example.demo.service.AuthService;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;

@RestController @RequiredArgsConstructor @RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register") public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req){
        return ResponseEntity.ok(authService.register(req));
    }
    @PostMapping("/login") public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req){
        return ResponseEntity.ok(authService.login(req));
    }
}
