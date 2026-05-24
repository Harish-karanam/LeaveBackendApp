package com.example.demo.controller;



import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LoginRequestDto;

import com.example.demo.dto.response.LoginResponseDto;

import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody LoginRequestDto dto) {

        return authService.login(dto);
    }
}