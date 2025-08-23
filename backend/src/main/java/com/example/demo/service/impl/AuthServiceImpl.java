package com.example.demo.service.impl;

import com.example.demo.dto.*; import com.example.demo.entity.Persona; import com.example.demo.entity.Usuario;
import com.example.demo.repository.PersonaRepository; import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtService; import com.example.demo.service.AuthService;
import jakarta.persistence.EntityNotFoundException; import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*; import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepo; private final PersonaRepository personaRepo;
    private final PasswordEncoder encoder; private final JwtService jwt; private final AuthenticationManager authManager;

    @Override public AuthResponse register(RegisterRequest req){
        if (usuarioRepo.existsByUsername(req.getUsername())) throw new IllegalArgumentException("Username ya existe");
        Persona p = personaRepo.findById(req.getPersonaId()).orElseThrow(()->new EntityNotFoundException("Persona no encontrada"));
        var u=new Usuario(); u.setUsername(req.getUsername()); u.setPassword(encoder.encode(req.getPassword()));
        u.setRole(req.getRole()); u.setPersona(p); usuarioRepo.save(u);
        return new AuthResponse(jwt.generateToken(u.getUsername(), u.getRole().name()));
    }

    @Override public AuthResponse login(AuthRequest req){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        var u = usuarioRepo.findByUsername(auth.getName()).orElseThrow();
        return new AuthResponse(jwt.generateToken(u.getUsername(), u.getRole().name()));
    }
}
