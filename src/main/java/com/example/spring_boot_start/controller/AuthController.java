package com.example.spring_boot_start.controller;


import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.controller.vm.AuthRequest;
import com.example.spring_boot_start.controller.vm.AuthResponse;
import com.example.spring_boot_start.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    ResponseEntity<AuthResponse> signup(AccountDto accountDto){
        return ResponseEntity.ok(authService.signup(accountDto));
    }

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(AuthRequest authRequest){
        return ResponseEntity.ok(authService.login(authRequest));
    }

}
