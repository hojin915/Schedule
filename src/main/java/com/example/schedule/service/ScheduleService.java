package com.example.schedule.service;

import com.example.schedule.dto.*;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedules(FindSchedulesContext context);
    ScheduleResponseDto findScheduleById(Long todoId);
    ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto dto, Long todoId);
    void deleteSchedule(PasswordRequestDto dto, Long todoId);
}
