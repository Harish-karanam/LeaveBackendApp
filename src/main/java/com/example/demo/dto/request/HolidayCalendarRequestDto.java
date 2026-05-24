package com.example.demo.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.entity.User;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HolidayCalendarRequestDto {
	
	@NotBlank(message="Holiday name is required")
	@Size(max=100,message = "HOliday name cannot exceed 100 characters")
	private String holidayName;

	@NotNull(message = "holiday date is required")
	@FutureOrPresent(message = "holiday date cannot be in past")
	private LocalDate holidayDate;

	@NotBlank(message = "holiday type is required")
	private String type;

	@NotNull(message = "project id is required")
	private Long projectId;

}
