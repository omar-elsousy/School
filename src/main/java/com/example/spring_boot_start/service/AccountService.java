package com.example.spring_boot_start.service;

import com.example.spring_boot_start.DTO.AccountDto;

public interface AccountService {


    AccountDto getByUserName(String userName);
    AccountDto createAccount(AccountDto accountDto);
}
