package com.example.demo.dto.request;

import java.time.LocalDateTime;

import com.example.demo.entity.User;

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

public class RoleRequestDto {
	
	@NotBlank(message = "role name is required")
	@Size(min=2,max=50,message = "Role name must be between 2 and 50 characters")
	private String roleName;

}
