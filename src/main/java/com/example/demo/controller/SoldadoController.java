package com.example.demo.controller;

import com.example.demo.service.CRUDService;
import com.example.demo.dto.SoldadoDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/soldados")
public class SoldadoController extends PlantillaCRUDController<SoldadoDTO, Long> {
    public SoldadoController(CRUDService<SoldadoDTO, Long> service) { // <- tu SoldadoService implementa CRUDService
        super(service);
    }

    @GetMapping("/ping")
    @PreAuthorize("hasAnyRole('SOLDADO','SUBOFICIAL','OFICIAL')")
    public String ping() { return "pong"; }

    @GetMapping("/solo-oficial")
    @PreAuthorize("hasRole('OFICIAL')")
    public String soloOficial() { return "OK OFICIAL"; }

}
