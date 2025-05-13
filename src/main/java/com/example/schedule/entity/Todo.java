package com.example.schedule.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Todo {
    private Long todoId;
    private String todo;
    private String writerName;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;

    public Todo(String todo, String writerName, Long writerId, String password){
        this.todo = todo;
        this.writerName = writerName;
        this.writerId = writerId;
        this.password = password;
    }
}
