package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.PersonaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(AuthRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        Usuario u = usuarioRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Long personaId = Optional.ofNullable(u.getPersona()).map(Persona::getId).orElse(null);
        String token = jwtService.generateToken(u.getUsername(), u.getRole().name(), personaId);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse register(RegisterRequest req) {
        if (usuarioRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Persona persona = null;
        if (req.getPersonaId() != null) {
            persona = personaRepository.findById(req.getPersonaId())
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        }

        Usuario u = new Usuario();
        u.setUsername(req.getUsername());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setRole(req.getRole());
        u.setPersona(persona);

        usuarioRepository.save(u);

        Long personaId = (persona != null) ? persona.getId() : null;
        String token = jwtService.generateToken(u.getUsername(), u.getRole().name(), personaId);
        return new AuthResponse(token);
    }
}
