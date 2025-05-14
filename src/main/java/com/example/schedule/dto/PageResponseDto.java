package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto<T> {
    private List<T> data;
    private int page; // 리턴할 페이지
    private int size; // 페이지 사이즈(항목 수)
    private long totalItems; // 전체 항목 개수
    private int totalPages; // 전체 페이지 수

    public PageResponseDto(PageResponseDto<T> dto) {
        this.data = dto.data;
        this.page = dto.page;
        this.size = dto.size;
        this.totalItems = dto.totalItems;
        this.totalPages = dto.totalPages;
    }
}