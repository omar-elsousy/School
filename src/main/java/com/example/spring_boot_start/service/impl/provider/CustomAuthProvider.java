package com.example.spring_boot_start.service.impl.provider;

import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private AccountService accountService;

    public CustomAuthProvider(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        AccountDto accountDto = accountService.getByUserName(username);

        if(!accountDto.getPassword().equals(password)){
            throw new RuntimeException("Invalid password");
        }

       List<GrantedAuthority> roles =  accountDto.getRoles().stream().map(
                            roleDto -> new SimpleGrantedAuthority("ROLE_" + roleDto.getRoleName()))
                    .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(username,password,roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
