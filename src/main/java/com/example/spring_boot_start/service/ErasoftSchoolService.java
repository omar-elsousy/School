package com.example.spring_boot_start.service;

import com.example.spring_boot_start.DTO.ErasoftSchoolDto;

import java.util.List;

public interface ErasoftSchoolService {
    ErasoftSchoolDto save(ErasoftSchoolDto erasoftSchoolDto);
    ErasoftSchoolDto update(ErasoftSchoolDto erasoftSchoolDto);
    List<ErasoftSchoolDto> getAll();
    ErasoftSchoolDto getOne(Long id);
    boolean delete(Long id);
    ErasoftSchoolDto getByUsername(String username);
}
