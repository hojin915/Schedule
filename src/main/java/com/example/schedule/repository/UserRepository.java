package com.example.schedule.repository;

import com.example.schedule.dto.user.UserResponseDto;
import com.example.schedule.entity.User;

import java.util.List;

public interface UserRepository {
    UserResponseDto registerUser(User user);
    List<UserResponseDto> findAllUsers();
}
