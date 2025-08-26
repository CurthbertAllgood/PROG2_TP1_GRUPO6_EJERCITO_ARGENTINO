package com.example.demo.repository;

import com.example.demo.entity.Militar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MilitarRepository extends JpaRepository<Militar, Long> {
    Optional<Militar> findByCodigoMilitar(String codigoMilitar);
}
