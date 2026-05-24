package com.example.demo.dto.response;

import java.time.LocalDateTime;

import com.example.demo.entity.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponseDto {
	 private String token;

	    private String role;

	    private String name;

}






