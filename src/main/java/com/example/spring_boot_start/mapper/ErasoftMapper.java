package com.example.spring_boot_start.mapper;

import com.example.spring_boot_start.DTO.ErasoftSchoolDto;
import com.example.spring_boot_start.model.ErasoftSchool;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ErasoftMapper {
    @Mapping(source = "fullUsername" , target = "username")
    ErasoftSchool toEntity(ErasoftSchoolDto erasoftSchoolDto);
    @Mapping(source = "username" , target = "fullUsername")
    ErasoftSchoolDto toDto(ErasoftSchool erasoftSchool);

    List<ErasoftSchool> toEntities(List <ErasoftSchoolDto> erasoftSchoolDtos);
    List<ErasoftSchoolDto> toDtos(List<ErasoftSchool> erasoftSchools);
}
