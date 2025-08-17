// security/UserPrincipal.java
package com.example.demo.security;

import com.example.demo.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final Long usuarioId;
    private final String username;
    private final String passwordHash;
    private final String rol;          // "SOLDADO", "SUBOFICIAL", "OFICIAL", "ADMIN"
    private final Long personaId;      // id de Persona/Militar (para "esMismoSoldado"), puede ser null

    public UserPrincipal(Usuario u) {
        this.usuarioId = u.getId();
        this.username = u.getUsername();
        this.passwordHash = u.getPasswordHash();
        this.rol = u.getRol();
        this.personaId = (u.getMilitar() != null ? u.getMilitar().getId() : null);
    }

    /** Exponer roles con prefijo ROLE_ para hasRole('XYZ') */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol));
    }

    @Override public String getPassword() { return passwordHash; }
    @Override public String getUsername() { return username; }

    // Para este caso, dejamos todo habilitado
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    // --- extras propios que usa tu Authz ---
    public Long getUsuarioId() { return usuarioId; }
    public Long getPersonaId() { return personaId; }
    public String getRolPlano() { return rol; }
}
