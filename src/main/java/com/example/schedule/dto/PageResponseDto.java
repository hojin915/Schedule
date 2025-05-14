package com.example.schedule.dto;

import com.example.schedule.dto.schedule.ScheduleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto<T> {
    private List<T> data;
    private int page;
    private int size;
    private long totalItems;
    private int totalPages;

    public PageResponseDto(PageResponseDto<T> dto) {
        this.data = dto.data;
        this.page = dto.page;
        this.size = dto.size;
        this.totalItems = dto.totalItems;
        this.totalPages = dto.totalPages;
    }
}
