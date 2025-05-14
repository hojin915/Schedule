package com.example.schedule.service;

import com.example.schedule.dto.user.UserRequestDto;
import com.example.schedule.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto dto);
    List<UserResponseDto> findAllUsers();
}
