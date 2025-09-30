package com.example.spring_boot_start.mapper;


import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountDto accountDto);

    AccountDto toAccountDto(Account account);

    List<Account> toAccountList(List<AccountDto> accountDtos);

    List<AccountDto> toAccountDtoList(List<Account> accounts);
}
