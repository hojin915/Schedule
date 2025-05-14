package com.example.schedule.repository;

import com.example.schedule.dto.schedule.ScheduleResponseDto;
import com.example.schedule.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedule.entity.Todo;

import java.util.List;

public interface ScheduleRepository{
    ScheduleResponseDto createSchedule(Todo todo);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(Long todoId);
    ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto dto, Long todoId);
    ScheduleResponseDto updateScheduleTodo(ScheduleUpdateRequestDto dto, Long todoId);
    ScheduleResponseDto updateScheduleWriterName(ScheduleUpdateRequestDto dto, Long todoId);
    String findPasswordById(Long todoId);
    int deleteSchedule(Long todoId);
}
