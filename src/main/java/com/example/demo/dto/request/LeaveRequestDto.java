package com.example.demo.dto.request;



import java.time.LocalDate;


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


public class LeaveRequestDto {

	@NotNull(message = "user id is required")
    private Long userId;

	@NotNull(message = "leaveTypeId is required")
    private Long leaveTypeId;

	@NotNull(message = "startdate is required")
	@FutureOrPresent(message = "start date cannot be in past")
    private LocalDate startDate;

	@NotNull(message = "end date is required")
	@FutureOrPresent(message = "start date cannot be in past")
    private LocalDate endDate;

	@NotBlank(message = "reason is required")
	@Size(min=5,max=500, message = "reason must be between 5 to 500 characters")
    private String reason;
    private boolean halfDay;
}
