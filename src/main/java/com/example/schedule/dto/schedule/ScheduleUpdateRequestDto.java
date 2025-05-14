package com.example.schedule.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    @Size(max = 200, message = "maximum 200 characters")
    private String todo;
    private String writerName;

    @NotBlank(message = "password is required")
    private String password;
}