package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Todo;

import java.util.List;

public interface ScheduleRepository{
    ScheduleResponseDto createSchedule(Todo todo);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(ScheduleRequestDto dto, Long id);
    ScheduleResponseDto updateScheduleTodo(ScheduleRequestDto dto, Long id);
    ScheduleResponseDto updateScheduleWriterName(ScheduleRequestDto dto, Long id);
    String findPasswordById(Long id);
    int deleteSchedule(Long id);
}
