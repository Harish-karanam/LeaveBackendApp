package com.example.demo.service;



import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.request.LoginRequestDto;

import com.example.demo.dto.response.LoginResponseDto;

import com.example.demo.security.CustomUserDetailsService;

import com.example.demo.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager
            authenticationManager;

    private final CustomUserDetailsService
            userDetailsService;

    private final JwtService jwtService;

    public LoginResponseDto login(
            LoginRequestDto dto) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        dto.getEmail(),

                        dto.getPassword()
                )
        );

        UserDetails userDetails =

                userDetailsService
                        .loadUserByUsername(dto.getEmail());

        String token =
                jwtService.generateToken(userDetails);

        return new LoginResponseDto(

                token,

                userDetails.getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority(),

                userDetails.getUsername()
        );
    }
}