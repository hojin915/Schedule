package com.example.schedule.service;

import com.example.schedule.dto.PasswordRequestDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.FindSchedulesContext;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedules(FindSchedulesContext context);
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(ScheduleRequestDto dto, Long id);
    void deleteSchedule(PasswordRequestDto dto, Long id);
}
