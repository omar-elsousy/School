package com.example.spring_boot_start.service.impl;


import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.mapper.AccountMapper;
import com.example.spring_boot_start.model.Account;
import com.example.spring_boot_start.model.Role;
import com.example.spring_boot_start.repo.AccountRepo;
import com.example.spring_boot_start.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;
    private AccountMapper accountMapper;
    private PasswordEncoder passwordEncoder;

@Autowired
    public AccountServiceImpl(AccountRepo accountRepo, AccountMapper accountMapper, @Lazy PasswordEncoder passwordEncoder) {
        this.accountRepo = accountRepo;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountDto getByUserName(String userName) {

        Optional<Account> account = accountRepo.findByUserName(userName);

        if (!account.isPresent()) {
            throw new RuntimeException("user name not exist");
        }

        return accountMapper.toAccountDto(account.get());
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        if(accountRepo.findByUserName(accountDto.getUserName()).isPresent()){
            throw new RuntimeException("username already exists");
        }

        Account account = accountMapper.toAccount(accountDto);
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        Role role = new Role();
        role.setRoleName("USER");
        role.setAccount(account);
        account.setRoles(List.of(role));
        return accountMapper.toAccountDto(accountRepo.save(account));
    }
}

