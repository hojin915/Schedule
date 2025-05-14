package com.example.schedule.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @Size(min = 1, max = 200, message = "contents should be 1~200 characters")
    private String todo;

    private String writerName;
    private Long writerId;

    @NotBlank(message = "password is required")
    private String password;
}
