package com.example.demo.controller;



import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;

import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(
        name = "User APIs",

        description = "User management APIs"
)
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Create User",

            description = "Create a new employee"
    )
    public UserResponseDto createUser(
          @Valid  @RequestBody UserRequestDto dto) {

        return userService.createUser(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @Operation(
            summary = "Get All Users",

            description = "Fetch all employees"
    )
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get User By ID",

            description = "Fetch user details using ID"
    )
    public UserResponseDto getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete User",

            description = "Delete user by ID"
    )
    public void deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);
    }
}