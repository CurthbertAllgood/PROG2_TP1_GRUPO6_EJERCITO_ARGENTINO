    package com.example.demo.security;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import java.util.List;

    @Configuration
    public class CorsConfig {

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration c = new CorsConfiguration();

            // Orígenes de tu frontend (CRA y/o Vite)
            c.setAllowedOrigins(List.of(
                    "http://localhost:3000",
                    "http://localhost:5173"
            ));


            c.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
            c.setAllowedHeaders(List.of("Authorization", "Content-Type"));

            // Si más adelante usás cookies/sesión desde el browser, pasá esto a true
            c.setAllowCredentials(false);

            c.setMaxAge(3600L); // cache del preflight 1h

            UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
            src.registerCorsConfiguration("/**", c);
            return src;
        }
    }
