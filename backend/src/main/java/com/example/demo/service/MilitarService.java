package com.example.demo.service;

import com.example.demo.dto.MilitarCreateDTO;
import com.example.demo.dto.MilitarDTO;

import java.util.List;

public interface MilitarService {
    List<MilitarDTO> getAll();
    MilitarDTO getById(Long id);
    MilitarDTO create(MilitarCreateDTO dto);
    void delete(Long id);
}
