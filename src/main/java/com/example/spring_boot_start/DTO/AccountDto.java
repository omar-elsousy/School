package com.example.spring_boot_start.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private String userName;

    private String password;

    private String phone;

    private String address;

    private List<RoleDto> roles;
}

