package com.example.schedule.service;

import com.example.schedule.dto.PasswordRequestDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.FindSchedulesContext;
import com.example.schedule.entity.Todo;
import com.example.schedule.exception.NotFoundException;
import com.example.schedule.exception.PasswordMismatchException;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<ScheduleResponseDto> findAllSchedules(FindSchedulesContext context) {
        List<ScheduleResponseDto> result = scheduleRepository.findAllSchedules();
        if(context.getUserId() != null){
            result = result.stream()
                    .filter(schedule -> schedule.getWriterId().equals(context.getUserId()))
                    .toList();
        }
        if(context.getDate() != null){
            result = result.stream()
                    .filter(schedule -> schedule.getUpdatedAt().toLocalDate().isAfter(context.getDate()))
                    .toList();
        }
        result = result.stream().sorted(Comparator.comparing(a -> a.getUpdatedAt().toLocalDate())).toList();
        int pageOffset = context.getOffset();
        result = result.subList(pageOffset, pageOffset + context.getSize());
        int totalResult = result.size();
        int totalPages = (totalResult + context.getSize() - 1) / context.getSize(); // 전체 페이지 수 필요하면 사용
        if(totalResult < context.getSize()){
            return null;
        }
        return result;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long todoId) {
        ScheduleResponseDto result = scheduleRepository.findScheduleById(todoId);
        if(result == null){
            throw new NotFoundException("Schedule not found or already deleted");
        } else{
            return result;
        }
    }

    @Override
    public ScheduleResponseDto updateSchedule(ScheduleRequestDto dto, Long todoId) {
        if(!dto.getPassword().equals(scheduleRepository.findPasswordById(todoId))){
            throw new PasswordMismatchException("Password mismatch");
        }
        if(dto.getTodo() != null && dto.getWriterName() != null){
            return scheduleRepository.updateSchedule(dto, todoId);
        }
        if(dto.getTodo() != null){
            return scheduleRepository.updateScheduleTodo(dto, todoId);
        }
        if(dto.getWriterName() != null){
            return scheduleRepository.updateScheduleWriterName(dto, todoId);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update contents does not exist");
    }

    @Override
    public void deleteSchedule(PasswordRequestDto dto, Long todoId) {
        if(!dto.getPassword().equals(scheduleRepository.findPasswordById(todoId)) && scheduleRepository.findScheduleById(todoId) != null){
            throw new PasswordMismatchException("Password mismatch");
        }
        int deletedRow = scheduleRepository.deleteSchedule(todoId);
        if(deletedRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, todoId + "does not exist");
        }
    }
}