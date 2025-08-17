package com.example.demo.security;

import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repo;

    public UserDetailsServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        // OJO: si usás LAZY en usuario.militar, asegurate de no acceder fuera de transacción
        if (usuario.getMilitar() != null) {
            usuario.getMilitar().getId(); // tocar el id fuerza inicialización mínima
        }
        return new UserPrincipal(usuario);
    }
}
