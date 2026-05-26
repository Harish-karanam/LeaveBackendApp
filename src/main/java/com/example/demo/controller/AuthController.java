package com.example.demo.controller;




import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LoginRequestDto;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.LoginResponseDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.service.AuthService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(
        name = "Authentication APIs",

        description = "Login and authentication APIs"
)
public class AuthController {

	//register
    private final AuthService authService;
    
    @PostMapping("/register")
    @Operation(
            summary = "Register User",

            description = "Register user and convert password to hashcode  save data in database"
    )
    public UserResponseDto register(

            @Valid
            @RequestBody UserRequestDto dto) {

        return authService.register(dto);
    }
    
    //login

    @PostMapping("/login")
    @Operation(
            summary = "Login User",

            description = "Authenticate user and generate JWT token"
    )
    public LoginResponseDto login(
          @Valid  @RequestBody LoginRequestDto dto) {

        return authService.login(dto);
    }
}