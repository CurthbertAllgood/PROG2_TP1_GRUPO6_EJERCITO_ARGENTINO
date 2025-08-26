package com.example.demo.repository;

import com.example.demo.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    boolean existsByCodigo(String codigo);
}
