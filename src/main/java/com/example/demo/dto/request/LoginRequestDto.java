package com.example.demo.dto.request;

import java.time.LocalDateTime;

import com.example.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


	public class LoginRequestDto {

	    private String email;

	    private String password;
	}


