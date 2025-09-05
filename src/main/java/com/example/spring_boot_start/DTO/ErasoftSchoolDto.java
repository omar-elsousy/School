package com.example.spring_boot_start.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErasoftSchoolDto {
    private Long id;
    @NotBlank(message = "{username.required}")
    @Size(min = 3,message = "{username.size}")
    private String fullUsername;
    @NotBlank(message = "{password.required}")
    @Size(min = 3,max = 10,message = "{password.size}")
    private String password;
    @NotNull(message = "{age.required}")
    @Min(value = 21,message = "{age.min}")
    private Integer age;
}

