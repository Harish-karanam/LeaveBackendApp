package com.example.demo.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.entity.User;
import com.example.demo.enums.LeaveStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LeaveResponseDto {
	private Long id;

	private String employeeName;

	private String leaveType;

	private LocalDate startDate;

	private LocalDate endDate;

	private Double totalDays;

	private String reason;

	private LeaveStatus status;

	private String managerComment;

	private String hrComment;

}
