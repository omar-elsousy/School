package com.example.spring_boot_start.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

// select RoleDto
@Setter
@Getter
public class RoleDto {

    private Long id;

    private String roleName;


//    private AccountDto account;
}