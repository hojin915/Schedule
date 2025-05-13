package com.example.schedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "exact email type required")
    private String email;
}
