package com.example.demo.dto.response;

import java.time.LocalDate;
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

public class HolidayCalendarResponseDto {
	private Long id;

	private String holidayName;

	private LocalDate holidayDate;

	private String type;

	private Long projectId;

}
