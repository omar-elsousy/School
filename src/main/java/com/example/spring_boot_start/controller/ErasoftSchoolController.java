package com.example.spring_boot_start.controller;

import com.example.spring_boot_start.DTO.ErasoftSchoolDto;
import com.example.spring_boot_start.service.ErasoftSchoolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("eraa-soft")
@RestController
public class ErasoftSchoolController {
    private ErasoftSchoolService erasoftSchoolService;

    @Autowired
    public ErasoftSchoolController(ErasoftSchoolService erasoftSchoolService) {
        this.erasoftSchoolService = erasoftSchoolService;
    }
    @PostMapping("/add-student")
    ResponseEntity<ErasoftSchoolDto> saveStudent(@Valid @RequestBody ErasoftSchoolDto erasoftSchoolDto){
        return ResponseEntity.ok(erasoftSchoolService.save(erasoftSchoolDto));
        }
    @PutMapping("/update-student")
    ResponseEntity<ErasoftSchoolDto> updateStudent(@Valid @RequestBody ErasoftSchoolDto erasoftSchoolDto){
        return ResponseEntity.ok(erasoftSchoolService.update(erasoftSchoolDto));
    }

    @GetMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<ErasoftSchoolDto>> getAllStudents(){
        return ResponseEntity.ok(erasoftSchoolService.getAll());
    }
    @GetMapping("/student")
    ResponseEntity<ErasoftSchoolDto> getOneStudent(@RequestParam Long id){
        return ResponseEntity.ok(erasoftSchoolService.getOne(id));
    }
    @GetMapping("/studentByName")
    ResponseEntity<ErasoftSchoolDto> getOneStudentByUsername(@RequestParam String username){
        return ResponseEntity.ok(erasoftSchoolService.getByUsername(username));
    }
    @DeleteMapping("/delete-student")
    ResponseEntity<Boolean> deleteStudent(@RequestParam Long id){
        return erasoftSchoolService.delete(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

}
