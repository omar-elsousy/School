package com.example.spring_boot_start.service.impl;

import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.controller.vm.AuthRequest;
import com.example.spring_boot_start.controller.vm.AuthResponse;
import com.example.spring_boot_start.service.AccountService;
import com.example.spring_boot_start.service.AuthService;
import com.example.spring_boot_start.service.token.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private AccountService accountService;
    private TokenHandler tokenHandler;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AccountService accountService, TokenHandler tokenHandler, PasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.tokenHandler = tokenHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse signup(AccountDto accountDto) {
        AccountDto savedAccountDto = accountService.createAccount(accountDto);
        if(Objects.isNull(savedAccountDto)){
            throw new RuntimeException("account had not been created");
        }
        return new AuthResponse(tokenHandler.createToken(savedAccountDto));
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        AccountDto accountDto = accountService.getByUserName(authRequest.getUsername());

        if(!passwordEncoder.matches(authRequest.getPassword(),accountDto.getPassword())){
            throw new RuntimeException("invalid password");
        }
        return new AuthResponse(tokenHandler.createToken(accountDto));
    }
}
