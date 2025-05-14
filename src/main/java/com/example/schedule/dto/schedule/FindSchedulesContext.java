package com.example.schedule.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FindSchedulesContext {
    private int page;
    private int size;
    private Long userId;
    private LocalDate date;

    public int getOffset() {
        return (page - 1) * size;
    }
}