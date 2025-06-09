package org.example.cloud.controller;

import lombok.RequiredArgsConstructor;
import org.example.cloud.dto.UserRequestDto;
import org.example.cloud.dto.UserResponseDto;
import org.example.cloud.entity.User;
import org.example.cloud.repository.UserRepository;
import org.example.cloud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.ok(createdUser);
    }
}
