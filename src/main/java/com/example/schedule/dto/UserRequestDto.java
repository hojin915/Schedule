package com.example.schedule.dto;

import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {
    private String name;
    private String email;
}
