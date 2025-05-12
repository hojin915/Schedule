package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(ScheduleRequestDto dto, Long id);
    void deleteSchedule(Long id);
}
