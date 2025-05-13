package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedules(Long userId, LocalDate date);
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(ScheduleRequestDto dto, Long id);
    void deleteSchedule(Long id);
}
