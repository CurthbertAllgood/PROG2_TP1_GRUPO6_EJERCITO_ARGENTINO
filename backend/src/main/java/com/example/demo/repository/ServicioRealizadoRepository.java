package com.example.demo.repository;

import com.example.demo.entity.ServicioRealizado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ServicioRealizadoRepository extends JpaRepository<ServicioRealizado, Long> {
    boolean existsByMilitarIdAndServicioIdAndFecha(Long militarId, Long servicioId, LocalDate fecha);
}
