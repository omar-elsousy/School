package com.example.spring_boot_start.service.impl;

import com.example.spring_boot_start.DTO.ErasoftSchoolDto;
import com.example.spring_boot_start.mapper.ErasoftMapper;
import com.example.spring_boot_start.model.ErasoftSchool;
import com.example.spring_boot_start.repo.ErasoftSchoolRepo;
import com.example.spring_boot_start.service.ErasoftSchoolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ErasoftSchoolServiceImpl implements ErasoftSchoolService {

    private ErasoftSchoolRepo erasoftSchoolRepo;
    // private ModelMapper modelMapper;
    private ErasoftMapper erasoftMapper;

    public ErasoftSchoolServiceImpl() {
    }

    @Autowired
    public ErasoftSchoolServiceImpl(ErasoftSchoolRepo erasoftSchoolRepo, ErasoftMapper erasoftMapper /*ModelMapper modelMapper*/) {
        this.erasoftSchoolRepo = erasoftSchoolRepo;
        // this.modelMapper = modelMapper;
        this.erasoftMapper= erasoftMapper;
    }

//    private ErasoftSchoolDto convertToDto(ErasoftSchool entity){
//        ErasoftSchoolDto dto = modelMapper.map(entity, ErasoftSchoolDto.class);
//        dto.setFullUsername(entity.getUsername());
//        return dto;
//    }
//
//
//    private ErasoftSchool convertToEntity(ErasoftSchoolDto dto){
//        ErasoftSchool entity = modelMapper.map(dto,ErasoftSchool.class);
//        entity.setUsername(dto.getFullUsername());
//        return entity;
//    }


    @Override
    public ErasoftSchoolDto save(ErasoftSchoolDto erasoftSchoolDto) {
        if (erasoftSchoolDto.getId() != null) {
            throw new RuntimeException("id must be null");
        }
        // ErasoftSchool entity = convertToEntity(erasoftSchoolDto);
        ErasoftSchool entity = erasoftMapper.toEntity(erasoftSchoolDto);
        ErasoftSchool saved = erasoftSchoolRepo.save(entity);
        // return convertToDto(saved);
        return erasoftMapper.toDto(saved);
    }

    @Override
    public ErasoftSchoolDto update(ErasoftSchoolDto erasoftSchoolDto) {
        if (Objects.isNull(erasoftSchoolDto.getId())) {
            throw new RuntimeException("id mustn't be null");
        }
        if (!erasoftSchoolRepo.existsById(erasoftSchoolDto.getId())) {
            throw new RuntimeException("Student not found");
        }
        // ErasoftSchool entity = convertToEntity(erasoftSchoolDto);
        ErasoftSchool entity = erasoftMapper.toEntity(erasoftSchoolDto);
        ErasoftSchool updated = erasoftSchoolRepo.save(entity);
        // return convertToDto(updated);
        return erasoftMapper.toDto(updated);
    }

    @Override
    public List<ErasoftSchoolDto> getAll() {
        /* return erasoftSchoolRepo.findAll().
                stream().map(this::convertToDto).collect(Collectors.toList()); */
        List<ErasoftSchool> entities = erasoftSchoolRepo.findAll();
        return erasoftMapper.toDtos(entities);
    }

    @Override
    public ErasoftSchoolDto getOne(Long id) {
        /* Optional <ErasoftSchool> entity = erasoftSchoolRepo.findById(id);
        if(entity.isEmpty()){
            return null;
        }
        // return entity.map(this::convertToDto).orElse(null); */
        return erasoftSchoolRepo.findById(id).map(erasoftMapper::toDto).orElse(null);
    }

    @Override
    public ErasoftSchoolDto getByUsername(String username) {
        ErasoftSchool entity = erasoftSchoolRepo.extractByUsername(username);
        if(Objects.isNull(entity)){
            return null;
        }
        // return convertToDto(entity);
        return erasoftMapper.toDto(entity);
    }

    @Override
    public boolean delete(Long id) {
        Optional <ErasoftSchool> erasoftSchool = erasoftSchoolRepo.findById(id);
        if(erasoftSchool.isEmpty()){
            return false;
        }
        erasoftSchoolRepo.deleteById(id);
        return true;
    }


}
