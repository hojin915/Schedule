package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private String todo;
    private String writerName;

    @NotBlank(message = "password is required")
    private String password;
}
