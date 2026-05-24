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

public class ProjectRequestDto {
	
	@NotBlank(message = "project name is required")
	@Size(max=100,message = "Project name cannot exceed 100 characters")
	private String projectName;
	
	@NotBlank(message = "calendar type is required")
	private String calendarType;
	
	@NotBlank(message = "weekend configuration is required")
	private String weekendConfig;

}
