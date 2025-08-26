package com.example.demo.service.impl;

import com.example.demo.dto.AsignacionCreateDTO;
import com.example.demo.dto.AsignacionDTO;
import com.example.demo.entity.AsignacionServicio;
import com.example.demo.entity.AsignacionServicio.Estado;
import com.example.demo.entity.Militar;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.ServicioRealizado;
import com.example.demo.mapper.AsignacionServicioMapper;
import com.example.demo.repository.AsignacionServicioRepository;
import com.example.demo.repository.MilitarRepository;
import com.example.demo.repository.ServicioRealizadoRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.AsignacionServicioService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignacionServicioServiceImpl implements AsignacionServicioService {

    private final AsignacionServicioRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final MilitarRepository militarRepo;
    private final ServicioRepository servicioRepo;
    private final ServicioRealizadoRepository realizadoRepo;
    private final AsignacionServicioMapper mapper;

    @Override
    public AsignacionDTO crear(AsignacionCreateDTO dto, String actorUsername) {
        Militar m = militarRepo.findById(dto.getMilitarId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Militar no encontrado"));
        Servicio s = servicioRepo.findById(dto.getServicioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado"));

        AsignacionServicio a = new AsignacionServicio();
        a.setMilitar(m);
        a.setServicio(s);
        a.setFechaProgramada(dto.getFechaProgramada());
        a.setEstado(Estado.PENDIENTE);

        if (actorUsername != null) {
            usuarioRepo.findByUsername(actorUsername).ifPresent(a::setRegistradoPor);
        }

        try {
            a = repo.save(a);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una asignación igual (militar/servicio/fecha).");
        }

        return mapper.toDTO(a);
    }

    @Override
    public List<AsignacionDTO> listar(Long militarId, String estadoOpt, String actorRole, Long actorPersonaId) {
        if ("SOLDADO".equals(actorRole)) {
            militarId = actorPersonaId; // soldado solo ve sus asignaciones
        }
        List<AsignacionServicio> out;
        if (militarId != null && estadoOpt != null) {
            Estado est = Estado.valueOf(estadoOpt.toUpperCase());
            out = repo.findByMilitarIdAndEstado(militarId, est);
        } else if (militarId != null) {
            out = repo.findByMilitarId(militarId);
        } else {
            out = repo.findAll();
        }
        return mapper.toDTOs(out);
    }

    @Override
    public AsignacionDTO marcarRealizado(Long id, LocalDate fecha, String actorRole, Long actorPersonaId) {
        AsignacionServicio a = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignación no encontrada"));

        // SOLDADO solo puede marcar realizadas sus propias asignaciones
        if ("SOLDADO".equals(actorRole) && !a.getMilitar().getId().equals(actorPersonaId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No podés modificar asignaciones de otro militar.");
        }

        if (a.getEstado() == Estado.REALIZADO) {
            return mapper.toDTO(a); // idempotente
        }

        LocalDate when = (fecha != null) ? fecha : LocalDate.now();
        a.setEstado(Estado.REALIZADO);
        a.setFechaRealizacion(when);
        a = repo.save(a);


// Histórico en ServicioRealizado (si no existe ya por unique)
        try {
            boolean yaExiste = realizadoRepo.existsByMilitarIdAndServicioIdAndFecha(
                    a.getMilitar().getId(),
                    a.getServicio().getId(),
                    when
            );
            if (!yaExiste) {
                ServicioRealizado r = new ServicioRealizado();
                r.setMilitar(a.getMilitar());
                r.setServicio(a.getServicio());
                r.setFecha(when);
                realizadoRepo.save(r);
            }
        } catch (org.springframework.dao.DataIntegrityViolationException ignore) {
            // Si dos hilos llegan a la vez, la unique del DB nos protege; ignoramos el choque.
        }

        return mapper.toDTO(a);
    }
}
