package com.example.spring_boot_start.mapper;

import com.example.spring_boot_start.DTO.RoleDto;
import com.example.spring_boot_start.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {


    Role toRole(RoleDto roleDto);

    RoleDto toRoleDto(Role role);

    List<Role> toRoleList(List<RoleDto> roleDtos);
    List<RoleDto> toRoleDtoList(List<Role> roles);
}
