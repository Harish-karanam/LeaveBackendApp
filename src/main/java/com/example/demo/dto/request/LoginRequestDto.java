package com.example.demo.dto.request;

import java.time.LocalDateTime;

import com.example.demo.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


	public class LoginRequestDto {

	
		@NotBlank(message = "email is required")
		@Email(message="invalid email format")
	    private String email;

		@NotBlank(message = "password is required")
		@Size(min=6,max=20,message = "Password must be between 6 to 20 characters")
	    private String password;
	}


