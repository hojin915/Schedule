package com.example.schedule.service;

import com.example.schedule.dto.*;
import com.example.schedule.dto.schedule.FindSchedulesContext;
import com.example.schedule.dto.schedule.ScheduleRequestDto;
import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.dto.schedule.ScheduleUpdateRequestDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    PageResponseDto<ScheduleResponseDto> findAllSchedules(FindSchedulesContext context);
    ScheduleResponseDto findScheduleById(Long todoId);
    ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto dto, Long todoId);
    void deleteSchedule(PasswordRequestDto dto, Long todoId);
}
