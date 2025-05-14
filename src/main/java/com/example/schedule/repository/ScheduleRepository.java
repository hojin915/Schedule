package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Todo;

import java.util.List;

public interface ScheduleRepository{
    ScheduleResponseDto createSchedule(Todo todo);
    List<ScheduleResponseDto> findAllSchedules();
    ScheduleResponseDto findScheduleById(Long todoId);
    ScheduleResponseDto updateSchedule(ScheduleRequestDto dto, Long todoId);
    ScheduleResponseDto updateScheduleTodo(ScheduleRequestDto dto, Long todoId);
    ScheduleResponseDto updateScheduleWriterName(ScheduleRequestDto dto, Long todoId);
    String findPasswordById(Long todoId);
    int deleteSchedule(Long todoId);
}
