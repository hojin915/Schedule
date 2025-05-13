package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Todo;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        Todo todo = new Todo(dto.getTodo(), dto.getWriterName(), dto.getWriterId(), dto.getPassword());
        return scheduleRepository.createSchedule(todo);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(Long userId, LocalDate date) {
        List<ScheduleResponseDto> result = scheduleRepository.findAllSchedules();
        if(userId != null){
            result = result.stream().filter(schedule -> schedule.getWriterId().equals(userId)).toList();
        }
        if(date != null){
            result = result.stream()
                    .filter(schedule -> schedule.getUpdatedAt().toLocalDate().isAfter(date))
                    .toList();
        }
        result = result.stream().sorted(Comparator.comparing(a -> a.getUpdatedAt().toLocalDate())).toList();
        return result;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id);
    }

    @Override
    public ScheduleResponseDto updateSchedule(ScheduleRequestDto dto, Long id) {
        if(!dto.getPassword().equals(scheduleRepository.findPasswordById(id))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Password does not match");
        }
        if(dto.getTodo() != null && dto.getWriterName() != null){
            return scheduleRepository.updateSchedule(dto, id);
        }
        if(dto.getTodo() != null){
            return scheduleRepository.updateScheduleTodo(dto, id);
        }
        if(dto.getWriterName() != null){
            return scheduleRepository.updateScheduleWriterName(dto, id);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update contents does not exist");
    }

    @Override
    public void deleteSchedule(Long id) {
        int deletedRow = scheduleRepository.deleteSchedule(id);
        if(deletedRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + "does not exist");
        }
    }
}