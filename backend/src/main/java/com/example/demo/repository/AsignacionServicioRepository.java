package com.example.demo.repository;

import com.example.demo.entity.AsignacionServicio;
import com.example.demo.entity.AsignacionServicio.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignacionServicioRepository extends JpaRepository<AsignacionServicio, Long> {
    List<AsignacionServicio> findByMilitarId(Long militarId);
    List<AsignacionServicio> findByMilitarIdAndEstado(Long militarId, Estado estado);
}
