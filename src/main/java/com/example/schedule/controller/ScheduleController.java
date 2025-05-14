package com.example.schedule.controller;

import com.example.schedule.dto.PasswordRequestDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.FindSchedulesContext;
import com.example.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody @Valid ScheduleRequestDto dto){
        System.out.println(dto.getWriterId());
        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) LocalDate date
    ){
        FindSchedulesContext context = new FindSchedulesContext(page, size, userId, date);
        return new ResponseEntity<>(scheduleService.findAllSchedules(context), HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long todoId){
        return new ResponseEntity<>(scheduleService.findScheduleById(todoId), HttpStatus.OK);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @RequestBody ScheduleRequestDto dto, @PathVariable Long todoId
    ){
        return new ResponseEntity<>(scheduleService.updateSchedule(dto, todoId), HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteSchedule(
            @RequestBody @Valid PasswordRequestDto dto, @PathVariable Long todoId){
        scheduleService.deleteSchedule(dto, todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
