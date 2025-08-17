// security/Authz.java
package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("authz")
public class Authz {
    public boolean esMismoSoldado(Authentication auth, Long soldadoId) {
        if (auth == null || !auth.isAuthenticated() || soldadoId == null) return false;
        Object p = auth.getPrincipal();
        if (p instanceof UserPrincipal up) {
            Long personaId = up.getPersonaId(); // viene de usuario.militar.id
            return personaId != null && personaId.equals(soldadoId);
        }
        return false;
    }
}
