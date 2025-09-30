package com.example.spring_boot_start.controller;

import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user")
    ResponseEntity<AccountDto> getStudentById(@RequestParam String userName) {
        return ResponseEntity.ok(accountService.getByUserName(userName));
    }

}
