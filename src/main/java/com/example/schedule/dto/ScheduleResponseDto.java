package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long todoId;
    private String todo;
    private String writerName;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
