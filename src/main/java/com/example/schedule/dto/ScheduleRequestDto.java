package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String todo;
    private String writerName;
    private Long writerId;
    private String password;
}
