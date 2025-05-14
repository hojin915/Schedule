package com.example.schedule.service;

import com.example.schedule.dto.user.UserRequestDto;
import com.example.schedule.dto.user.UserResponseDto;
import com.example.schedule.entity.User;
import com.example.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto dto) {
        User user = new User(dto.getName(), dto.getEmail());
        return userRepository.registerUser(user);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAllUsers();
    }
}
