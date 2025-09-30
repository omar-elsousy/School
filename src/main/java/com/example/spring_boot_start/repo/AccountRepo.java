package com.example.spring_boot_start.repo;

import com.example.spring_boot_start.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByUserName(String name);
}
