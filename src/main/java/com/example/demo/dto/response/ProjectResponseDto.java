package com.example.demo.dto.response;

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

public class ProjectResponseDto {
	private Long id;
	private String projectName;
	private String calendarType;
	private String weekendConfig;

}
