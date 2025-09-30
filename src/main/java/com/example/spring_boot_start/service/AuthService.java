package com.example.spring_boot_start.service;

import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.controller.vm.AuthRequest;
import com.example.spring_boot_start.controller.vm.AuthResponse;

public interface AuthService {
    AuthResponse signup(AccountDto accountDto);
    AuthResponse login(AuthRequest authRequest);
}
