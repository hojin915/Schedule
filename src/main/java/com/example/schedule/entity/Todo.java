package com.example.schedule.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Todo {
    private Long todoId;
    private String todo;
    private String writerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;

    public Todo(String todo, String writerName, String password){
        this.todo = todo;
        this.writerName = writerName;
        this.password = password;
    }
}
