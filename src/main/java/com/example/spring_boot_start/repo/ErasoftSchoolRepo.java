package com.example.spring_boot_start.repo;

import com.example.spring_boot_start.model.ErasoftSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ErasoftSchoolRepo extends JpaRepository<ErasoftSchool,Long> {

    //ErasoftSchool findByUsername(String username);

    //@Query(value = "select * from ERASOFT_SCHOOL where USERNAME = :username",nativeQuery = true)

    @Query(value = "select ess from ErasoftSchool ess where ess.username = :username",nativeQuery = false)
    ErasoftSchool extractByUsername(@Param("username") String username);
}
